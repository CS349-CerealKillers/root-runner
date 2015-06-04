package com.cerealkillers.rootrunner.GameObjects;
import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.engine.handler.IUpdateHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class MapTouchDetector implements IUpdateHandler
{
    public interface TouchDetectedListener {
        public void onTouchDetected(MapTool mapToolUsed, List<Map> touchResults);
    }

    private final Map mAttachedMap;
    private MapTool mMapToolUsed;
    private List<Map> touchResults; // Should be map coordinates maybe?
    private List<TouchDetectedListener> mTouchDetectedListeners;

    public MapTouchDetector(MapTool mapToolUsed, Map attachedMap) {
        mMapToolUsed = mapToolUsed;
        mAttachedMap = attachedMap;
        touchResults = new ArrayList<>();
        mTouchDetectedListeners = new ArrayList<>();
    }

    /**
     * Register to be notified when a touch is detected
     * @param listener
     */
    public void registerTouchListener(TouchDetectedListener listener){
        if(listener != null && ! mTouchDetectedListeners.contains(listener)){
            mTouchDetectedListeners.add(listener);
        }
    }

    /**
     * Stop listening for touch detections. Touch detection will continue,
     * but the listener will no longer receive any notification.
     * @param listener
     */
    public void unregisterTouchListener(TouchDetectedListener listener){
        if(listener != null){
            mTouchDetectedListeners.remove(listener);
        }
    }

    private void notifyListeners(){
        for (TouchDetectedListener listener: mTouchDetectedListeners){
            listener.onTouchDetected(mMapToolUsed, touchResults);
            touchResults.clear();
        }
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        boolean touching = mAttachedMap.isMapTouched(mMapToolUsed, touchResults);
        if(touching){
            notifyListeners();
        }
    }

    @Override
    public void reset() {

    }
}
