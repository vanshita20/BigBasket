package com.example.bigbasket;

public class DataModel {
     String cust_name;
   String cust_Address;
   String  product_name;
    String  product_price;
    String imageURL;
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    String phar_name;
    String phar_Address;
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }



    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_Address() {
        return cust_Address;
    }

    public void setCust_Address(String cust_Address) {
        this.cust_Address = cust_Address;
    }



    public String getPhar_name() {
        return phar_name;
    }

    public void setPhar_name(String phar_name) {
        this.phar_name = phar_name;
    }

    public String getPhar_Address() {
        return phar_Address;
    }

    public void setPhar_Address(String phar_Address) {
        this.phar_Address = phar_Address;
    }



}