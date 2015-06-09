package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.scene.BaseScene;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/5/2015.
 */
public class Gcc extends ObjectTool
{
    /**
     * The gcc constructor.
     * @param sprite The associated sprite.
     */
    public Gcc(Sprite sprite)
    {
        super(sprite, "gcc", "Compiles source code.");
    }

    /**
     * Take the target mapobject and creates a new mapopbject out of it.
     * @param map The current map.
     * @param mapObject The target map object.
     */
    public void use(Map map, MapObject mapObject)
    {
        Log.d("GCC", "detected gcc tool use");

        Tag locked = mapObject.getTag("locked");
        Tag itemType = mapObject.getTag("source code");
        if(itemType != null && itemType.value != null && locked.value.equals("unlocked"))
        {
            //TODO Figure out how we are going to get tmxObject and sprite for new MapObjects
            MapObject newObject = null;
            //newObject = new MapObject();

            // Set new mapobjects item type
            newObject.addTag(new Tag("type", itemType.value));
            // Set to have same permissions as source
            newObject.addTag(locked);

            map.replaceMapObject(mapObject, newObject);
        }
    }
}
