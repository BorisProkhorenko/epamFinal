package com.epam.web.command;


import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class UploadFileCommand implements Command {

    private static final String CV = "cv";
    private static final String ID = "id";
    private static final String DELIMITER = "\\";
    private static final String INIT_PARAMETER = "file-upload";
    private final ApplicantService service;
    private final ServletFileUpload servletFileUpload;

    public UploadFileCommand(ApplicantService service, ServletFileUpload servletFileUpload) {
        this.service = service;
        this.servletFileUpload = servletFileUpload;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, FileUploadException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute(ID);
        List<FileItem> multiParts = servletFileUpload.parseRequest(request);
        FileItem item = multiParts.get(0);
        String fileName = item.getName();
        ServletContext servletContext = request.getServletContext();
        String filePath = servletContext.getInitParameter(INIT_PARAMETER) + DELIMITER + fileName;
        service.SavePhoto(id, filePath, item);

        return CommandResult.redirect(CV);
    }


}
