package com.cerealkillers.rootrunner.GameObjects.Items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */


public class ConcreteItem extends Item
{
    private boolean isStatic;
    /**
     * The class constructor for com.ceralkillers.rootrunner.gameobjects.items that have no behavior besides having a description.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param id The id of the item.
     * @param isStatic Whether or not the item should be able to be picked up or not.
     */
    public ConcreteItem(String name, String description, int id, boolean isStatic)
    {
        super(name, description, id);
    }

    /**
     * Returns true if the object is static. Returns false if the object is not static.
     * @return if an object is static or not.
     */
    public boolean isStatic()
    {
        return this.isStatic;
    }
}
