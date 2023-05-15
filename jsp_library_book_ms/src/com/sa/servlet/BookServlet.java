// 
// 
// 

package com.sa.servlet;

import com.sa.util.ImgUtils;
import java.lang.reflect.Method;
import com.sa.web.Page;
import com.sa.web.CriteriaBook;
import com.sa.domain.BookBorrow;
import java.util.List;
import com.google.gson.Gson;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import com.sa.domain.Book;
import java.util.Collection;
import com.sa.domain.BookCart;
import com.sa.domain.User;
import java.util.HashMap;
import com.sa.web.BookCartWebUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.sa.service.ReturnService;
import com.sa.service.BorrowService;
import com.sa.service.BookService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/bookServlet" })
public class BookServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private BorrowService borrowService;
    private ReturnService returnService;
    
    public BookServlet() {
        this.bookService = new BookService();
        this.borrowService = new BorrowService();
        this.returnService = new ReturnService();
    }
    
    protected void fine(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userid = request.getParameter("userid");
        this.returnService.fine(userid);
        request.setAttribute("msg", (Object)("\u7528\u6237 " + userid + " \u5df2\u7f34\u6e05\u7f5a\u6b3e\uff01"));
        request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void returnBook(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final BookCart bc = BookCartWebUtils.getBookCart(request);
        final Collection<Book> books = bc.getBooks().values();
        final Map<String, Float> overFine = new HashMap<String, Float>();
        final float fine = Float.parseFloat(this.getServletContext().getInitParameter("overFine"));
        final String msg = this.returnService.returnBook(((User)request.getSession().getAttribute("user")).getId(), books, fine, overFine);
        request.setAttribute("overFine", (Object)overFine);
        request.setAttribute("msg", (Object)msg);
        request.getRequestDispatcher("return-success.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void borrowBook(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final BookCart bc = BookCartWebUtils.getBookCart(request);
        final Collection<Book> books = bc.getBooks().values();
        final String msg = this.borrowService.borrowBook(((User)request.getSession().getAttribute("user")).getId(), books);
        request.setAttribute("msg", (Object)msg);
        request.getRequestDispatcher("borrow-success.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void clear(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.bookService.clear(BookCartWebUtils.getBookCart(request));
        if (request.getParameter("kind") != null) {
            request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void remove(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String bookId = request.getParameter("bookId");
        this.bookService.removeBookFromCart(bookId, BookCartWebUtils.getBookCart(request));
        request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void addToReturnCart(final BookCart bc, final String idStr, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        boolean flag = true;
        flag = this.returnService.isUserBorrowBook(((User)request.getSession().getAttribute("user")).getId(), idStr);
        if (!flag) {
            request.setAttribute("msg", (Object)"\u8be5\u56fe\u4e66\u4e0d\u662f\u60a8\u7684\u5728\u501f\u56fe\u4e66\uff01");
            request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        this.bookService.addToCart(idStr, bc);
        request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void addToCart(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String idStr = request.getParameter("bookId");
        final int id = -1;
        boolean flag = true;
        final BookCart bc = BookCartWebUtils.getBookCart(request);
        if (request.getParameter("kind").equals("return")) {
            this.addToReturnCart(bc, idStr, request, response);
            return;
        }
        final User user = (User)request.getSession().getAttribute("user");
        final int curr_BorrowCount = this.borrowService.getCurBorrowCount(user.getId());
        if (curr_BorrowCount + bc.getBookNumber() >= 5) {
            request.setAttribute("msg", (Object)"\u8d85\u51fa\u501f\u9605\u6570\u91cf(5\u672c)");
            request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        if (this.borrowService.overBook(user.getId()) > 0L) {
            request.setAttribute("msg", (Object)"\u60a8\u5b58\u5728\u8d85\u671f\u56fe\u4e66\uff0c\u4e0d\u80fd\u7ee7\u7eed\u501f\u9605\uff01");
            request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        if (this.borrowService.isAlreadyBorrow(user.getId(), idStr) > 0L) {
            request.setAttribute("msg", (Object)"\u60a8\u5df2\u501f\u6709\u8be5\u56fe\u4e66\uff0c\u4e0d\u80fd\u91cd\u590d\u501f\u9605\uff01");
            request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        flag = this.bookService.addToCart(idStr, bc);
        if (flag) {
            request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/error-1.jsp");
    }
    
    protected void update(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String bookId = request.getParameter("bookId");
        final String bookName = request.getParameter("bookName");
        final String type = request.getParameter("type");
        final String author = request.getParameter("author");
        final String isbn = request.getParameter("isbn");
        final String press = request.getParameter("press");
        final String pubTime = request.getParameter("pubTime");
        final String allQuantity = request.getParameter("allQuantity");
        final String aviQuantity = request.getParameter("aviQuantity");
        final String imgPath = request.getParameter("imgPath");
        final String pageNo = request.getParameter("pageNo");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.bookService.update(new Book(bookId, bookName, type, isbn, author, press, new Date(sdf.parse(pubTime).getTime()), Integer.parseInt(allQuantity), Integer.parseInt(aviQuantity), imgPath));
        }
        catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("bookServlet?method=getBooks&pageNo=" + pageNo);
    }
    
    protected void getBook(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String bookId = request.getParameter("bookId");
        String type = "";
        if (request.getParameter("opType") != null) {
            type = request.getParameter("opType");
        }
        final Book book = this.bookService.getBook(bookId);
        request.setAttribute("book", (Object)book);
        if (type.equals("update")) {
            request.getRequestDispatcher("WEB-INF/pages/book-update.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        final List<BookBorrow> bookBorrowList = this.bookService.getBookBorrow(bookId);
        request.setAttribute("bookBorrowList", (Object)bookBorrowList);
        if (type.equals("broQuery")) {
            final Map<String, Object> result = new HashMap<String, Object>();
            if (book != null) {
                result.put("bookName", book.getBookName());
                result.put("author", book.getAuthor());
                result.put("type", book.getType());
                result.put("press", book.getPress());
                result.put("isbn", book.getIsbn());
                result.put("pubTime", book.getPubTime());
                result.put("allQuantity", book.getAllQuantity());
                result.put("aviQuantity", book.getAviQuantity());
            }
            else {
                result.put("msg", "\u4e0d\u5b58\u5728\u8be5\u56fe\u4e66\u4fe1\u606f\uff01");
            }
            final Gson gson = new Gson();
            final String jsonStr = gson.toJson((Object)result);
            response.setContentType("text/javascript;charset=utf-8");
            response.getWriter().print(jsonStr);
            return;
        }
        if (request.getSession().getAttribute("userIdentity") == null) {
            request.getRequestDispatcher("WEB-INF/pages/book_4User.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        if (request.getSession().getAttribute("userIdentity").toString().equals("user")) {
            request.getRequestDispatcher("WEB-INF/pages/book_4User.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        request.getRequestDispatcher("WEB-INF/pages/book.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getBooks(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String bookName = (request.getParameter("bookName") == null) ? "" : request.getParameter("bookName");
        String type = (request.getParameter("type") == null) ? "" : request.getParameter("type");
        String isbn = (request.getParameter("isbn") == null) ? "" : request.getParameter("isbn");
        String bookId = (request.getParameter("bookId") == null) ? "" : request.getParameter("bookId");
        final String pageNoStr = request.getParameter("pageNo");
        final String searchType = (request.getParameter("searchWay") == null) ? "" : request.getParameter("searchWay");
        final String searchValue = (request.getParameter("searchValue") == null) ? "" : request.getParameter("searchValue");
        if (!searchType.equals("")) {
            if (searchType.equals("bookName")) {
                bookName = searchValue;
            }
            else if (searchType.equals("bookId")) {
                bookId = searchValue;
            }
            else if (searchType.equals("isbn")) {
                isbn = searchValue;
            }
            else if (searchType.equals("type")) {
                type = searchValue;
            }
        }
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
        }
        catch (Exception ex) {}
        final CriteriaBook cb = new CriteriaBook(new Book(bookId, bookName, type, isbn, "", "", null, -1, -1, ""), pageNo);
        Page<Book> bookPage = null;
        try {
            bookPage = this.bookService.getBookPage(cb);
        }
        catch (Exception ex2) {}
        request.setAttribute("bookPage", (Object)bookPage);
        request.getRequestDispatcher("WEB-INF/pages/books.jsp").forward((ServletRequest)request, (ServletResponse)response);
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
    
    protected void getReturnPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.bookService.clear(BookCartWebUtils.getBookCart(request));
        request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getBorrowPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.bookService.clear(BookCartWebUtils.getBookCart(request));
        request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void getAddBookPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void addBook(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Book book = null;
        try {
            book = (Book)ImgUtils.uploadBookImg(request);
        }
        catch (ParseException e) {
            request.setAttribute("msg", (Object)"\u65e5\u671f\u8f93\u5165\u4e0d\u5408\u6cd5\uff01");
            request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        this.bookService.addBook(book);
        request.setAttribute("msg", (Object)("\u56fe\u4e66\uff1a" + book.getBookName() + "\u6dfb\u52a0\u6210\u529f\uff01"));
        request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
