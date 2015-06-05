package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/5/2015.
 */
public class Ls extends ObjectTool
{
    public Ls(Sprite sprite)
    {
        super(sprite, "ls", "Lists information about a file.");
    }

    public void use(MapObject mapObject)
    {
        //TODO
        Log.d("ls", "detected ls tool use");
    }
}
