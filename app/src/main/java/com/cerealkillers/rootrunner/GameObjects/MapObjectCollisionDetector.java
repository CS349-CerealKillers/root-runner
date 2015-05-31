package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class MapObjectCollisionDetector implements IUpdateHandler {

    public interface CollisionDetectedListener{
        public void onCollisionDetected(MapObject subject, List<MapObject> touching);
    }

    private final Map mAttachedMap;
    private MapObject mMapObject;
    private List<MapObject> touchResults;
    private List<CollisionDetectedListener> mCollisionDetectedListeners;

    public MapObjectCollisionDetector(MapObject mapObject, Map attachedMap) {
        mMapObject = mapObject;
        mAttachedMap = attachedMap;
        touchResults = new ArrayList<>();
        mCollisionDetectedListeners = new ArrayList<>();
    }

    /**
     * Register to be notified when a collision is detected
     * @param listener
     */
    public void registerCollisionListener(CollisionDetectedListener listener){
        if(listener != null && ! mCollisionDetectedListeners.contains(listener)){
            mCollisionDetectedListeners.add(listener);
        }
    }

    /**
     * Stop listening for collision detections. Collision detection will continue,
     * but the listener will no longer receive any notification.
     * @param listener
     */
    public void unregisterCollisionListener(CollisionDetectedListener listener){
        if(listener != null){
            mCollisionDetectedListeners.remove(listener);
        }
    }

    private void notifyListeners(){
        for (CollisionDetectedListener listener: mCollisionDetectedListeners){
            listener.onCollisionDetected(mMapObject, touchResults);
            touchResults.clear();
        }
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        boolean touching = mAttachedMap.isMapObjectColliding(mMapObject, touchResults);
        if(touching){
            notifyListeners();
        }
    }

    @Override
    public void reset() {

    }
}
