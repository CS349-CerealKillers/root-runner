package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public abstract class MapTool extends InventoryItem implements MapToolBehavior
{
    String name;
    String desc;

    public MapTool(int id, Sprite sprite, String name, String desc)
    {
        super(id, sprite);
        this.name = name;
        this.desc = desc;
    }

    private class MapTouchListener
    {
        public void onMapTouched(Map map, int x, int y)
        {
            Log.d("MAP", "detected map touch");
        }
    }
}
