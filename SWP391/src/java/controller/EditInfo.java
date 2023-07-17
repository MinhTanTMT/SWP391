/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Inforuser;

/**
 *
 * @author msi
 */
@WebServlet(name = "EditInfo", urlPatterns = {"/useredit"})
public class EditInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditInfo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditInfo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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

        UserDAO obj = new UserDAO();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String full_name = request.getParameter("full_name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        if (account != null) {

            Inforuser a = obj.getInfo(account.getId());

            request.setAttribute("type", 2);
            request.setAttribute("info", a);

            if (obj.checkInputInfo(full_name) && obj.checkInputInfo(address)) {
                if (obj.checkTimeTrue(dob)) {
                    if (obj.checkPhone(phone)) {
                   
                        Inforuser b = new Inforuser(a.getId(), a.getId_acc(), full_name, dob, address, phone, gender, null);

                        obj.updateInfo(b, a.getId_acc());
                        response.sendRedirect("user");
                    } else {
                        request.setAttribute("error", "Chưa nhập số điện thoại hoặc nhập sai");
                        request.getRequestDispatcher("user.jsp").forward(request, response);
                    }
                } else {
                    
                    request.setAttribute("error", "Thời gian sai thực tế");
                    request.getRequestDispatcher("user.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Chưa điền hoặc quá dài");
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Invalid account");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
