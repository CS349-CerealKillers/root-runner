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
     *
     * @param sprite
     */
    public AddUser(Sprite sprite)
    {
        super(sprite, "adduser", "Add a user to the system.");
    }

    /**
     *
     * @param map
     * @param x
     * @param y
     */
    public void use(Map map, float x, float y)
    {
        Log.d("ADDUSER", "detected adduser tool use");
    }
}
