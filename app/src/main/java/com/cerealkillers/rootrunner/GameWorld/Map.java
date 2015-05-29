package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.GameObjects.MapObject;

import org.andengine.entity.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 5/6/15.
 */
public class Map {

    private Scene mScene;
    private ArrayList<MapObject> mMapObjects;

    public Map(Scene scene){
        mScene = scene;
    }

    /**
     * Place a map object on the map for use in game play.
     * @param m MapObject to be added
     */
    public void addMapObject(MapObject m){
        mMapObjects.add(m);
        mScene.attachChild(m.getSprite());
        m.onAttachToMap(this);
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
            if(m.isTouching(objectInQuestion)){
                touchingResults.add(m);
                touching = true;
            }
        }
        return touching;
    }

}
