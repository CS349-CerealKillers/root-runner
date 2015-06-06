package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

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

    public void use(Map map, MapObject mapObject)
    {
        Log.d("GCC", "detected gcc tool use");

        Tag locked = mapObject.getTag("locked");
        Tag itemType = mapObject.getTag("source code");
        if(itemType != null && itemType.value != null && locked.value.equals("unlocked"))
        {
            //TODO Figure out how we are going to get tmxObject and sprite for new MapObjects
            MapObjectFactory factory = new MapObjectFactory();
            MapObject newObject = factory.createMapObject(null, null, "items");

            // Set new mapobjects item type
            newObject.addTag(new Tag("type", itemType.value));
            // Set to have same permissions as source
            newObject.addTag(locked);

            map.replaceMapObject(mapObject, newObject);
        }
    }
}
