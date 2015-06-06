package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class Rm extends ObjectTool
{
    public Rm(Sprite sprite)
    {
        super(sprite, "rm", "Remove a file");
    }

    public void use(MapObject mapObject)
    {
        Log.d("RM", "detected rm tool use");

        Tag locked = mapObject.getTag("locked");
        if(locked != null && locked.value.equals("unlocked"))
        {
            mapObject.onDetachedFromMap();
        }
    }
}
