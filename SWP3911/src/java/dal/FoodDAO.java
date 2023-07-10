/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import database.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FoodDAO extends DBContext {

    public List<Food> getAll() {
        Connection con = DBContext.getConnection();
        List<Food> list = new ArrayList<>();
        //String sql="select * from Categories";
        String sql = "SELECT `food`.`id`,\n"
                + "    `food`.`id_category`,\n"
                + "    `food`.`name_food`,\n"
                + "    `food`.`describe_food`,\n"
                + "    `food`.`price_buy`,\n"
                + "    `food`.`price_sell`,\n"
                + "    `food`.`img`\n"
                + "FROM `swp391tmtnew`.`food`";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Food(
                        rs.getInt("id"),
                        rs.getInt("id_category"),
                        rs.getString("name_food"),
                        rs.getString("describe_food"),
                        rs.getInt("price_buy"),
                        rs.getInt("price_sell"),
                        rs.getString("img")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        FoodDAO c = new FoodDAO();
        List<Food> list = c.getAll();

        System.out.println(list.get(0).getId() + "--" + list.get(0).getName_food());

    }
}
