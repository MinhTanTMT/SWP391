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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;
import model.Inforuser;

/**
 *
 * @author msi
 */
public class UserDAO {

    Connection con = DBContext.getConnection();

    public boolean insertInfo(Inforuser a) {

        String sql = "I"
                + "NSERT INTO `inforuser`\n"
                + "(`id`,\n"
                + "`id_acc`,\n"
                + "`full_name`,\n"
                + "`dob`,\n"
                + "`address`,\n"
                + "`phone`,\n"
                + "`gender`,\n"
                + "`role_name`)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.setInt(2, a.getId_acc());
            st.setString(3, a.getFull_name());
            st.setString(4, a.getDob());
            st.setString(5, a.getAddress());
            st.setString(6, a.getPhone());
            st.setString(7, a.getGender());
            st.setString(8, a.getRole_name());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateInfo(Inforuser a, int id) {

        String sql = "UPDATE `inforuser`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`id_acc` = ?,\n"
                + "`full_name` = ?,\n"
                + "`dob` = ?,\n"
                + "`address` = ?,\n"
                + "`phone` = ?,\n"
                + "`gender` = ?,\n"
                + "`role_name` = ?\n"
                + "WHERE `id` = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.setInt(2, a.getId_acc());
            st.setString(3, a.getFull_name());
            st.setString(4, a.getDob());
            st.setString(5, a.getAddress());
            st.setString(6, a.getPhone());
            st.setString(7, a.getGender());
            st.setString(8, a.getRole_name());
            st.setInt(9, a.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Inforuser getInfo(int id) {

        Inforuser info = new Inforuser();

        String sql = "select inforuser.id, "
                + "inforuser.id_acc, "
                + "inforuser.full_name, "
                + "inforuser.dob, "
                + "inforuser.address, "
                + "inforuser.phone, "
                + "inforuser.gender, "
                + "inforuser.role_name "
                + "from inforuser "
                + "where inforuser.id_acc = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                info = new Inforuser(
                        rs.getInt("id"),
                        rs.getInt("id_acc"),
                        rs.getString("full_name"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("role_name"));
                return info;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean checkPhone(String s) {

        String regex = "^(0|84)(2(0[3-9]|1[0-6|8|9"
                + "]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|"
                + "4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6"
                + "|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-"
                + "6|8|9]|9[0-4|6-9])([0-9]{7})$";

        return Pattern.matches(regex, s);
    }

    public boolean checkInputInfo(String s) {

        if (s.length() >= 1 && s.length() <= 19) {
            return true;
        }
        return false;
    }


    public String getTimeCurrent() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }
    
    
    public boolean checkTimeTrue(String timeinput){
        String timecurrent = getTimeCurrent();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(timeinput, formatter);
        LocalDate date2 = LocalDate.parse(timecurrent, formatter);
        
        if (date1.isAfter(date2)) {
            return false;
        } else {
            return true;
        }
  
    }
    

    public static void main(String[] args) {

        Inforuser a = new Inforuser(18, 18, "cde", "2002-01-21", "hn", "12345678", "nam", null);

        UserDAO obj = new UserDAO();

        System.out.println(obj.checkTimeTrue("2024-03-30"));

        //obj.updateInfo(a , 18);
    }

}
