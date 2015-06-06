package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public interface ObjectToolBehavior
{
    /**
     *
     * @param map The current map
     * @param mapObject the map object for this to be used on.
     */
    public void use(Map map, MapObject mapObject);
}
