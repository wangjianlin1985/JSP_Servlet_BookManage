// 
// 
// 

package com.sa.filter;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;

@WebFilter({ "/*" })
public class EncodingFilter implements Filter
{
    private FilterConfig filterConfig;
    
    public EncodingFilter() {
        this.filterConfig = null;
    }
    
    public void destroy() {
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String encoding = this.filterConfig.getServletContext().getInitParameter("encoding");
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
    
    public void init(final FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }
}
