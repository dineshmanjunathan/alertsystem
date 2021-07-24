package com.alert.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
/**
 * This Java filter demonstrates how to intercept the request
 * and transform the response to implement authentication feature.
 * for the website's back-end.
 *
 * @author www.codejava.net
 */
//@WebFilter("/*")
public class AuthenticationFilter implements Filter {
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
 
        boolean isLoggedIn = (session != null && session.getAttribute("MEMBER_ID") != null);
 
        String adminLoginURI = httpRequest.getContextPath() + "/admin";
        String spLoginURI = httpRequest.getContextPath() + "/stock/point/login";
        boolean isRegisterPage = httpRequest.getRequestURI().endsWith("/register");
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
        boolean isIndexPage = httpRequest.getRequestURI().endsWith("index.jsp");
 
        boolean isAdminLoginRequest = httpRequest.getRequestURI().equals(adminLoginURI);
        boolean isSPLoginRequest = httpRequest.getRequestURI().equals(spLoginURI);
 
       if (isLoggedIn || isAdminLoginRequest || isSPLoginRequest || isRegisterPage || isIndexPage || isLoginPage) {
            // continues the filter chain
            // allows the request to reach the destination
            chain.doFilter(request, response);
 
        } else {
            // the admin is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
 
        }
 
    }
 
   
    public void destroy() {
    }
 
    public void init(FilterConfig fConfig) throws ServletException {
    }
}