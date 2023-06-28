/*
}}}}
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CartItem;

public class CartDAO extends DBContext {

    public List<CartItem> getCartItemsByCustomerId(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        Connection con = DBContext.getConnection();

        String sql = "SELECT food.id, food.img, food.name_food, cart.quantity, food.describe_food, food.price_sell, food.img, menudaily.discout, menudaily.quantity, food.price_sell * menudaily.discout AS price_final " +
             "FROM cart " +
             "JOIN food ON cart.id_food = food.id " +
             "JOIN menudaily ON food.id = menudaily.id_food " +
             "WHERE cart.customer = ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem(
                        rs.getInt("id"),
                        rs.getString("name_food"),
                        rs.getString("img"),
                        rs.getInt("quantity"),
                        rs.getInt("price_sell"),
                        rs.getInt("price_final")
                );
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cartItems;
    }

    public static void main(String[] args) {

    }
}
