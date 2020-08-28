package main;

import java.util.HashMap;
import java.util.Map;

public class Order {
    String apple,orange,banana;
    Map<String,String> orderValue = new HashMap<String, String>();

    public Map<String, String> setOrderValues(final String appleValue, final String orangeValue, final String bananaValue){
        Map<String, String> orderValues = new HashMap<String, String>() {{
            put( "apple", appleValue);
            setOrder("apple",appleValue);
            put("orange", orangeValue);
            setOrder("orange",orangeValue);
            put( "banana", bananaValue);
            setOrder("banana",bananaValue);

        }};
    return orderValues;
    }

    public void setOrder(String key,String value){
        this.orderValue.put(key,value);
    }

}
