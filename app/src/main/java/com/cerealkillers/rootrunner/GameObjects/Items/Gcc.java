package com.cerealkillers.rootrunner.GameObjects.Items;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class Gcc extends Tool
{
    /**
     * Class constructor for the gcc tool.
     * @param id The id of the tool.
     */
    public Gcc(int id)
    {
        super("gcc", "When you invoke GCC, it does preprocessing, compilation, assembly and linking.", id);
    }

    /**
     * Compiles source code into a tool.
     * @param id the id of the target object.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz
        Log.d(Gcc.class.getSimpleName(), "Used gcc.");
    }
}
