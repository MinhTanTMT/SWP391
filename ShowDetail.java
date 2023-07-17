/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.mysql.cj.Session;
import dal.CartDAO;
import dal.DetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.CartItem;
import model.FoodDetail;
import model.MenuDaily;

/**
 *
 * @author msi
 */
@WebServlet(name = "ShowDetail", urlPatterns = {"/detail"})
public class ShowDetail extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowDetail at " + request.getContextPath() + "</h1>");
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

        String n = request.getParameter("num");

        int num = 0;
        int rate;

        try {
            num = Integer.parseInt(n);

        } catch (Exception e) {
            e.printStackTrace();
        }

        DetailDAO detail = new DetailDAO();

        List<FoodDetail> list = detail.getDetailFood(num);

        List<MenuDaily> list1 = detail.DetailId(num);

        rate = detail.getAvg(num);
        request.setAttribute("num", num);

        request.setAttribute("commentfood", list);

        request.setAttribute("infofood", list1);

        request.setAttribute("rate", rate);

        request.getRequestDispatcher("fooddetail.jsp").forward(request, response);

//        list = obj.Detail();
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
        CartDAO cartDAO = new CartDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int accId = account.getId();
            int id = Integer.parseInt(request.getParameter("foodId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (cartDAO.checkExistItem(id, accId) == true) {
                int quan = cartDAO.getQuantityByItemIdAndCustomer(id, accId);
                cartDAO.UpdateItem(accId, id, quan + quantity);
            } else {
                cartDAO.addNewItemToCart(accId, id, quantity);
            }
            
            String param = request.getParameter("num");
            int num = Integer.parseInt(param);
            // Tạo URL đích
            String destinationURL = "detail";
            destinationURL += "?num=" + num;
            request.getSession().setAttribute("showSuccessMessage", true);

            // Chuyển hướng trình duyệt đến URL đích
            response.sendRedirect(destinationURL);
            
        } else {
            request.setAttribute("error", "Invalid account");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
