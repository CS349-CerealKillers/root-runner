package com.cerealkillers.rootrunner.listeners;

import com.cerealkillers.rootrunner.GameObjects.MapObject;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by Benjamin Daschel on 6/6/15.
 */
public class MapObjectTouchDetector implements ITouchArea{

    private MapObjectTouchListener mListener;

    public interface MapObjectTouchListener{
        public void onMapObjectTouched(MapObject mapObject);
    }

    private MapObject mMapObject;

    public MapObjectTouchDetector(MapObject mapObject) {
        mMapObject = mapObject;
    }

    @Override
    public boolean contains(float pX, float pY) {
        return mMapObject.getSprite().contains(pX, pY);
    }

    @Override
    public float[] convertSceneToLocalCoordinates(float pX, float pY) {
        return mMapObject.getSprite().convertSceneToLocalCoordinates(pX, pY);
    }

    @Override
    public float[] convertLocalToSceneCoordinates(float pX, float pY) {
        return mMapObject.getSprite().convertLocalToSceneCoordinates(pX, pY);
    }

    @Override
    public boolean onAreaTouched(TouchEvent touchEvent, float v, float v1) {
        if(touchEvent.isActionUp()){
            if(mListener != null){
                mListener.onMapObjectTouched(mMapObject);
            }
        }
        return true;
    }

    public void setOnMapObjectTouchListener(MapObjectTouchListener listener){
        if(listener != null){
            mListener = listener;
        }
    }


}
