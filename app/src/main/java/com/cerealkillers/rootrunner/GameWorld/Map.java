package com.cerealkillers.rootrunner.GameWorld;

import android.util.Log;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.GameObjects.MapObjectCollisionDetector;
import com.cerealkillers.rootrunner.scene.BaseScene;

import org.andengine.entity.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 5/6/15.
 */
public class Map {

    private BaseScene mScene;
    private ArrayList<MapObject> mMapObjects;
    private MapObjectCollisionDetector.CollisionDetectedListener mCollisionDetectedListener;

    public Map(BaseScene scene){
        mScene = scene;
        mCollisionDetectedListener = new MapCollisionDetectedListener();
        mMapObjects = new ArrayList<>();
    }

    /**
     * Place a map object on the map for use in game play.
     * @param m MapObject to be added
     */
    public void addMapObject(MapObject m){
        if(m != null){
            mMapObjects.add(m);
            mScene.attachChild(m.getSprite());
            m.onAttachToMap(this);
        }
    }

    public void removeMapObject(MapObject m){
        if(m != null){
            mMapObjects.remove(m);
            m.onDetachedFromMap();
        }
    }

    /**
     *
     * @param objectInQuestion
     * @param touchingResults Objects which are determined to be touching the object in question
     * @return true if object in question is touching any other map objects
     */
    public boolean isMapObjectColliding(MapObject objectInQuestion, List<MapObject> touchingResults){
        if(objectInQuestion == null){
            return false;
        }
        boolean touching = false;
        for(MapObject m: mMapObjects){
            if(m.isColliding(objectInQuestion)){
                touchingResults.add(m);
                touching = true;
            }
        }
        return touching;
    }

    private class MapCollisionDetectedListener implements MapObjectCollisionDetector.CollisionDetectedListener{

        @Override
        public void onCollisionDetected(MapObject subject, List<MapObject> touching) {
            //TODO: figure out what to do based on the touched objects
            //TODO: should be generic, no switch, typecasting or other horsefuckery
            //TODO: keep in mind this happens on the rendering thread in all likelyhood
            Log.d("MAP", "detected player collision");
        }
    }

}
