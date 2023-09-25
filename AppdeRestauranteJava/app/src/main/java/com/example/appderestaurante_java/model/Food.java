package com.example.appderestaurante_java.model;

public class Food {
    private int imgFood;
    private String foodName;
    private String foodDescription;
    private String price;

    public Food(int imgFood, String foodName, String foodDescription, String price) {
        this.setImgFood(imgFood);
        this.setFoodName(foodName);
        this.setFoodDescription(foodDescription);
        this.setPrice(price);
    }

    public int getImgFood() {
        return imgFood;
    }

    public void setImgFood(int imgFood) {
        this.imgFood = imgFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
