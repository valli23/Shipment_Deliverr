package main;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    String apple,orange,banana;

    public void setInventory(String key,String value){
        if(key.equals("apple"))
            this.apple=value;
        if(key.equals("orange"))
            this.orange=value;
        if(key.equals("banana"))
            this.banana=value;
    }

    public Map<String, String> setInventoryValues(final String appleValue, final String orangeValue, final String bananaValue){
        Map<String, String> inventoryValues = new HashMap<String, String>() {{
            put( "apple", appleValue);
            put("orange", orangeValue);
            put( "banana", bananaValue);

        }};
        return inventoryValues;
    }
}
