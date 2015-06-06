package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public abstract class MapTool extends InventoryItem implements MapToolBehavior, MapTouchDetector.TouchDetectedListener
{
    String name;
    String desc;

    public MapTool(Sprite sprite, String name, String desc)
    {
        super(sprite);
        this.name = name;
        this.desc = desc;
    }

    public void onTouchDetected(Map map, float x, float y)
    {
        Log.d("MAP", "detected map touch");

        use(map, x, y);
    }
}
