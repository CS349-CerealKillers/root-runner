package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.scene.GameScene;

/**
 * Created by Benjamin Daschel on 5/25/15.
 * This class is at the root of all the in game models.
 * The world contains maps, which contain items and characters.
 */
public class World {

    private Map mCurrentMap;
    private MapLoader mMapLoader;
    private GameScene mGameScene;
    private boolean initialized;

    public World(MapLoader mapLoader, GameScene scene){
        mMapLoader = mapLoader;
        mGameScene = scene;
    }

    /**
     * Spawns the initial map for the game and populates the scene with game objects.
     * Only needs to be called once per world lifetime.
     */
    public void initialize(){
        synchronized (this){
            if(initialized){
                return;
            }
            initialized = true;
        }

        String initialMap = "map.tmx";
        mCurrentMap = mMapLoader.load(initialMap, mGameScene);

        //call onMapLoaded on the game scene to finish scene setup
    }
}
