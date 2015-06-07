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
public class MapObject<S extends Sprite> extends GameObject<S> implements Attachable<Map> {

    private Map mAttachedMap;
    private MapObjectCollisionDetector mCollisionDetector;
    private ITouchArea mTouchArea;

    public MapObject(int id, S sprite) {
        super(id, sprite);
    }

    /**
     * Called by Map when this object has been added to the map.
     * @param attached
     */
    public void onAttach(Map attached){
        mAttachedMap = attached;
    }

    /**
     * Called after this object is removed from the map.
     * Removes the sprite from the Scene and unregisters any detectors.
     */
    public void onDetach(){
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


}
