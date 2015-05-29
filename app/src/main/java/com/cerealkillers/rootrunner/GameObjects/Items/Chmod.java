package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/10/2015.
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

    /* Old way
    public void use(int id, Map map)
    {
        //TODO Implement me plz.
        Item target = map.getItem(id);

        Log.d(Chmod.class.getSimpleName(), "Used chmod.");
    }
     */

    // New way?
    public void use(InteractiveItem item)
    {
        item.unlock();
    }
}
