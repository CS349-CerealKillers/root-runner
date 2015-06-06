package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/30/15.
 */
public class Portal extends MapObject {

    private String mDestination;

    /**
     * Portal constructor.
     * @param sprite The portal's sprite.
     */
    public Portal(Sprite sprite) {
        super(sprite);
    }

    /**
     * Set the map that the portal will transport to.
     * @param destinationMap The map the portal links too.
     */
    void setDestination(String destinationMap){
        mDestination = destinationMap;
    }
}
