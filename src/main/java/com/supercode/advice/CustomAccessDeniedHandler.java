package com.supercode.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.warn("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
