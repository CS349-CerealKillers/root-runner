package com.cerealkillers.rootrunner.GameObjects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 6/3/15.
 */
public class PlayerInventory implements I_Inventory {

    private List<InventoryItem> mInventoryItems;

    public PlayerInventory(List<InventoryItem> items){
        mInventoryItems = items;
    }

    public PlayerInventory() {
        this(new ArrayList<InventoryItem>());
    }

    @Override
    public List<InventoryItem> getInventory() {
        return null;
    }

    @Override
    public void addItem(InventoryItem item) {
        if(item != null){
            mInventoryItems.add(item);
        }
    }

    @Override
    public void removeItem(@NonNull InventoryItem item) {
        if(item != null){
            mInventoryItems.remove(item);
        }
    }

    @Override
    public List<InventoryItem> findByTag(Tag tag) {
        List<InventoryItem> results = new ArrayList<>();
        if(tag != null){
            for (InventoryItem item : mInventoryItems){
                if (item.getTags().contains(tag)){
                    results.add(item);
                }
            }
        }
        return results;
    }
}
