package com.epam.web.controller;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    Logger LOGGER = LogManager.getLogger();
    private final CommandFactory commandFactory;
    private final static String COMMAND = "command";
    private final static String ERROR_PAGE = "error.jsp";
    private final static String COMMAND_HEADER = "/controller?command=";

    public Controller() {
        commandFactory = new CommandFactory();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandType = request.getParameter(COMMAND);
        Command command = commandFactory.create(commandType);
        String page;
        boolean isRedirect = false;
        try {
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            page = ERROR_PAGE;
        }
        if (isRedirect) {
            String contextPath = getServletContext().getContextPath();
            String pagePath = contextPath + COMMAND_HEADER + page;
            response.sendRedirect(pagePath);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

}
