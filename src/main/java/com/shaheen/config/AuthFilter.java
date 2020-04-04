/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.config;

import com.shaheen.model.User;
import com.shaheen.repository.UserRepo;
import com.shaheen.repository.UserRepoImpl;
import com.shaheen.utility.MapObject;
import com.shaheen.utility.ReadWriteCookei;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lts
 */
public class AuthFilter implements Filter {

    FilterConfig filterConfig = null;

    private static final List<String> PUBLIC_MACHES = Arrays.asList("/login",
            "/register", "/static");

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        User user = (User) httpRequest.getSession().getAttribute("user");

        Optional<String> userCookie = ReadWriteCookei.readCookie("user", httpRequest);

        if (user != null || checkPublic(httpRequest)) {
            System.out.println("on chain.doFilter");
            chain.doFilter(request, response);
        } else if (userCookie.isPresent()) {
            performUserCookie(userCookie.get(), request, response, chain);

        } else {
            System.out.println("httpRequest.getRequestURI()" + httpRequest.getRequestURI());
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        System.out.println(this.getClass().getName() + " destroy called ");
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        System.out.println(this.getClass().getName() + " init called ");

    }

    private boolean checkPublic(HttpServletRequest httpRequest) {
        boolean startsWith = false;
        String path = httpRequest.getRequestURI();
        for (String publicPath : PUBLIC_MACHES) {
            if (path.startsWith(httpRequest.getContextPath() + publicPath)) {
                startsWith = true;
            }
        }
        return startsWith;
    }

    private void performUserCookie(String cookieJson, ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            Cookie cookie = new MapObject<Cookie>().mapJsonToObject(cookieJson);
            System.out.println(cookie);
//            User userFromJson = new MapObject<User>().mapJsonToObject(cookie.getValue());
//            UserRepo userRepo = new UserRepoImpl();
//
//            User findByUsernameAndPassword = userRepo.findByUsernameAndPassword(userFromJson.getUsername(), userFromJson.getPassword());
//            if (findByUsernameAndPassword != null) {
//                chain.doFilter(request, response);
//            } else {
//                request.getRequestDispatcher("login.jsp").include(request, response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
