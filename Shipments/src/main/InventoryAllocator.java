package main;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryAllocator extends Warehouse{
    // Default input values
    static String shipment = "[ { name: owd, inventory: { apple: 5,orange: 4} },{ name: bcd, inventory: { apple: 5,orange: 4}  }]";
    static String orderInput = "{ apple: 6 }";
    static int InventoryValue;
    static Gson gson = new Gson  ();
    static Warehouse[] warehouseList ;
    static List<Warehouse> newWarehouseList = new ArrayList<Warehouse>();
    static Order orderObject;
    static int orderCount =0;
    static boolean flag = false;

    // Used Greedy Algorithm for identifying Shipment
    public static int organizeShipment(String shipment, String orderInput){
        warehouseList  = gson.fromJson(shipment, Warehouse[].class);
        orderObject  = gson.fromJson(orderInput, Order.class);
        Warehouse newWarehouse;
        System.out.println("Transformed Json to Java Object");
        orderObject.setOrderValues(orderObject.apple,orderObject.orange,orderObject.banana);

        for (Map.Entry<String,String> order:orderObject.orderValue.entrySet()) {

            if(order.getValue()!=null) {
                System.out.println("Order Input: key is " + order.getKey() + " and value is " + order.getValue());
                orderCount = Integer.parseInt(order.getValue());

                for (Warehouse warehouse : warehouseList) {
                    newWarehouse = new Warehouse();
                    flag = false;
                    Map<String, String> inventoryValues = warehouse.inventory.setInventoryValues(warehouse.inventory.apple, warehouse.inventory.orange, warehouse.inventory.banana);
                    if (inventoryValues.containsKey(order.getKey()) && inventoryValues.get(order.getKey()) != null) {
                        System.out.println("Inventory Input : key is "+order.getKey()+" value is " + inventoryValues.get(order.getKey())+" Warehouse name is "+warehouse.getName());
                        InventoryValue = Integer.parseInt(inventoryValues.get(order.getKey()));
                        // Order is less than or same as Inventory
                        if (orderCount != 0 && orderCount <= InventoryValue) {
                            newWarehouse.setName(warehouse.getName());
                            newWarehouse.inventory.setInventory(order.getKey(), String.valueOf(orderCount));
                            newWarehouseList.add(newWarehouse);
                            orderCount -= InventoryValue;
                            flag = true;
                            break;
                            // Order is greater than Inventory
                        } else if (orderCount != 0 && orderCount > InventoryValue) {
                            orderCount -= InventoryValue;
                            newWarehouse.setName(warehouse.getName());
                            newWarehouse.inventory.setInventory(order.getKey(), String.valueOf(InventoryValue));
                            newWarehouseList.add(newWarehouse);
                        }
                    }

                }
            }
        }

        return orderCount;
    }

    public static void main(String args[])throws Exception{

        if(args!=null && args.length==2) {
            shipment = args[0];
            orderInput = args[1];
        }
        int count = InventoryAllocator.organizeShipment(shipment,orderInput);

        //Order is more than Inventory
        if(count>0){
            System.out.println("Order cannot be shipped because there is not enough inventory");
            newWarehouseList= new ArrayList<Warehouse>();
        }else if(count==0 && !flag){   //Order is empty
            System.out.println("Order is empty. Please check your input!");
        }else {             //Order can be shipped.
            String output = gson.toJson(newWarehouseList);
            System.out.println("Order can be shipped. Output is " + output);
        }
    }
}
