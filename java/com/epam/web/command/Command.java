package com.epam.web.command;

import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface represents Command Pattern and used in MVC for handling {@link HttpServletRequest} and
 * {@link HttpServletResponse} from JSP and send {@link CommandResult} to
 * {@link com.epam.web.controller.Controller}
 *
 * @author Boris Prokhorenko
 * @see HttpServletResponse
 * @see HttpServletRequest
 * @see CommandResult
 * @see com.epam.web.controller.Controller
 * @see javax.servlet.http.HttpServlet
 */
public interface Command {

    /**
     * Method handles {@link HttpServletRequest} and {@link HttpServletResponse} from JSP and send
     * {@link CommandResult} to {@link com.epam.web.controller.Controller}
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return {@link CommandResult}
     * @throws IOException         {@link IOException} - Work with file system from command
     * @throws ServiceException    {@link ServiceException} - Service layer exception
     * @throws FileUploadException {@link FileUploadException} - Work with file system from command
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException, FileUploadException;
}
