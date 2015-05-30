package com.cerealkillers.rootrunner;

import android.content.Context;

import com.cerealkillers.rootrunner.GameWorld.MapLoader;
import com.cerealkillers.rootrunner.GameWorld.MapLoaderFactory;
import com.cerealkillers.rootrunner.GameWorld.World;
import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.SceneFactory;
import com.cerealkillers.rootrunner.scene.SceneListener;

import org.andengine.engine.Engine;

/**
 * Created by Benjamin Daschel on 5/25/15.
 */
public class Game {

    private final Engine mEngine;
    private MapLoader mMapLoader;
    private SceneManager mSceneManager;
    private ResourceManager mResourceManager;
    private World mWorld;
    private SceneListener.SceneChangeListener<BaseScene> mGameSceneSceneChangeListener;

    public Game(Context context, Engine engine){
        mMapLoader = MapLoaderFactory.getMapLoader(context, engine);
        mGameSceneSceneChangeListener = new GameSceneListener();
        mEngine = engine;
        createResourceManager(context);
        createSceneManager();
    }

    private class GameSceneListener implements SceneListener.SceneChangeListener<BaseScene>{

        @Override
        public void onSceneLoaded(BaseScene scene) {
            //TODO: Don't really want to create a new world every time the scene changes
            mWorld = new World(mMapLoader, mSceneManager);
            mWorld.initialize();
        }

        @Override
        public void onSceneUnloaded(BaseScene scene) {
            // TODO save the game state in preparation for shutdown
        }
    }

    private void createResourceManager(Context context){
        mResourceManager = new ResourceManager(mEngine.getTextureManager(),
                context.getAssets(), mEngine.getFontManager());
    }

    private void createSceneManager(){
        SceneFactory sceneFactory = new SceneFactory(mResourceManager);
        mSceneManager = new SceneManager(mEngine, sceneFactory);
        mSceneManager.registerSceneChangeListener(mGameSceneSceneChangeListener);
    }

    public void start() {
        mSceneManager.loadGameScene();
        mEngine.start();
    }
}
