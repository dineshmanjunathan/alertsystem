package com.alert.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionFilter implements Filter {
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession();
        Cookie[] allCookies = req.getCookies();
        ArrayList<String> skipList = getSkipList();
        String uri = req.getRequestURI();
        System.out.println();
        if(!uri.equals("/") || 
        		!uri.equals("/login") ||
        		!uri.equals("/admin/login") ||
        		!uri.equals("/stock/point/login") ||
        		!uri.equals("/index.jsp") ||
        		!uri.equals("login.jsp") ||
        		!uri.equals("/logout")) {
        	if(ses !=null) {
            	String isLoggedIn = (String) ses.getAttribute("LOGGED_ON");
            	String memberId = (String) ses.getAttribute("MEMBER_ID");
            	if(!"true".equals(isLoggedIn)) {
            		String uriArray[] = uri.split("/");
            		if(uriArray.length > 1) {
            			System.out.println(uriArray[1]);
                		if(!skipList.contains(uriArray[1])) {
                			res.sendRedirect("/");
                			return;
                		}
            		}
            	}
            }
        }
        if (allCookies != null) {
            Cookie session = 
              Arrays.stream(allCookies).filter(x -> x.getName().equals("JSESSIONID"))
                    .findFirst().orElse(null);

            if (session != null) {
                session.setHttpOnly(true);
                session.setSecure(true);
                res.addCookie(session);
            }
        }
        chain.doFilter(req, res);
    }

	private ArrayList<String> getSkipList() {
		ArrayList<String> skipList = new ArrayList<>();
		skipList.add("img");
		skipList.add("fonts");
		skipList.add("js");
		skipList.add("css");
		skipList.add("style.css");
		return skipList;
	}

}
