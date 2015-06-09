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

    /**
     * MapTool constructor.
     * @param sprite The associated sprite.
     * @param name The name of the MapTool.
     * @param desc The description of the MapTool.
     */
    public MapTool(Sprite sprite, String name, String desc)
    {
        super(sprite);
        this.name = name;
        this.desc = desc;
    }

    /**
     * When a touch is detected use the tool on the coordinates.
     * @param map The current map.
     * @param x The x coordinate of a touch event.
     * @param y The y coordinate of a touch event.
     */
    public void onTouchDetected(Map map, float x, float y)
    {
        Log.d("MAP", "detected map touch");

        use(map, x, y);
    }
}
