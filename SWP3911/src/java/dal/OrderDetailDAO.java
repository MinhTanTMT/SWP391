/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import database.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author msi
 */
public class OrderDetailDAO {

    public List<OrderDetail> getOrderDetail(int idDelivery, int a) {

        Connection con = DBContext.getConnection();

        String data;

        if (a == 1) {
            data = "and (oorder.status_order not like '%done%' "
                    + "and oorder.status_order not like '%ok%' "
                    + "and oorder.status_order not like '%delivering%' "
                    + "and oorder.status_order not like '%complete%' "
                    + "and oorder.status_order not like '%cancel%') ";
        } else if (a == 2) {
            data = "and (oorder.status_order like '%delivering%') ";
        } else {
            data = "and (oorder.status_order like '%done%' "
                    + "or oorder.status_order like '%ok%' "
                    + "or oorder.status_order like '%complete%' "
                    + "or oorder.status_order like '%cancel%') ";
        }

        List<OrderDetail> list = new ArrayList<>();
        //String sql="select * from Categories";
        String sql = "select oorder.id, "
                + "inforuser.full_name , "
                + "inforuser.address, "
                + "inforuser.phone , "
                + "oorder.timegiao, "
                + "oorder.status_order, "
                + "orderdetail.quantity, "
                + "orderdetail.price, "
                + "food.name_food "
                + "from oorder, inforuser, "
                + "orderdetail, food "
                + "where inforuser.id_acc = oorder.customer "
                + "and oorder.id_orderdetail = orderdetail.id "
                + "and orderdetail.id_food = food.id "
                + "and oorder.delivery = ? "
                + data
                + "order by oorder.timegiao desc;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idDelivery);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("timegiao"),
                        rs.getString("status_order"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("name_food")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean getUpdateOrderDetail(int idOrder, String status, String time) {

        Connection con = DBContext.getConnection();
        String sql = "update oorder "
                + "set oorder.timegiao = ?, oorder.status_order = ?  where oorder.id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, time);
            st.setString(2, status);
            st.setInt(3, idOrder);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /*
    chỉnh sửa tất cả các status order tại nơi nó bằng done và của tôi quản lí
    */
    public boolean getUpdateAllStatus(int idOrdertaking, String status, String data) {

        Connection con = DBContext.getConnection();

        OrderDetailDAO obj = new OrderDetailDAO();

        String time = obj.getTimeCurrent();

        String sql = "update oorder "
                + "set oorder.timegiao = ?, "
                + "oorder.status_order = ?  "
                + "where oorder.ordertaking = ? and oorder.status_order like '%"+data+"%'";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, time);
            st.setString(2, status);
            st.setInt(3, idOrdertaking);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String getTimeCurrent() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(date);
        return strDate;
    }

    public boolean getStatus(int idOrder) {
        Connection con = DBContext.getConnection();

        boolean dk = true;

        String sql = "select oorder.status_order "
                + "from oorder where oorder.id = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idOrder);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("status_order").equalsIgnoreCase("complete")) {
                    dk = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dk;
    }

    public boolean getDKUpdateOrderDetail(int idOrder, String status) {

        OrderDetailDAO obj = new OrderDetailDAO();

        String time = obj.getTimeCurrent();

        if (obj.getStatus(idOrder)) {
            obj.getUpdateOrderDetail(idOrder, status, time);
            return true;
        } else {
            return false;
        }
    }

    public int getRoseOk(int idOrder, float persent) {

        Connection con = DBContext.getConnection();

        int a = 0;

        String sql = "select oorder.id, "
                + "oorder.timegiao, "
                + "orderdetail.price "
                + "from oorder, orderdetail "
                + "where oorder.id_orderdetail = orderdetail.id "
                + "and oorder.delivery = ? "
                + "and oorder.status_order like '%ok%';";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idOrder);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a += rs.getInt("price");

                String sql2 = "update oorder "
                        + "set oorder.status_order = 'complete' where oorder.id = ?";
//                String sql2 = "update oorder "
//                        + "set oorder.status_order = 'ok' where oorder.id = ?";

                try {
                    PreparedStatement st2 = con.prepareStatement(sql2);
                    st2.setInt(1, rs.getInt("id"));
                    st2.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return (int) (a * persent);
    }

    public int getWage(int idOrder) {
        Connection con = DBContext.getConnection();

        int a = 0;

        String sql = "select salary.wage from salary where salary.id_acc = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idOrder);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("wage");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    public boolean getAddRoseSalary(int id, double persent) {

        Connection con = DBContext.getConnection();

        OrderDetailDAO obj = new OrderDetailDAO();

        int moneyRose = obj.getRoseOk(id, (float) persent);
        int wage = obj.getWage(id);
        int money = (moneyRose + wage);

        String sql = "update salary "
                + "set salary.wage = ? where salary.id_acc = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, money);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public List<OrderDetail> getManager(int idManager, int a) {

        Connection con = DBContext.getConnection();

        String data;

        if (a == 1) {
            data = "and (oorder.status_order not like '%done%' "
                    + "and oorder.status_order not like '%ok%' "
                    + "and oorder.status_order not like '%delivering%' "
                    + "and oorder.status_order not like '%complete%' "
                    + "and oorder.status_order not like '%cancel%') ";
        } else if (a == 2) {
            data = "and (oorder.status_order like '%delivering%') ";
        } else if (a == 3) {
            data = "and oorder.status_order like '%ok%' ";
        } else {
            data = "and oorder.status_order like '%done%' ";
        }

        List<OrderDetail> list = new ArrayList<>();
        //String sql="select * from Categories";
        String sql = "select oorder.id, "
                + "oorder.delivery, "
                + "inforuser.full_name, "
                + "oorder.timegiao, "
                + "orderdetail.quantity, "
                + "food.name_food, "
                + "oorder.status_order "
                + "from oorder, inforuser, orderdetail, food "
                + "where inforuser.id_acc = oorder.delivery "
                + "and oorder.id_orderdetail = orderdetail.id "
                + "and orderdetail.id_food = food.id "
                + "and oorder.ordertaking = 4 "
                + data
                + "order by oorder.timegiao;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            //st.setInt(1, idManager);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("delivery"),
                        rs.getString("full_name"),
                        rs.getString("timegiao"),
                        rs.getInt("quantity"),
                        rs.getString("name_food"),
                        rs.getString("status_order")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
       

    public static void main(String[] args) {

        OrderDetailDAO obj = new OrderDetailDAO();
        int a;
        a = obj.getRoseOk(1, (float) 0.3);

        List<OrderDetail> list = obj.getManager(4, 0);

//        System.out.println(list.get(0).getFull_name());
        
        System.out.println(obj.getAddRoseSalary(1, 0.1));
        

//        System.out.println(a);
    }

}
