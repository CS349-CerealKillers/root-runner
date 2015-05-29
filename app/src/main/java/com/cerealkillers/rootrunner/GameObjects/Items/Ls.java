package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Author: Tyler Herrin
 * Date: 5/10/2015
 */
public class Ls extends  Tool
{
    /**
     * Class constructor for the ls tool.
     * @param id The id of the tool.
     */
    public Ls(int id)
    {
        super("ls", "Reveal information  about  the FILEs in the current directory.", id);
    }

    /**
     * Reveals any hidden files or directories on the map.
     * @param id the id of an object on the map. (Unused for the ls tool.)
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        // TODO: Display location info

        Log.d(Ls.class.getSimpleName(), "Used ls.");
    }
}
