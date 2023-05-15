// 
// 
// 

package com.sa.web;

import javax.servlet.http.HttpSession;
import com.sa.domain.BookCart;
import javax.servlet.http.HttpServletRequest;

public class BookCartWebUtils
{
    public static BookCart getBookCart(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        BookCart bc = (BookCart)session.getAttribute("BookCart");
        if (bc == null) {
            bc = new BookCart();
            session.setAttribute("BookCart", (Object)bc);
        }
        return bc;
    }
}
