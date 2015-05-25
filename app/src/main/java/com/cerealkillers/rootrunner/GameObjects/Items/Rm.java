package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class Rm extends Tool
{
    /**
     * Class constructor for the rm tool.
     * @param id The id of the tool.
     */
    public Rm(int id)
    {
        super("rm", "Remove files or directories.", id);
    }

    /**
     * Removes a file from the map. Damages enemy characters it is used on.
     * @param id the id of the target object.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz.
        Log.d(Rm.class.getSimpleName(), "Used rm.");
    }
}
