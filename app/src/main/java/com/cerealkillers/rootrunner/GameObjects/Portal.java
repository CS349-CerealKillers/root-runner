package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/30/15.
 */
public class Portal extends MapObject {

    private String mDestination;

    public Portal(int id, Sprite sprite) {
        super(id, sprite);
    }

    void setDestination(String destinationMap){
        mDestination = destinationMap;
    }
}
