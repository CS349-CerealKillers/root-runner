package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class AddUser extends MapTool
{
    public AddUser(Sprite sprite)
    {
        super(sprite, "adduser", "Add a user to the system.");
    }

    public void use(float x, float y)
    {
        Log.d("ADDUSER", "detected adduser tool use");
    }
}
