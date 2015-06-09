package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class AddUser extends MapTool
{
    /**
     * AddUser constructor.
     * @param sprite The associated spirte.
     */
    public AddUser(Sprite sprite)
    {
        super(sprite, "adduser", "Add a user to the system.");
    }

    /**
     * Create a user at the specified coordinates.
     * @param map The associated map.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void use(Map map, float x, float y)
    {
        Log.d("ADDUSER", "detected adduser tool use");
    }
}
