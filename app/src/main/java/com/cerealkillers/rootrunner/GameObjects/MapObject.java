package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class MapObject<S extends Sprite> extends GameObject<S> {

    private Map mAttachedMap;
    private MapObjectCollisionDetector mCollisionDetector;

    public MapObject(int id, S sprite) {
        super(id, sprite);
    }

    /**
     * Called by Map when this object has been added to the map.
     * @param attached
     */
    public void onAttachToMap(Map attached){
        mAttachedMap = attached;
    }

    /**
     * Called after this object is removed from the map.
     * Removes the sprite from the Scene and unregisters any detectors.
     */
    public void onDetachedFromMap(){
        Sprite mySprite = getSprite();
        mySprite.detachSelf();
        mySprite.unregisterUpdateHandler(mCollisionDetector);
    }

    /**
     * Determine if this object is colliding with another MapObject.
     * @param other Map object to check for collision with
     * @return true if this object is colliding with other
     */
    public boolean isColliding(MapObject other){
        return this.getSprite().collidesWith(other.getSprite());
    }

    public Map getAttachedMap() {
        return mAttachedMap;
    }

    public MapObjectCollisionDetector getCollisionDetector() {
        return mCollisionDetector;
    }

    public void setCollisionDetector(MapObjectCollisionDetector collisionDetector) {
        if(collisionDetector != null){
            mCollisionDetector = collisionDetector;
            getSprite().registerUpdateHandler(collisionDetector);
        }

    }
}
