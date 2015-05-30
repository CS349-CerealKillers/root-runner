package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 */

import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.SceneFactory;
import com.cerealkillers.rootrunner.scene.SceneListener;

import org.andengine.engine.Engine;

import java.util.ArrayList;

public class SceneManager {

    /* Different Scenes */
    private BaseScene mCurrentScene;

    private Engine mEngine;
    private SceneFactory mSceneFactory;

    private ArrayList<SceneListener.SceneChangeListener<BaseScene>> mSceneChangeListeners;

    public SceneManager(Engine engine, SceneFactory sceneFactory) {
        mSceneChangeListeners = new ArrayList<>();
        mEngine = engine;
        mSceneFactory = sceneFactory;
    }

    public enum SceneType {
        SPLASH,
        MENU,
        GAME,
        LOAD
    }

    private void disposeScene() {
        mCurrentScene.disposeScene();
        mCurrentScene = null;
    }

    public void loadGameScene() {
        mCurrentScene = mSceneFactory.createScene(mEngine, mEngine.getCamera(), SceneType.GAME);
        notifyGameSceneListeners(mCurrentScene, true);
    }

    public BaseScene getCurrentScene(){
        return mCurrentScene;
    }

    private void notifyGameSceneListeners(BaseScene scene, boolean loaded){
        for(SceneListener.SceneChangeListener<BaseScene> listener: mSceneChangeListeners){
            if(loaded){
                listener.onSceneLoaded(scene);
            }else{
                listener.onSceneUnloaded(scene);
            }
        }
    }

    public void registerSceneChangeListener(SceneListener.SceneChangeListener<BaseScene> listener){
        mSceneChangeListeners.add(listener);
    }

    public void unregisterSceneChangeListener(SceneListener.SceneChangeListener<BaseScene> listener){
        mSceneChangeListeners.remove(listener);
    }

}
