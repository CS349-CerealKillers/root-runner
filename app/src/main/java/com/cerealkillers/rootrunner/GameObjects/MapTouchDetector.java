package com.cerealkillers.rootrunner.GameObjects;
import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Herrin on 6/4/2015.
 */
public class MapTouchDetector implements IOnSceneTouchListener
{
    public interface TouchDetectedListener {
        public void onTouchDetected(Map map, float x, float y);
    }

    private final Map mAttachedMap;
    private List<Map> touchResults;
    private List<TouchDetectedListener> mTouchDetectedListeners;
    private float mX, mY;

    public MapTouchDetector(Map attachedMap)
    {
        mAttachedMap = attachedMap;
        touchResults = new ArrayList<>();
        mTouchDetectedListeners = new ArrayList<>();
    }

    @Override
    public boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent)
    {
        if(touchEvent.isActionUp())
        {
            mX = touchEvent.getX();
            mY = touchEvent.getY();
        }
        return false;
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
        for (TouchDetectedListener listener : mTouchDetectedListeners){
            listener.onTouchDetected(mAttachedMap, mX, mY);
            touchResults.clear();
        }
    }
}
