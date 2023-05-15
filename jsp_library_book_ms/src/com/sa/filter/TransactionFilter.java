// 
// 
// 

package com.sa.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import com.sa.web.ConnectionContext;
import com.sa.util.JDBCUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;

@WebFilter({ "/*" })
public class TransactionFilter implements Filter
{
    public void destroy() {
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            ConnectionContext.getInstance().bind(connection);
            chain.doFilter(request, response);
            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
            final HttpServletResponse resp = (HttpServletResponse)response;
            final HttpServletRequest req = (HttpServletRequest)request;
            resp.sendRedirect(String.valueOf(req.getContextPath()) + "/error-1.jsp");
            return;
        }
        finally {
            ConnectionContext.getInstance().remove();
            JDBCUtils.releaseDB(connection);
        }
        ConnectionContext.getInstance().remove();
        JDBCUtils.releaseDB(connection);
    }
    
    public void init(final FilterConfig fConfig) throws ServletException {
    }
}
