package com.cerealkillers.rootrunner.GameObjects.Structures;

import com.cerealkillers.rootrunner.GameObjects.GameObject;
import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public class Portal extends GameObject
{
    //TODO: perhaps this shouldn't be a string, but it almost certainly shouldn't reference
    // another map directly.
    String destination;

    public Portal(int id, String destination)
    {
        super(id);
        this.destination = destination;
    }

}
