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
             
            //check cartItems not in menudaily today73-94
            List<CartItem> cartItemsNotInMenu = cartDAO.getCartItemsNotInMenuDaily(customerId);
            StringBuilder foodNamesBuilder = new StringBuilder();

            for (CartItem cartItem : cartItemsNotInMenu) {
                String foodName = cartItem.getFoodName();
                foodNamesBuilder.append(foodName).append(", ");
            }

            String foodNames = foodNamesBuilder.toString();
            if (foodNames.endsWith(", ")) {
                foodNames = foodNames.substring(0, foodNames.length() - 2);
            }
            boolean cartNotification = false;

            if(cartItemsNotInMenu.size() > 0 ){
                cartNotification = true;
            }
            request.setAttribute("notification", cartNotification);
            request.setAttribute("cartItemsNotInMenu", cartItemsNotInMenu.size());
            request.setAttribute("foodNames", foodNames);
            
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
            
            String checkout = "false";
            checkout = request.getParameter("checkout");
            if("true".equals(checkout)){
                response.sendRedirect("checkout");

            } else {
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
            List<CartItem> cartItems = cartDAO.getCartItemsByCustomerId(customerId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            // Trả về kết quả thành công (nếu cần)
            response.setStatus(HttpServletResponse.SC_OK);
            }
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
