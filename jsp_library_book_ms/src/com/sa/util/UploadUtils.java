// 
// 
// 

package com.sa.util;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileUploadException;
import java.io.FileOutputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.http.HttpServletRequest;

public class UploadUtils
{
    public static Object UploadUtils(final String kind, final HttpServletRequest request) throws Exception {
        final DiskFileItemFactory factory = new DiskFileItemFactory();
        final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
        upload.setSizeMax(5242880L);
        try {
            final List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
            for (final FileItem item : items) {
                if (item.isFormField()) {
                    final String name = item.getFieldName();
                    final String value = item.getString();
                    System.out.println(String.valueOf(name) + ": " + value);
                }
                else {
                    final String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    final String contentType = item.getContentType();
                    final long sizeInBytes = item.getSize();
                    System.out.println(fieldName);
                    System.out.println(fileName);
                    System.out.println(contentType);
                    System.out.println(sizeInBytes);
                    final InputStream in = item.getInputStream();
                    final byte[] buffer = new byte[1024];
                    int len = 0;
                    fileName = "d:\\files\\" + fileName;
                    System.out.println(fileName);
                    final OutputStream out = new FileOutputStream(fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    in.close();
                }
            }
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        return null;
    }
}
