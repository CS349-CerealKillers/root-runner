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
        Log.d("GCC", "detected gcc tool use");

        Tag itemType = mapObject.getTag("source code");
        if(itemType != null && itemType.value != null)
        {
            //TODO Figure out how we are going to get tmxObject and sprite for new MapObjects
            //TODO as well determine how source code determines the object it creates when compiled.
            MapObjectFactory factory = new MapObjectFactory();
            MapObject newObject = factory.createMapObject(null, null, "items");
            newObject.addTag(new Tag(itemType.value, null));
        }
    }
}
