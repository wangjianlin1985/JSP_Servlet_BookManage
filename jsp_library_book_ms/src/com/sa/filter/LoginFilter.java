// 
// 
// 

package com.sa.filter;

import javax.servlet.ServletContext;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR }, urlPatterns = { "/*" })
public class LoginFilter implements Filter
{
    private String redirectPage;
    private String checkedUrls;
    private String userAuthority;
    private String adminAuthority;
    private String error_404;
    
    public void destroy() {
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest)request;
        final HttpServletResponse resp = (HttpServletResponse)response;
        final String servletPath = req.getServletPath();
        final List<String> urls = Arrays.asList(this.checkedUrls.split(","));
        if (!urls.contains(servletPath)) {
            chain.doFilter(request, response);
            return;
        }
        final Object user = req.getSession().getAttribute("user");
        if (user == null) {
            resp.getWriter().write("<script type='text/JavaScript'>window.open('" + req.getContextPath() + this.redirectPage + "','_top'); </script>");
            return;
        }
        final String identity = req.getSession().getAttribute("userIdentity").toString();
        if (identity.equals("user")) {
            final List<String> userUrls = Arrays.asList(this.userAuthority.split(","));
            if (!userUrls.contains(servletPath)) {
                chain.doFilter(request, response);
                return;
            }
        }
        if (identity.equals("sys")) {
            final List<String> adminUrls = Arrays.asList(this.adminAuthority.split(","));
            if (!adminUrls.contains(servletPath)) {
                chain.doFilter(request, response);
                return;
            }
        }
        resp.getWriter().write("<script type='text/JavaScript'>window.open('" + req.getContextPath() + this.error_404 + "','_top'); </script>");
        chain.doFilter(request, response);
    }
    
    public void init(final FilterConfig fConfig) throws ServletException {
        final ServletContext servletContext = fConfig.getServletContext();
        this.redirectPage = servletContext.getInitParameter("redirectPage");
        this.checkedUrls = servletContext.getInitParameter("checkedUrls");
        this.userAuthority = servletContext.getInitParameter("userAuthority");
        this.adminAuthority = servletContext.getInitParameter("adminAuthority");
        this.error_404 = servletContext.getInitParameter("error-404");
    }
}
