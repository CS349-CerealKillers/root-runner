package com.cerealkillers.rootrunner;

import android.content.Context;

import com.cerealkillers.rootrunner.GameWorld.MapLoader;
import com.cerealkillers.rootrunner.GameWorld.MapLoaderFactory;
import com.cerealkillers.rootrunner.GameWorld.World;
import com.cerealkillers.rootrunner.scene.GameScene;
import com.cerealkillers.rootrunner.scene.SceneFactory;
import com.cerealkillers.rootrunner.scene.SceneListener;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

/**
 * Created by Benjamin Daschel on 5/25/15.
 */
public class Game {

    private final Engine mEngine;
    private MapLoader mMapLoader;
    private SceneManager mSceneManager;
    private ResourceManager mResourceManager;
    private World mWorld;
    private SceneListener.SceneChangeListener<GameScene> mGameSceneSceneChangeListener;

    public Game(Context context, Engine engine){
        mMapLoader = MapLoaderFactory.getMapLoader(context, engine);
        mGameSceneSceneChangeListener = new GameSceneListener();
        mEngine = engine;
        createResourceManager(context);
        createSceneManager();
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

    private void createResourceManager(Context context){
        mResourceManager = new ResourceManager(mEngine.getTextureManager(),
                context.getAssets(), mEngine.getFontManager());
    }

    private void createSceneManager(){
        SceneFactory sceneFactory = new SceneFactory(mResourceManager);
        mSceneManager = new SceneManager(mEngine, mResourceManager, sceneFactory);
        mSceneManager.registerGameSceneChangeListener(mGameSceneSceneChangeListener);
    }

    public void start() {
        mSceneManager.loadGameScene();
    }
}
