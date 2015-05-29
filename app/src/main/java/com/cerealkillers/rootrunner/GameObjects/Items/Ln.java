package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameObjects.Structures.Portal;
import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Author: Tyler Herrin
 * Date: 5/10/2015
 */
public class Ln extends Tool
{
    /**
     * Class constructor for the ln tool.
     * @param id The id of the tool.
     */
    public Ln(int id)
    {
        super("ln", "Create a link to TARGET in the current directory.", id);
    }

    /**
     * Creates a portal object on the map that is linked to another map.
     * @param id the id of an object on the map. (Unused for the ln tool).
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        // TODO: Create a way of selecting different destinations
        map.addPortal(new Portal(map.getPortalID(), "destination"));

        Log.d(Ln.class.getSimpleName(), "Used ln.");
    }
}
