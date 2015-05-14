package com.cerealkillers.rootrunner.GameObjects.Items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public abstract class Item
{
    private String name;
    private String description;
    private int id;

    /**
     * The class constructor for an item.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param id The id of the item.
     */
    public Item(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
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

    /**
     * Returns the id of the item.
     * @return the id of the item.
     */
    public int getID()
    {
        return this.id;
    }
}
