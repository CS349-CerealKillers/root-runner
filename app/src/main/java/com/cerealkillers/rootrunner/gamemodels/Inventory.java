package com.cerealkillers.rootrunner.gamemodels;


import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ben on 4/25/15.
 */
public class Inventory {

    Map<String, Item> itemList;

    public Inventory(){
        itemList = new TreeMap<>();
    }

    public Item getItemById(String itemId){
        return itemList.get(itemId);
    }

    public boolean contains(Item item) {
        return itemList.containsValue(item);
    }
}
