package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class MapObject extends GameObject {

    private Map mAttachedMap;

    public MapObject(int id, Sprite sprite) {
        super(id, sprite);
    }

    /**
     * Called by Map when this object has been added to the map.
     * @param attached
     */
    public void onAttachToMap(Map attached){
        mAttachedMap = attached;
    }

    public boolean isTouching(MapObject other){
        return this.getSprite().collidesWith(other.getSprite());
    }

    public Map getAttachedMap() {
        return mAttachedMap;
    }
}
