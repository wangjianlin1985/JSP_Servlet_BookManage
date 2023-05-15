// 
// 
// 

package com.sa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import java.awt.Graphics;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/codeServlet" })
public class CodeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        final HttpSession session = request.getSession();
        final int width = 60;
        final int height = 20;
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final BufferedImage image = new BufferedImage(width, height, 1);
        final Graphics g = image.getGraphics();
        final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final char[] rands = new char[4];
        for (int i = 0; i < 4; ++i) {
            final int rand = (int)(Math.random() * 36.0);
            rands[i] = chars.charAt(rand);
        }
        g.setColor(new Color(14474460));
        g.fillRect(0, 0, width, height);
        for (int i = 0; i < 120; ++i) {
            final int x = (int)(Math.random() * width);
            final int y = (int)(Math.random() * height);
            final int red = (int)(Math.random() * 255.0);
            final int green = (int)(Math.random() * 255.0);
            final int blue = (int)(Math.random() * 255.0);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, 3, 18));
        g.drawString(new StringBuilder().append(rands[0]).toString(), 1, 17);
        g.drawString(new StringBuilder().append(rands[1]).toString(), 16, 15);
        g.drawString(new StringBuilder().append(rands[2]).toString(), 31, 18);
        g.drawString(new StringBuilder().append(rands[3]).toString(), 46, 16);
        g.dispose();
        final ServletOutputStream sos = response.getOutputStream();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        final byte[] buffer = baos.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        baos.close();
        sos.close();
        session.setAttribute("checkcode", (Object)new String(rands));
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
