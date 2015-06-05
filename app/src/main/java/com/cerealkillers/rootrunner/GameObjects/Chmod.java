package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

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
        //TODO
        Log.d("chmod", "detected chmod tool use");
    }
}
