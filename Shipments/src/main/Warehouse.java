package main;

public class Warehouse extends Inventory {
    String name="";
    Inventory inventory = new Inventory();
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
