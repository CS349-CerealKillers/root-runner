package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class MapObject<S extends Sprite> extends GameObject<S> {

    private Map mAttachedMap;
    private MapObjectCollisionDetector mCollisionDetector;
    private ITouchArea mTouchArea;

    public MapObject(int id, S sprite) {
        super(id, sprite);
        createTouchArea(sprite);
    }

    private void createTouchArea(S sprite) {
        mTouchArea = new MapObjectTouchArea(sprite);
    }

    /**
     * Called by Map when this object has been added to the map.
     * @param attached
     */
    public void onAttachToMap(Map attached){
        mAttachedMap = attached;
        attached.registerTouchArea(mTouchArea);
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

    public void onCollide(MapObject collidedWith){
        mAttachedMap.removeMapObject(this);
    }

    private static class MapObjectTouchArea implements ITouchArea{

        private Sprite mSprite;

        public MapObjectTouchArea(Sprite sprite) {
            mSprite = sprite;
        }

        @Override
        public boolean contains(float pX, float pY) {
            return mSprite.contains(pX, pY);
        }

        @Override
        public float[] convertSceneToLocalCoordinates(float pX, float pY) {
            return mSprite.convertSceneToLocalCoordinates(pX, pY);
        }

        @Override
        public float[] convertLocalToSceneCoordinates(float pX, float pY) {
            return mSprite.convertLocalToSceneCoordinates(pX, pY);
        }

        @Override
        public boolean onAreaTouched(TouchEvent touchEvent, float v, float v1) {
            if(touchEvent.isActionUp()){
                Log.d("MapObject", "thing was touched");
            }
            return false;
        }
    }
}
