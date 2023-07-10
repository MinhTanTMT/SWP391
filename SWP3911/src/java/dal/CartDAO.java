/*
}}}}
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import database.DBContext;
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

        String sql = "SELECT food.id, food.img, food.name_food, cart.quantity, food.describe_food, food.price_sell, food.img, menudaily.discout, menudaily.quantity, food.price_sell * menudaily.discout AS price_final "
                + "FROM cart "
                + "JOIN food ON cart.id_food = food.id "
                + "JOIN menudaily ON food.id = menudaily.id_food "
                + "WHERE cart.customer = ?";

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

    public int getQuantityByFoodId(int customerId, int foodId) {
        int quantity = 0;
        Connection con = DBContext.getConnection();

        String sql = "SELECT cart.quantity"
                + "FROM cart "
                + "WHERE cart.customer = ? AND cart.id_food = ? ";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            st.setInt(2, foodId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return quantity;
    }

    public void removeCartItem(int customerId, int foodId) {
        Connection con = DBContext.getConnection();

        String sql = "DELETE FROM cart WHERE customer = ? AND id_food = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            st.setInt(2, foodId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            System.out.println(this.getClass().getName() + " : " + sql);
        }
    }
//        public void addNewCartItem(int customerId, int foodId) {
//        Connection con = DBContext.getConnection();
//
//        String sql = " INSERT INTO cart(id, customer, id_food,quantity)\n" +
//"       VALUES (?, ?, ?,?);";
//
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setInt(1, customerId);
//            st.setInt(2, foodId);
//            st.setInt(3, 1);        ;
//            st.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    public void UpdateItem(int customerId, int foodId, int quantity) {
        Connection con = DBContext.getConnection();

        String sql = "update cart set quantity= ? where id_food = ? and customer = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, foodId);
            st.setInt(3, customerId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addItem(int customerId, int foodId, int quantity) {
        Connection con = DBContext.getConnection();

        String sql = "insert into cart set quantity= ? where id_food = ? and customer = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, foodId);
            st.setInt(3, customerId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addNewItemToCart(int cusId, int foodId, int quantity) {
        Connection con = DBContext.getConnection();

        String sql = "INSERT INTO `swp391tmtnew`.`cart`\n"
                + "("
                + "`customer`,\n"
                + "`id_food`,\n"
                + "`quantity`)\n"
                + "VALUES\n"
                + "("
                + "?,\n"
                + "?,\n"
                + "?); ";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cusId);
            st.setInt(2, foodId);
            st.setInt(3, quantity);
            System.out.println(this.getClass().getName() + " - SQL : " + sql);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getQuantityByItemIdAndCustomer(int id, int cusId) {
        Connection con = DBContext.getConnection();
        String sql = "select quantity from cart where id_food = ? and customer = ? ";
        int quan = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, cusId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                quan = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quan;
    }

    public boolean checkExistItem(int foodId, int cusId) {
        Connection con = DBContext.getConnection();
        String sql = "select * from cart where id_food = ? and customer = ?";
        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, foodId);
            ps.setInt(2, cusId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count > 0;
    }

    public static void main(String[] args) {

    }
}
