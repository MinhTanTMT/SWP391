/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.HomeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.MenuDaily;

/**
 * Lớp gọi hàm và đưa dữ liệu lên trang
 *
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Trịnh Minh Tân
 */
@WebServlet(name = "HomeShow", urlPatterns = {"/homeshow"})
public class HomeShow extends HttpServlet {

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
            out.println("<title>Servlet HomeShow</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeShow at " + request.getContextPath() + "</h1>");
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
        String saledisplay = request.getParameter("saledisplay");
        String cheapdisplay = request.getParameter("cheapdisplay");

        int num1 = Integer.parseInt(saledisplay);
        int num2 = Integer.parseInt(cheapdisplay);
        
        HomeDAO obj = new HomeDAO();

        // Lấy danh sách dữ liệu
        List<MenuDaily> foodmenu = obj.getFoodMenu(); 

        List<MenuDaily> foodcheap = obj.getFoodCheap(foodmenu);

        List<MenuDaily> foodsale = obj.getFoodSale(foodmenu);

        // Bắn dữ liệu lên trang
        request.setAttribute("foodsale", obj.setDisplay(foodsale, num1, 1));

        request.setAttribute("foodcheap", obj.setDisplay(foodcheap, num2, 2));

        request.setAttribute("num1", num1);

        request.setAttribute("num2", num2);
        
        request.setAttribute("type", 1); // Kiểu trang home

        request.getRequestDispatcher("home.jsp").forward(request, response);
        // processRequest(request, response);
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
        processRequest(request, response);
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
