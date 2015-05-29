package com.cerealkillers.rootrunner.GameObjects.Items;

import com.cerealkillers.rootrunner.GameObjects.GameObject;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public abstract class Item extends GameObject
{
    private String name;
    private String description;
    private boolean isLocked;

    /**
     * The class constructor for an item.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param id The id of the item.
     * @param isLocked Whether or not the player has access to the item.
     */
    public Item(String name, String description, int id, boolean isLocked)
    {
        super(id);
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
    }

    /**
     * Returns the name of the item.
     * @return the name of the item.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the description of the item.
     * @return the description of the item.
     */
    public String getDescription()
    {
        return this.description;
    }
}
