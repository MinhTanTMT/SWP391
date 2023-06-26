/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CartItem {

    private int foodId;
    private String foodName;
    private String foodImg;
    private int quantity;
    private int price_sell;
    private int price_final;


    public CartItem(int foodId, String foodName, String foodImg, int quantity, int price_sell, int price_final) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.quantity = quantity;
        this.price_sell = price_sell;
        this.price_final = price_final;

    }

    // Getter and setter methods

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price_sell;
    }

    public void setPrice(int price_sell) {
        this.price_sell = price_sell;
    }

    public int getPricePay() {
        return price_final;
    }

    public void setPricePay(int price_final) {
        this.price_final = price_final;
    }

}
