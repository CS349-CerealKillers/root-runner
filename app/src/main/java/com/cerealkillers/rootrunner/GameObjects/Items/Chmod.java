package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Author: Tyler Herrin
 * Date: 5/10/2015
 */
public class Chmod extends Tool
{
    /**
     * Class constructor for the chmod tool.
     * @param id The id of the tool.
     */
    public Chmod(int id)
    {
        super("chmod", "Change file access permissions.", id);
    }

    /**
     * Unlocks an object on the map.
     * @param id the id of the object to be unlocked.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        Item target = map.getItem(id);
        target.unlock();

        Log.d(Chmod.class.getSimpleName(), "Used chmod.");
    }
}
