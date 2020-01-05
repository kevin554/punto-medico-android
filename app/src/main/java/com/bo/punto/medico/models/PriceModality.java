package com.bo.punto.medico.models;


public class PriceModality {

    private String title;
    private String price;
    private String features_array[];
    private int image;

    public PriceModality() {
    }

    public PriceModality(String title, String price, String... features_array) {
        this.title = title;
        this.price = price;
        this.features_array = features_array;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] getFeatures_array() {
        return features_array;
    }

    public void setFeatures_array(String[] features_array) {
        this.features_array = features_array;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
