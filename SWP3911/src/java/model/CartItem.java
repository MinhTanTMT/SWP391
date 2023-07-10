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
    private int priceSell;
    private int priceFinal;

    public CartItem(int foodId, String foodName, String foodImg, int quantity, int priceSell, int priceFinal) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.quantity = quantity;
        this.priceSell = priceSell;
        this.priceFinal = priceFinal;

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

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public int getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(int priceFinal) {
        this.priceFinal = priceFinal;
    }

}
