/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class OrderDetail extends Inforuser{

    private int id;
    private int id_orderdetail;
    private String timegiao;
    private String describe_food;
    private int ordertaking;
    private int delivery;
    private int customer;
    private String status_order;
    private int id_food;
    private int quantity;
    private int price;

    public OrderDetail() {
    }

    public OrderDetail(int id, int id_orderdetail, String timegiao, String describe_food, int ordertaking, int delivery, int customer, String status_order, int id_food, int quantity, int price, int id_acc, String full_name, String address, String phone, String gender, String role_name) {
        super(id_acc, full_name, address, phone, gender, role_name);
        this.id = id;
        this.id_orderdetail = id_orderdetail;
        this.timegiao = timegiao;
        this.describe_food = describe_food;
        this.ordertaking = ordertaking;
        this.delivery = delivery;
        this.customer = customer;
        this.status_order = status_order;
        this.id_food = id_food;
        this.quantity = quantity;
        this.price = price;
    }
    
    public OrderDetail(int id, int id_orderdetail, String timegiao, String describe_food, int ordertaking, int delivery, int customer, String status_order, int id_food, int quantity, int price) {
        this.id = id;
        this.id_orderdetail = id_orderdetail;
        this.timegiao = timegiao;
        this.describe_food = describe_food;
        this.ordertaking = ordertaking;
        this.delivery = delivery;
        this.customer = customer;
        this.status_order = status_order;
        this.id_food = id_food;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_orderdetail() {
        return id_orderdetail;
    }

    public void setId_orderdetail(int id_orderdetail) {
        this.id_orderdetail = id_orderdetail;
    }

    public String getTimegiao() {
        return timegiao;
    }

    public void setTimegiao(String timegiao) {
        this.timegiao = timegiao;
    }

    public String getDescribe_food() {
        return describe_food;
    }

    public void setDescribe_food(String describe_food) {
        this.describe_food = describe_food;
    }

    public int getOrdertaking() {
        return ordertaking;
    }

    public void setOrdertaking(int ordertaking) {
        this.ordertaking = ordertaking;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
