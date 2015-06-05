package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class AddUser extends MapTool
{
    public AddUser(Sprite sprite, String name, String desc)
    {
        super(sprite, "adduser", "Add a user to the system.");
    }

    public void use(float x, float y)
    {

    }
}
