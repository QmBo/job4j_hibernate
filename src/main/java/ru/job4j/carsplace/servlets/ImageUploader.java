package ru.job4j.carsplace.servlets;

import com.google.common.base.Joiner;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

/**
 * ImageUploader
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class ImageUploader {
    private static final Logger LOG = LogManager.getLogger(ImageUploader.class);
    private static final String PHOTO_ID = "photoId";
    private static final String IMAGES_FOLDER = "WEB-INF/bin/images";
    private static final String NUMBERS = "numbersOfPhoto";

    /**
     * Upload http servlet request.
     *
     * @param servlet the servlet
     * @param req     the req
     * @return the http servlet request
     */
    public static HttpServletRequest upload(HttpServlet servlet, HttpServletRequest req) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = servlet.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File(servletContext.getRealPath(IMAGES_FOLDER));
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    throw new IOException(format("%s make exception", folder));
                }
                //TODO UPLOAD DEFAULT IMAGE
            }
            int numbers = 0;
            for (FileItem item : items) {
                if (!item.isFormField() && item.getSize() != 0L) {
                    String fileName = format("%s_%s", System.currentTimeMillis(), item.getName());
                    File file = new File(Joiner.on(File.separatorChar).join(folder, fileName));
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                        req.setAttribute(Joiner.on("").join(PHOTO_ID, numbers++), fileName);
                    }
                } else {
                    String name = item.getFieldName();
                    req.setAttribute(name, item.getString("UTF-8"));
                }
            }
            req.setAttribute(NUMBERS, numbers);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return req;
    }

    /**
     * Delete boolean.
     *
     * @param servlet the servlet
     * @param photoId the photo id
     * @return the boolean
     */
    @SuppressWarnings("unused")
    public static boolean delete(HttpServlet servlet, String photoId) {
        boolean result = false;
        File folder = new File(servlet.getServletConfig().getServletContext().getRealPath(IMAGES_FOLDER));
        File image = new File(Joiner.on(File.separatorChar).join(folder, photoId));
        if (image.exists() && image.isFile()) {
            result = image.delete();
        }
        return result;
    }
}
