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
@WebServlet(name = "SearchFood", urlPatterns = {"/search"})
public class SearchFood extends HttpServlet {

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
            out.println("<title>Servlet SearchFood</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchFood at " + request.getContextPath() + "</h1>");
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

        String find = request.getParameter("find"); 

        HomeDAO obj = new HomeDAO(); 

        List<MenuDaily> foodmenu = obj.getFoodMenu(); // lấy dữu liệu từ menu
        List<MenuDaily> list;
        
        find = obj.delSpace(find);

        if (obj.checkInputSearch(find)) { //kiểm tra xem có kí tự trong tìm kiếm không
            request.setAttribute("error", "Hãy nhập tìm kiếm");
        } else {       
            list = obj.getSearch(foodmenu, find); //lấy danh sách dữu liệu tìm được
            if (list.isEmpty()) { //nếu danh sách rỗng thì in thông báo
                request.setAttribute("error", "Không tìm thấy kết quả cho: " + find);
            } else { //nếu có danh sách thì bắn lên trang
                request.setAttribute("error", "Kết quả cho: " + find);
                request.setAttribute("search", list);

            }
        }
        
        request.setAttribute("type", 2); // dạng trang tìm kiếm
        request.getRequestDispatcher("home.jsp").forward(request, response);

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
