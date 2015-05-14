package com.cerealkillers.rootrunner.GameObjects;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public class GameObject
{
    int id;

    public GameObject(int id)
    {
        this.id = id;
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
