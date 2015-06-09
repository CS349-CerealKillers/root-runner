package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.GameWorld.CommandFacade;

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

    public MapObject(S sprite) {
        super(sprite);
        createTouchArea(sprite);
    }

    public void createTouchArea(S sprite) {
        mTouchArea = new MapObjectTouchArea();
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

    public MapObject clone()
    {
        MapObject theClone;
        Sprite sprite = new Sprite(this.getSprite().getX(), this.getSprite().getY(),
                this.getSprite().getTextureRegion(), this.getSprite().getVertexBufferObjectManager());

        theClone = new MapObject(sprite);
        return theClone;
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
//        mAttachedMap.removeMapObject(this);
//        Tag descriptionTag = getTag("description");
//        String description = null;
//        if(descriptionTag == null || descriptionTag.value == null){
//            description = "No description available";
//        }else{
//            description = descriptionTag.value;
//        }
//        CommandFacade.displayMessage(String.format("%s : %s", getName(), description));
    }

    private class MapObjectTouchArea implements ITouchArea{

        @Override
        public boolean contains(float pX, float pY) {
            return getSprite().contains(pX, pY);
        }

        @Override
        public float[] convertSceneToLocalCoordinates(float pX, float pY) {
            return getSprite().convertSceneToLocalCoordinates(pX, pY);
        }

        @Override
        public float[] convertLocalToSceneCoordinates(float pX, float pY) {
            return getSprite().convertLocalToSceneCoordinates(pX, pY);
        }

        @Override
        public boolean onAreaTouched(TouchEvent touchEvent, float v, float v1) {
            if(touchEvent.isActionUp()){
                Tag description = getTag("description");
                if(description != null && description.value != null){
                    CommandFacade.displayMessage(description.value);
                }
            }
            return false;
        }
    }
}
