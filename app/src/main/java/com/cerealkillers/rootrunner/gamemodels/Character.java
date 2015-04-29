package com.cerealkillers.rootrunner.gamemodels;

/**
 * Created by ben on 4/25/15.
 */
public class Character {

    Inventory inventory;

    public Character(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }
}
