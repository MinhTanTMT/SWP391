package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import jakarta.servlet.http.HttpSession;
import model.CartItem;

/**
 *
 * @author msi
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            out.println("<title>Servlet HomeDisplay</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeDisplay at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int customerId = account.getId();
            request.setAttribute("customerId", customerId);
            CartDAO cartDAO = new CartDAO();
            List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
//            int a = cartItems[0].price_final;
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Invalid account");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        if (account != null) {
            int customerId = account.getId();
            CartDAO cartDAO = new CartDAO();

            String foodId = request.getParameter("foodId");
            String functionId = request.getParameter("functionId");
            String quantity = request.getParameter("quantity");

            if (foodId != null && !foodId.isEmpty()) {
            int addFoodId = Integer.parseInt(foodId);
            int quantityUpdate = Integer.parseInt(quantity);

                 if("add".equals(functionId)){
                     cartDAO.UpdateItem(customerId, addFoodId, quantityUpdate);
                }
            }
            String removeFoodIds = request.getParameter("remove");
            if (removeFoodIds != null) {
                    int removeFoodId = Integer.parseInt(removeFoodIds);
                    cartDAO.removeCartItem(customerId, removeFoodId);
                
            }

            // Cập nhật lại danh sách cartItems sau khi xóa
           
//            String increaseValue = request.getParameter("increase");
//            String decreaseValue = request.getParameter("decrease");
//
//            if (increaseValue != null) {
//                String[] increaseParts = increaseValue.split("\\?");
//                int increaseFoodId = Integer.parseInt(increaseParts[0]);
//                int index = Integer.parseInt(request.getParameter("index"));
//                int increaseQuantity = Integer.parseInt(request.getParameter("quantity" + index));
//                increaseQuantity++;
//                // Thực hiện xử lý tăng quantity với increaseFoodId và increaseQuantity
//                cartDAO.UpdateItem(customerId, increaseFoodId, increaseQuantity);
//            }
//
//            if (decreaseValue != null) {
//                String[] decreaseParts = decreaseValue.split("\\?");
//                int decreaseFoodId = Integer.parseInt(decreaseParts[0]);
//                int index = Integer.parseInt(request.getParameter("index"));
//                int decreaseQuantity = Integer.parseInt(request.getParameter("quantity" + index));
//                decreaseQuantity--;
//                // Thực hiện xử lý giảm quantity với decreaseFoodId và decreaseQuantity
//                cartDAO.UpdateItem(customerId, decreaseFoodId, decreaseQuantity);
//            }
            List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            // Trả về kết quả thành công (nếu cần)
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Trả về lỗi nếu không tìm thấy tài khoản hợp lệ
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
