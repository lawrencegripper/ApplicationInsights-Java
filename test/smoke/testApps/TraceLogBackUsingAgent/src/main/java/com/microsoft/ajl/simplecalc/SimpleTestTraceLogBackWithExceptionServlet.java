package com.microsoft.ajl.simplecalc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Servlet implementation class SimpleTestTraceLogBackWithExceptionServlet
 */
@WebServlet(description = "calls logback with exception", urlPatterns = "/traceLogBackWithException")
public class SimpleTestTraceLogBackWithExceptionServlet extends HttpServlet {

    private static final long serialVersionUID = -4480938547356817795L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletFuncs.geRrenderHtml(request, response);

        //jbosseap6 and jbosseap7 have error : org.slf4j.impl.Slf4jLogger cannot be cast to ch.qos.logback.classic.Logger
        Logger logger = (Logger) LoggerFactory.getLogger("root");
        logger.error("This is an exception!", new Exception("Fake Exception"));
    }
}