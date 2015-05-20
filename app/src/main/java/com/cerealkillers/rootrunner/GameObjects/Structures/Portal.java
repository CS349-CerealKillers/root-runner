package com.cerealkillers.rootrunner.GameObjects.Structures;

import com.cerealkillers.rootrunner.GameObjects.GameObject;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public class Portal extends GameObject
{
    Map destination;

    public Portal(int id, Map destination)
    {
        super(id);
        this.destination = destination;
    }

}
