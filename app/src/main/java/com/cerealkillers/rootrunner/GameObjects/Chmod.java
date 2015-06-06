package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.CommandFacade;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/5/2015.
 */
public class Chmod extends ObjectTool
{
    public Chmod(Sprite sprite)
    {
        super(sprite, "chmod", "Changes file permissions.");
    }

    public void use(MapObject mapObject)
    {
        Log.d("chmod", "detected chmod tool use");

        Tag locked = mapObject.getTag("locked");
        if(locked != null && locked.value != null)
        {
            if(locked.value.equals("locked"))
            {
                CommandFacade.displayMessage(mapObject.getName() + " is unlocked!");
            }
            else // mapobject is unlocked
            {
                CommandFacade.displayMessage(mapObject.getName() + " is locked! Perhaps there is someway to unlock it...");
            }

        }
    }
}
