/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.controller;

import com.shaheen.model.User;
import com.shaheen.repository.UserRepo;
import com.shaheen.repository.UserRepoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lts
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private UserRepo userRepo = new UserRepoImpl();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = validateUserData(request);
        if (user != null) {
            setUserDataOnSession(request, user);
        } else {
            // add error message on login page
            request.setAttribute("errorMessage", "error username or password");
        }
    }

    private User validateUserData(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username" + username + "password" + password);
        if (username != null && password != null) {
            return userRepo.findByUsernameAndPassword(username.trim(), password.trim());
        }
        return null;
    }

    private void setUserDataOnSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }

}
