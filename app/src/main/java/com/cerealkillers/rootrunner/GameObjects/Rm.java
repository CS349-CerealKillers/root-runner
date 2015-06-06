package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class Rm extends ObjectTool
{
    /**
     *
     * @param sprite
     */
    public Rm(Sprite sprite)
    {
        super(sprite, "rm", "Remove a file");
    }

    /**
     *
     * @param map The current map
     * @param mapObject the map object for this to be used on.
     */
    public void use(Map map, MapObject mapObject)
    {
        Log.d("RM", "detected rm tool use");

        Tag locked = mapObject.getTag("locked");
        if(locked != null && locked.value.equals("unlocked"))
        {
            map.removeMapObject(mapObject);
        }
    }
}
