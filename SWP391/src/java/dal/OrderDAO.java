/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import model.Order;
import model.OrderDetail;
import model.Product;
import utils.DBUtils;

/**
 *
 * @author admin
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email, int statuscheck, String sDate, String eDate, String accountid) throws SQLException, Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Orders o INNER JOIN Accounts a ON o.AccID = a.accID WHERE a.email like ? ";

                if (statuscheck != 0) {
                    sql += "and o.status = " + statuscheck;
                }
                if (sDate != null && eDate != null && !sDate.isEmpty() && !eDate.isEmpty()) {
                    sql += "and o.OrdDate BETWEEN '" + sDate + "' AND '" + eDate + "'";
                }
                if (accountid != null && !accountid.isEmpty()) {
                    sql += "and a.accID ='" + accountid + "'";
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String ordDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("ShipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    orders.add(new Order(orderID, ordDate, shipDate, status, accID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orders;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws SQLException, Exception {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM OrderDetails o INNER JOIN Plants p ON o.FID = p.PID WHERE o.OrderID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderID);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int detailId = rs.getInt("DetailId");
                    int fID = rs.getInt("FID");
                    String pName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");
                    orderDetails.add(new OrderDetail(detailId, orderID, fID, pName, price, imgPath, quantity));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderDetails;
    }

    public static boolean cancelOrder(int orderId) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Orders SET status = 3 WHERE OrderID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean orderAgain(int orderID) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Orders SET status = 1 WHERE OrderID = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderID);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertOrder(String email, Map<Integer, Product>  cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where Accounts.email=? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                    System.out.println("accountid: " + accid);
                    Date d = new Date(System.currentTimeMillis());
                    System.out.println("order date: " + d);
                    sql = "insert Orders(OrdDate,status,AccID) values(?,?,?) ";
                    pst = cn.prepareStatement(sql);
                    pst.setDate(1, d);
                    pst.setInt(2, 1);
                    pst.setInt(3, accid);
                    pst.executeUpdate();
                    sql = "select top 1 OrderID from Orders order by OrderID desc ";
                    pst = cn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        orderid = rs.getInt("OrderID");
                        System.out.println("order ID: " + orderid);
                        Set<Integer> pids = cart.keySet();
                        for (Integer pid : pids) {
                            sql = "insert OrderDetails values(?,?,?) ";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, orderid);
                            pst.setInt(2, pid);
                            pst.setInt(3, cart.get(pid).getQuantity());
                            pst.executeUpdate();
                            cn.commit();
                            cn.setAutoCommit(true);

                        }
                        return true;
                    } else {
                        System.out.println("k chen order dc");
                    }
                }
            }

        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();

                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    public static ArrayList<Order> getOrdersBetweenDate(String sDate, String eDate) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Orders WHERE orderDate BETWEEN ? END ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, sDate);
                pstmt.setString(1, eDate);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String ordDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("ShipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    orders.add(new Order(orderID, ordDate, shipDate, status, accID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orders;
    }
}
