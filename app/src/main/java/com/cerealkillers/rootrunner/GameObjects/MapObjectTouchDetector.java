package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
class MapObjectTouchDetector implements IUpdateHandler {

    public interface CollisionDetectedListener{
        public void onCollisionDetected(MapObject subject, List<MapObject> touching);
    }

    private final Map mAttachedMap;
    private MapObject mMapObject;
    private List<MapObject> touchResults;
    private List<CollisionDetectedListener> mCollisionDetectedListeners;

    public MapObjectTouchDetector(MapObject mapObject, Map attachedMap) {
        mMapObject = mapObject;
        mAttachedMap = attachedMap;
        touchResults = new ArrayList<>();
        mCollisionDetectedListeners = new ArrayList<>();
    }

    public void registerCollisionListener(CollisionDetectedListener listener){
        if(listener != null && ! mCollisionDetectedListeners.contains(listener)){
            mCollisionDetectedListeners.add(listener);
        }
    }

    public void unregisterCollistionListener(CollisionDetectedListener listener){
        if(listener != null){
            mCollisionDetectedListeners.remove(listener);
        }
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        boolean touching = mAttachedMap.isMapObjectColliding(mMapObject, touchResults);

        //TODO: fire on touching event somewhere??
    }

    @Override
    public void reset() {

    }
}
