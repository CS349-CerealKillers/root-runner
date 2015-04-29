package com.cerealkillers.rootrunner.gamemodels;

/**
 * Created by ben on 4/25/15.
 */
public abstract class Character {

    private static final int MAX_HEALTH = 100;
    Inventory inventory;
    Tool currentTool;
    int health;
    private String name;

    public Character(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }

    /**
     * Set the current tool the player is wielding.
     * @param t must also be in the player's inventory
     */
    public void setCurrentTool(Tool t){
        if(inventory.contains(t)){
            this.currentTool = t;
        }
    }


    public void useTool(){
        if(this.currentTool != null){
            currentTool.use();
        }
    }

    public int getHealth(){
        return health;
    }

    /**
     * Add or subtract health. All values are safe to use.
     * The characters health is bounded between 0 and MAX_HEALTH.
     * @param health negative or positive values accepted
     */
    public void adjustHealth(int health){
        this.health = Math.min(MAX_HEALTH, Math.max(0, this.health + health));
    }

    public boolean isAlive(){
        return health > 0;
    }

    public String getName(){
        return this.name;
    }
}
