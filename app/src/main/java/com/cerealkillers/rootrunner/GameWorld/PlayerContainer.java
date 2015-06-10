package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.GameObjects.InventoryItem;

import java.util.List;

/**
 * Created by jharshman on 6/8/15.
 */
public class PlayerContainer {

    private static PlayerContainer ourInstance = new PlayerContainer();

    private int currentStealth;
    private List<InventoryItem> playerInventory;

    public static PlayerContainer getInstance() {
        return ourInstance;
    }

    /* empty constructor */
    private PlayerContainer() { }

    /* Update Stealth */
    public void updatePlayerStealth(int newStealth) {
        currentStealth = newStealth;
    }

    /* Get Player Stealth */
    public int getPlayerStealth() {
        return currentStealth;
    }

    /* Add Item to Inventory */
    public void addItem(InventoryItem item) {
        playerInventory.add(item);
    }

    /* Remove item from inventory */
    public void removeItem(InventoryItem item) {
        playerInventory.remove(item);
    }

}
