package com.cerealkillers.rootrunner.GameObjects.Items;

/**
 * Created by Tyler Herrin on 5/28/2015.
 */
public class InteractiveItem extends Item
{
    /**
     * The class constructor for com.ceralkillers.rootrunner.gameobjects.items thar can be
     * interacted with, but cannot be picked up.
     * @param name The name of the item.
     * @param desc The description of the item.
     * @param id The id of the item.
     */
    public InteractiveItem(String name, String desc, int id)
    {
        super(name, desc, id);
    }

    public void interact(Tool tool)
    {
        tool.use(this);
    }

    public void unlock()
    {

    }
}
