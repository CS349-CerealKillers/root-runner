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
        PortalFactory pf = new PortalFactory();
        Portal portal = pf.getPortal(map, x, y);

        map.addMapObject(portal);
    }
}
