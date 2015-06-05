package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/5/2015.
 */
public class Gcc extends ObjectTool
{
    public Gcc(Sprite sprite)
    {
        super(sprite, "gcc", "Compiles source code.");
    }

    public void use(MapObject mapObject)
    {
        //TODO
        Log.d("GCC", "detected gcc tool use");

        Tag itemType = mapObject.getTag("source code");
        if(itemType != null)
        {

        }
    }
}
