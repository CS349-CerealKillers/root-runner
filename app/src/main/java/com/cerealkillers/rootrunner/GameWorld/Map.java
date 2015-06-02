package com.cerealkillers.rootrunner.GameWorld;

import android.util.Log;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.GameObjects.MapObjectCollisionDetector;
import com.cerealkillers.rootrunner.GameObjects.Player;
import com.cerealkillers.rootrunner.GameObjects.Tag;
import com.cerealkillers.rootrunner.scene.BaseScene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Daschel on 5/6/15.
 */
public class Map implements Player.PlayerSpawnedListener{

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

    /**
     * Remove an object from the map.
     * The objects sprite is also removed from the scene.
     * @param m map object to be removed
     */
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
            //don't check if the object is touching itself
            if(m != objectInQuestion && m.isColliding(objectInQuestion)){
                touchingResults.add(m);
                touching = true;
            }
        }
        return touching;
    }

    @Override
    public void onPlayerSpawned(Player player) {
        this.mScene.setCameraChaseObject(player.getSprite());

        MapObjectCollisionDetector detector = player.getCollisionDetector();
        if(detector != null) {
            detector.registerCollisionListener(mCollisionDetectedListener);
        }
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

    /**
     * Find all map objects matching a tag key.
     * @param key match object tags against
     * @return list of all map objects with tags matching key
     */
    public List<MapObject> findByTag(String key){
        List<MapObject> results = new ArrayList<>();

        for(MapObject mapObject: mMapObjects){
            Tag tag = mapObject.getTag(key);
            if(tag != null){
                results.add(mapObject);
            }
        }

        return results;
    }

    /**
     * Replace the map object in question with another map object.
     * The position of the original object's sprite will be used to determine where the
     * new object will be placed on the map.
     * @param replace object to be replaced
     * @param replaceWith replaces original map object
     */
    public void replaceMapObject(MapObject replace, MapObject replaceWith){
        if(replace != null && replaceWith != null){
            Sprite originalSprite = replace.getSprite();
            Sprite replaceWithSprite = replaceWith.getSprite();

            float x = originalSprite.getX();
            float y = originalSprite.getY();
            replaceWithSprite.setX(x);
            replaceWithSprite.setY(y);

            removeMapObject(replace);
            addMapObject(replaceWith);
        }
    }

}
