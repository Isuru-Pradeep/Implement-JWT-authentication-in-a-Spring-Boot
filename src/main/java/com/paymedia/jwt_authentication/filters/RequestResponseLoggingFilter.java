package com.paymedia.jwt_authentication.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullUrl = uri + (queryString != null ? "?" + queryString : "");
        String ip = request.getRemoteAddr();

        logger.info("[Request] " + method + " " + fullUrl);
        logger.info("Headers:");
        logger.info("Client IP: " + ip);


        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            Collections.list(headerNames).forEach(headerName -> {
                if (!headerName.equalsIgnoreCase("authorization")) {
                    String headerValue = request.getHeader(headerName);
                    logger.info("   • " + headerName + ": " + headerValue);
                } else {
                    logger.info("   • authorization: [PROTECTED]");
                }
            });
        }

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        int status = response.getStatus();

        logger.info("⬅️ [Response] " + method + " " + fullUrl +
                " | Status: " + status +
                " | Time: " + duration + " ms");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Skip auth logging (you can add more exclusions here)
        return path.equals("/auth/login") || path.equals("/auth/signup");
    }
}

