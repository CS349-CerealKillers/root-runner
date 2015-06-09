package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import java.util.List;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class PortalFactory
{
    public Portal getPortal(Map map, float x, float y)
    {
        Portal portal = null;
        MapObject item = map.findByTag("portal").get(0);

        if(item != null)
        {
            portal = (Portal)item.clone();
            portal.getSprite().setPosition(x,y);
            portal.createTouchArea(portal.getSprite());
        }

        return portal;
    }
}
