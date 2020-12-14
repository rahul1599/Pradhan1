package com.example.pradhan;

public class ItemSstockPPojo {

    String name;
    String quantity;

    public  ItemSstockPPojo(){

    }

    public ItemSstockPPojo(String name,String quantity){
        this.name=name;
        this.quantity=quantity;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void setQuantity(String quantity){
        this.quantity=quantity;
    }
    public String getQuantity(){
        return quantity;
    }
}
