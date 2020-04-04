/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.shaheen.model.User;
import com.shaheen.utility.ReadWriteCookei;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lts
 */
public class CookieFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println(this.getClass().getName() + " init called ");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("readCookie ---");

        String requestURI = httpRequest.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        Optional<String> readCookie = ReadWriteCookei.readCookie("testCookie", httpRequest);
        // old user and enable cookie
        if (readCookie.isPresent()) {
            System.out.println("readCookie else");

            chain.doFilter(request, response);
        } // new user or disable cookie
        else {
            System.out.println("readCookie else");
            Cookie cookie = new Cookie("testCookie", "test");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.addCookie(cookie);
            request.setAttribute("errorMessage", "please enable cookie to go on ");
            request.getRequestDispatcher("login.jsp").include(request, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getName() + " destroy called ");
    }
}
