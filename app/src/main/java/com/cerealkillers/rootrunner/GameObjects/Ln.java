package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public class Ln extends MapTool
{
    /**
     * Ln constructor.
     * @param sprite The associated sprite.
     */
    public Ln(Sprite sprite)
    {
        super(sprite, "ln", "Create links to other directories.");
    }

    /**
     * Create a portal at the designated coordinates.
     * @param map The current map.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void use(Map map, float x, float y)
    {
        Log.d("LN", "detected ln tool use");
        MapObjectFactory factory = new MapObjectFactory();
        Portal portal = (Portal)factory.createMapObject("portal");
        portal.getSprite().setPosition(x,y);
        portal.setDestination(getDestination());

        map.addMapObject(portal);
    }

    private String getDestination()
    {
        //TODO Get destination from user
        return "map2";
    }
}
