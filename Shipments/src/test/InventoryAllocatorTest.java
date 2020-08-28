package test;

import main.InventoryAllocator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class InventoryAllocatorTest {

    @Test
    public void ShipOrderMultipleWHTest(){
         String shipment = "[ { name: owd, inventory: { apple: 5,orange: 4} },{ name: bcd, inventory: { apple: 5,orange: 4}  }]";
         String orderInput = "{ apple: 7 }";

        InventoryAllocator inventoryAllocator = new InventoryAllocator();
        int count = InventoryAllocator.organizeShipment(shipment,orderInput);
        Assert.assertTrue("Shipment processed from multiple warehouses.",count<=0);
    }

    @Test
    public void ShipOrderSingleWHTest(){
        String shipment = "[ { name: owd, inventory: { apple: 5,orange: 4} },{ name: bcd, inventory: { apple: 5,orange: 4}  }]";
        String orderInput = "{ apple: 5 }";

        int count = InventoryAllocator.organizeShipment(shipment,orderInput);
        Assert.assertTrue("Shipment processed from Single warehouse.",count<=0);
    }

    @Test
    public void InsufficientInventoryTest(){
        String shipment = "[ { name: owd, inventory: { apple: 5,orange: 4} } ]";
        String orderInput = "{ apple: 10 }";

        int count = InventoryAllocator.organizeShipment(shipment,orderInput);
        Assert.assertTrue("Order cannot be shipped because there is not enough inventory.",count>0);
    }

    @Test
    public void EmptyOrderTest(){
        String shipment = "[ { name: owd, inventory: { apple: 5,orange: 4} } ]";
        String orderInput = "{ apple: 0 }";

        int count = InventoryAllocator.organizeShipment(shipment,orderInput);
        Assert.assertTrue("Order is Empty. Please Check!.",count==0);
    }
}