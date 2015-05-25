package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class Adduser extends Tool
{
    /**
     * Class constructor for the adduser tool.
     * @param id The id of the tool.
     */
    public Adduser(int id)
    {
        super("adduser", "", id);
    }

    /**
     * Adds a user to the map.
     * @param id the id of an object on the map. (Unused in adduser)
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz.
        Log.d(Adduser.class.getSimpleName(), "Used adduser.");
    }
}
