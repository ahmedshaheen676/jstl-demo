/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen;

import com.shaheen.model.Employee;
import com.shaheen.repository.EmployeeRepo;
import com.shaheen.repository.EmployeeRepoImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lts
 */
@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

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
        request.getRequestDispatcher("index.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        if (name != null) {
            EmployeeRepo employeeRepo = new EmployeeRepoImpl();
            List<Employee> employees = employeeRepo.findByName(name);
            System.out.println(employees.size());
            if (employees.size() > 0) {
                System.out.println(employees);
                request.setAttribute("employees", employees);
            } else {
                request.setAttribute("errorMessage",
                        String.format("no data found for your input {%s}", name)
                );
            }
        } else {
            request.setAttribute("errorMessage", "name to search can't be null ");

        }
        request.getRequestDispatcher("index.jsp").include(request, response);

    }

}
