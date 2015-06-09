package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by jharshman on 6/8/15.
 */
public class IDS extends MapObject {

    private int detectionRadius;

    public IDS(int id, Sprite sprite, int radius) {
        super(id, sprite);
        detectionRadius = radius;
    }

    /* hurt player */
    public void hurtPlayer() {

    }

}
