package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.SceneManager;
import com.cerealkillers.rootrunner.scene.BaseScene;

/**
 * Created by Benjamin Daschel on 5/25/15.
 * This class is at the root of all the in game models.
 * The world contains maps, which contain items and characters.
 */
public class World {

    private final SceneManager mSceneManager;
    private Map mCurrentMap;
    private MapLoader mMapLoader;
    private boolean initialized;

    public World(MapLoader mapLoader, SceneManager sceneManager){
        mMapLoader = mapLoader;
        mSceneManager = sceneManager;
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

        /*
            TODO: in the future, this should load up the last saved level that the player
            was at. Right now, we will always start the game from the beginning.
         */
        String initialMap = "newtmx.tmx";
        BaseScene gameScene = mSceneManager.getCurrentScene();
        mCurrentMap = mMapLoader.load(initialMap, gameScene);

        //call onMapLoaded on the game scene to finish scene setup
    }
}
