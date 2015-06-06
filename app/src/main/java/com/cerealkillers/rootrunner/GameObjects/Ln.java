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
     *
     * @param sprite
     */
    public Ln(Sprite sprite)
    {
        super(sprite, "ln", "Create links to other directories.");
    }

    /**
     *
     * @param map
     * @param x
     * @param y
     */
    public void use(Map map, float x, float y)
    {
        Log.d("LN", "detected ln tool use");
        PortalFactory pf = new PortalFactory();
        Portal portal = pf.getPortal(x, y);

        map.addMapObject(portal);
    }
}
