/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.config;

import com.shaheen.model.User;
import com.shaheen.repository.UserRepo;
import com.shaheen.repository.UserRepoImpl;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lts
 */
public class AuthFilter implements Filter {

    FilterConfig filterConfig = null;

    private UserRepo userRepo = new UserRepoImpl();

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        System.out.println(this.getClass().getName() + " doFilter called ");
        String loginParam = filterConfig.getInitParameter("login");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String path = httpRequest.getRequestURI();
        
        System.out.println("session =" + httpRequest.getSession());

        boolean startsWith = path.startsWith(httpRequest.getContextPath() + loginParam);
        System.out.println("startsWith+" + startsWith);
        if (startsWith) {
            String method = httpRequest.getMethod();
            System.out.println("request method =" + method);

            if (method.equals("POST")) {
                User user = validateUserData(httpRequest);
                if (user != null) {
                    setUserDataOnSession(httpRequest,user);
                } else {
                    // add error message on login page
                    request.setAttribute("errorMessage", "error username or password");
                }

            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } else {
            User user = (User) httpRequest.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
        System.out.println(this.getClass().getName() + " destroy called ");
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        System.out.println(this.getClass().getName() + " init called ");

    }

    private User validateUserData(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
       return userRepo.findByUsernameAndPassword(username, password);
    }

    private void setUserDataOnSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }

}
