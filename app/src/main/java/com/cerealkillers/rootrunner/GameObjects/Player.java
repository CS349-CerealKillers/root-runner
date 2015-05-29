package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class Player extends MapObject {

    private IUpdateHandler mUpdateHandler;

    public Player(int id, Sprite sprite) {
        super(id, sprite);
    }

    @Override
    public void onAttachToMap(Map attached) {
        super.onAttachToMap(attached);
        mUpdateHandler = new MapObjectTouchDetector(this, getAttachedMap());
        getSprite().registerUpdateHandler(mUpdateHandler);
    }

}
