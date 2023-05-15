// 
// 
// 

package com.sa.servlet;

import java.lang.reflect.Method;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import com.sa.domain.System;
import com.sa.domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.sa.service.SystemService;
import com.sa.service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/loginServlet" })
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private SystemService systemService;
    
    public LoginServlet() {
        this.userService = new UserService();
        this.systemService = new SystemService();
    }
    
    protected void logOut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("login.jsp");
    }
    
    protected void doLogin(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String identity = request.getParameter("identity");
        final String id = request.getParameter("id");
        final String password = request.getParameter("password");
        boolean flag = false;
        Object user = null;
        if (id != null && !id.trim().equals("")) {
            if (identity.equals("user")) {
                user = this.userService.getUser(id);
                if (user != null && password.trim().equals(((User)user).getPassword())) {
                    flag = true;
                }
            }
            else {
                user = this.systemService.getAdmin(id);
                if (user != null && password.trim().equals(((System)user).getPassword())) {
                    flag = true;
                }
            }
            if (flag) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userIdentity", (Object)identity);
                response.sendRedirect("index.jsp");
                return;
            }
        }
        request.setAttribute("msg", (Object)"\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef");
        request.getRequestDispatcher("login.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String methodName = request.getParameter("method");
        try {
            final Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, request, response);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
