package com.cerealkillers.rootrunner;

import android.content.Context;

import com.cerealkillers.rootrunner.GameWorld.MapLoader;
import com.cerealkillers.rootrunner.GameWorld.MapLoaderFactory;
import com.cerealkillers.rootrunner.GameWorld.World;
import com.cerealkillers.rootrunner.scene.GameScene;
import com.cerealkillers.rootrunner.scene.SceneListener;

import org.andengine.engine.Engine;

/**
 * Created by Benjamin Daschel on 5/25/15.
 */
public class Game {

    private final MapLoader mMapLoader;
    private World mWorld;
    private SceneListener.SceneChangeListener<GameScene> mGameSceneSceneChangeListener;

    public Game(Context context, Engine engine){
        mMapLoader = MapLoaderFactory.getMapLoader(context, engine);
    }

    private class GameSceneListener implements SceneListener.SceneChangeListener<GameScene>{

        @Override
        public void onSceneLoaded(GameScene scene) {
            mWorld = new World(mMapLoader, scene);
            mWorld.initialize();
        }

        @Override
        public void onSceneUnloaded(GameScene scene) {
            // TODO save the game state in preparation for shutdown
        }
    }
}
