// 
// 
// 

package com.sa.servlet;

import com.sa.util.ImgUtils;
import com.sa.domain.System;
import com.sa.domain.Borrow;
import java.util.ArrayList;
import com.sa.web.Page;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import com.sa.web.CriteriaUser;
import com.sa.domain.User;
import java.util.Map;
import java.util.List;
import com.google.gson.Gson;
import com.sa.domain.AjaxFormData;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.sa.service.SystemService;
import com.sa.service.BorrowService;
import com.sa.service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/userServlet" })
public class UserServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private BorrowService borrowService;
    private SystemService systemService;
    
    public UserServlet() {
        this.userService = new UserService();
        this.borrowService = new BorrowService();
        this.systemService = new SystemService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
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
    
    protected void getColleges(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final List<AjaxFormData> colleges = this.userService.getColleges();
        final Map<String, String> cm = new HashMap<String, String>();
        final StringBuffer cgStr = new StringBuffer();
        for (int i = 0; i < colleges.size(); ++i) {
            cgStr.append(colleges.get(i).getCollege());
            if (i < colleges.size() - 1) {
                cgStr.append(",");
            }
        }
        cm.put("college", cgStr.toString());
        final Gson gson = new Gson();
        final String jsonStr = gson.toJson((Object)cm);
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonStr);
    }
    
    protected void getMajors(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String college = request.getParameter("college");
        final List<AjaxFormData> majors = this.userService.getMajors(college);
        final Map<String, String> mm = new HashMap<String, String>();
        final StringBuffer cgStr = new StringBuffer();
        for (int i = 0; i < majors.size(); ++i) {
            cgStr.append(majors.get(i).getMajor());
            if (i < majors.size() - 1) {
                cgStr.append(",");
            }
        }
        mm.put("major", cgStr.toString());
        final Gson gson = new Gson();
        final String jsonStr = gson.toJson((Object)mm);
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonStr);
    }
    
    protected void getClasses(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String major = request.getParameter("major");
        final List<AjaxFormData> classes = this.userService.getClasses(major);
        final Map<String, String> mm = new HashMap<String, String>();
        final StringBuffer cgStr = new StringBuffer();
        for (int i = 0; i < classes.size(); ++i) {
            cgStr.append(classes.get(i).getClasses());
            if (i < classes.size() - 1) {
                cgStr.append(",");
            }
        }
        mm.put("classes", cgStr.toString());
        final Gson gson = new Gson();
        final String jsonStr = gson.toJson((Object)mm);
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonStr);
    }
    
    protected void getUsers(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String college = (request.getParameter("college") == null) ? "" : request.getParameter("college");
        final String major = (request.getParameter("major") == null) ? "" : request.getParameter("major");
        final String classes = (request.getParameter("classes") == null) ? "" : request.getParameter("classes");
        final String name = (request.getParameter("name") == null) ? "" : request.getParameter("name");
        final String id = (request.getParameter("id") == null) ? "" : request.getParameter("id");
        final String pageNoStr = request.getParameter("pageNo");
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
        }
        catch (Exception ex) {}
        final CriteriaUser cu = new CriteriaUser(new User(id, name, college, major, classes, "", ""), pageNo);
        final Page<User> userPage = this.userService.getUserPage(cu);
        request.setAttribute("userPage", (Object)userPage);
        request.getRequestDispatcher("WEB-INF/pages/tab.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getFinePage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getUser(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("userid");
        String type = "";
        if (request.getParameter("type") != null) {
            type = request.getParameter("type");
        }
        final User user = this.userService.getUser(id);
        request.setAttribute("user", (Object)user);
        if (type.equals("update")) {
            request.getRequestDispatcher("WEB-INF/pages/user-update.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        if (type.equals("fine")) {
            final List<Borrow> borrowList = this.borrowService.getUserBorrowRec(id);
            final List<Borrow> fineList = new ArrayList<Borrow>();
            float fineMoney = 0.0f;
            for (int i = 0; i < borrowList.size(); ++i) {
                if (borrowList.get(i).getOverFine() != null && borrowList.get(i).getOverFine() > 0.0f) {
                    fineList.add(borrowList.get(i));
                    fineMoney += borrowList.get(i).getOverFine();
                }
            }
            request.setAttribute("fineMoney", (Object)fineMoney);
            request.setAttribute("fineList", (Object)fineList);
            request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        final List<Borrow> borrowList = this.borrowService.getUserBorrowRec(id);
        request.setAttribute("borrowList", (Object)borrowList);
        request.getRequestDispatcher("WEB-INF/pages/user-info.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void update(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String college = request.getParameter("college");
        final String major = request.getParameter("major");
        final String classes = request.getParameter("classes");
        final String password = request.getParameter("password");
        final String imgPath = request.getParameter("imgPath");
        final String pageNo = request.getParameter("pageNo");
        this.userService.updateUser(new User(id, name, college, major, classes, password, imgPath));
        response.sendRedirect("userServlet?method=getUsers&pageNo=" + pageNo);
    }
    
    protected void changePwd(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userId = request.getParameter("userid");
        final String newPwd = request.getParameter("newPwd");
        final String code = request.getParameter("code");
        final String checkCode = request.getSession().getAttribute("checkcode").toString();
        if (!checkCode.equalsIgnoreCase(code)) {
            request.setAttribute("codeMsg", (Object)"\u9a8c\u8bc1\u7801\u9519\u8bef\uff01");
            request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        if (request.getSession().getAttribute("userIdentity").equals("user")) {
            this.userService.changePwd(userId, newPwd);
            request.setAttribute("msg", (Object)("\u5bc6\u7801\u4fee\u6539\u6210\u529f,\u65b0\u5bc6\u7801\uff1a" + ((User)request.getSession().getAttribute("user")).getPassword()));
        }
        else {
            this.systemService.changePwd(userId, newPwd);
            request.setAttribute("msg", (Object)("\u5bc6\u7801\u4fee\u6539\u6210\u529f,\u65b0\u5bc6\u7801\uff1a" + ((System)request.getSession().getAttribute("user")).getPassword()));
        }
        request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getCgPwdPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getAddUserPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/user-add.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void addUser(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User user = (User)ImgUtils.uploadUserImg(request);
        this.userService.addUser(user);
        request.setAttribute("msg", (Object)("\u7528\u6237\uff1a" + user.getName() + "\u6dfb\u52a0\u6210\u529f\uff01"));
        request.getRequestDispatcher("WEB-INF/pages/user-add.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
