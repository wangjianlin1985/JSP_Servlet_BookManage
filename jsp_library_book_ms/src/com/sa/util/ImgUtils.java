// 
// 
// 

package com.sa.util;

import java.text.ParseException;
import com.sa.domain.Book;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.apache.commons.fileupload.FileUploadException;
import com.sa.domain.User;
import java.io.FileOutputStream;
import org.apache.commons.fileupload.FileItem;
import java.util.HashMap;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.http.HttpServletRequest;

public class ImgUtils
{
    public static Object uploadUserImg(final HttpServletRequest request) {
        final DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(512000);
        final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
        upload.setSizeMax(5242880L);
        final Map<String, String> maps = new HashMap<String, String>();
        try {
            final List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
            for (final FileItem item : items) {
                if (item.isFormField()) {
                    final String name = item.getFieldName();
                    final String value = item.getString("UTF-8");
                    maps.put(name, value);
                }
                else {
                    final String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    final String contentType = item.getContentType();
                    final long sizeInBytes = item.getSize();
                    final InputStream in = item.getInputStream();
                    final byte[] buffer = new byte[1024];
                    maps.put("imgPath", fileName);
                    int len = 0;
                    fileName = String.valueOf(request.getServletContext().getRealPath("/img/user/")) + fileName;
                    final OutputStream out = new FileOutputStream(fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    in.close();
                }
            }
            final User user = new User(maps.get("id"), maps.get("name"), maps.get("college"), maps.get("major"), maps.get("classes"), maps.get("id"), maps.get("imgPath"));
            System.out.println(user);
            return user;
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }
    
    public static Object uploadBookImg(final HttpServletRequest request) throws ParseException {
        final DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(512000);
        final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
        upload.setSizeMax(5242880L);
        final Map<String, String> maps = new HashMap<String, String>();
        try {
            final List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
            for (final FileItem item : items) {
                if (item.isFormField()) {
                    final String name = item.getFieldName();
                    final String value = item.getString("UTF-8");
                    maps.put(name, value);
                }
                else {
                    final String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    final String contentType = item.getContentType();
                    final long sizeInBytes = item.getSize();
                    final InputStream in = item.getInputStream();
                    final byte[] buffer = new byte[1024];
                    maps.put("imgPath", fileName);
                    int len = 0;
                    fileName = String.valueOf(request.getServletContext().getRealPath("/img/book/")) + fileName;
                    final OutputStream out = new FileOutputStream(fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    in.close();
                }
            }
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            final Date pubTime = new Date(sdf.parse(maps.get("pubTime")).getTime());
            final Book book = new Book(maps.get("bookId"), maps.get("bookName"), maps.get("type"), maps.get("isbn"), maps.get("author"), maps.get("press"), pubTime, Integer.parseInt(maps.get("allQuantity")), Integer.parseInt(maps.get("allQuantity")), maps.get("imgPath"));
            return book;
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }
}
