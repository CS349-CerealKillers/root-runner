package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.PlayerContainer;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by jharshman on 6/8/15.
 */
public class IDS extends MapObject {

    private int detectionRadius;
    private PlayerContainer pContainer = PlayerContainer.getInstance();

    public IDS(int id, Sprite sprite, int radius) {
        super(id, sprite);
        detectionRadius = radius;
    }

    public int getDetectionRadius() {
        return detectionRadius;
    }

    /* hurt player on collision */
    @Override
    public void onCollide(MapObject collidedWith) {
        pContainer.updatePlayerStealth(pContainer.getPlayerStealth() - 10);
    }

}
