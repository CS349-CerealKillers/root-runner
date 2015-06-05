package com.cerealkillers.rootrunner.GameObjects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 6/3/15.
 */
public class PlayerInventory implements I_Inventory {

    private List<InventoryItem> mInventoryItems;
    private InventoryItem mCurrentTool;

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

    /**
     * Find an item in the inventory by tag.
     * Tags are searched by key only.
     * @param tag
     * @return
     */
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

    /**
     * Select a tool from the player's inventory as the current tool.
     * The item must already be in the inventory.
     * @param item
     */
    public void setCurrentTool(InventoryItem item){
        if(item != null){
            if(mInventoryItems.contains(item)){
                mCurrentTool = item;
            }
        }
    }

    /**
     * Returns default tool if no current tool is selected.
     * @return
     */
    public InventoryItem getCurrentTool(){
        return mCurrentTool;
    }
}
