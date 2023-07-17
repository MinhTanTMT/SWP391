package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.CartDAO;
import dal.OrderDetailDAO;
import dal.UserDAO;
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
import model.Inforuser;
import model.OrderDetail;

/**
 *
 * @author msi
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckOutController extends HttpServlet {

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
            UserDAO userDAO = new UserDAO();

            List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
            Inforuser users = userDAO.getPhoneandAddress(customerId);
            
            String defaultAddress = users.getAddress();
            String defaultPhoneNumber = users.getPhone();
            
            request.setAttribute("defaultAddress", defaultAddress);
            request.setAttribute("defaultPhoneNumber", defaultPhoneNumber);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
            
            List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
            
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            
            OrderDetailDAO orderDetail = new OrderDetailDAO();
            orderDetail.addOrder(customerId, phone, address);
            
            int orderId;
            orderId = orderDetail.getOrderId(customerId);
            
            if(orderId > 0){
                for (CartItem item : cartItems) {
                int foodId = item.getFoodId();
                int quantity = item.getQuantity();
                int price = item.getQuantity() * item.getPriceFinal();

                orderDetail.saveOrderDetail(orderId, foodId, quantity, price); // Hàm lưu thông tin chi tiết sản phẩm
                cartDAO.deleteCartItem(foodId, customerId);
                }  
            }
                                                 
            

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
