package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 */

import android.transition.Scene;

import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.GameScene;
import com.cerealkillers.rootrunner.scene.LoadingScene;
import com.cerealkillers.rootrunner.scene.MainMenuScene;
import com.cerealkillers.rootrunner.scene.SceneFactory;
import com.cerealkillers.rootrunner.scene.SceneListener;
import com.cerealkillers.rootrunner.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.engine.handler.timer.TimerHandler;

import java.util.ArrayList;

public class SceneManager {

    /* Different Scenes */
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadScene;

    /* variables */
    private SceneType currentSceneType = SceneType.SPLASH;
    private BaseScene currentScene;
    private Engine mEngine;
    private ResourceManager mResourceManager;
    private SceneFactory mSceneFactory;

    private ArrayList<SceneListener.SceneChangeListener<GameScene>> mGameSceneListeners;

    public SceneManager(Engine engine, ResourceManager resourceManager, SceneFactory sceneFactory) {
        mGameSceneListeners = new ArrayList<>();
        mEngine = engine;
        mResourceManager = resourceManager;
        mSceneFactory = sceneFactory;
    }

    public enum SceneType {
        SPLASH,
        MENU,
        GAME,
        LOAD
    }

    /* class logic */
    public void setScene(BaseScene scene) {
        mEngine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public void setScene(SceneType sceneType) {

        switch (sceneType) {
            case SPLASH:
                setScene(splashScene);
                break;
            case MENU:
                setScene(menuScene);
                break;
            case GAME:
                setScene(gameScene);
                break;
            case LOAD:
                setScene(loadScene);
                break;
        }
    }

    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }

    public void createSplashScene() {
        mResourceManager.loadSplashScreen();
        splashScene = mSceneFactory.createScene(SceneType.SPLASH, mEngine, mEngine.getCamera());
        currentScene = splashScene;
    }

    public void createMenuScene() {
        mResourceManager.loadMenuResources();
        menuScene = mSceneFactory.createScene(SceneType.MENU, mEngine, mEngine.getCamera());
        loadScene = mSceneFactory.createScene(SceneType.LOAD, mEngine, mEngine.getCamera());
        //SceneManager.getInstance().setScene(menuScene);
        setScene(menuScene);
        disposeSplashScene();
    }
    private void disposeSplashScene() {
        mResourceManager.unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public void loadGameScene() {
        setScene(loadScene);
        mResourceManager.unloadMenuTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler timerHandler) {
                mEngine.unregisterUpdateHandler(timerHandler);
                mResourceManager.loadGameResources();
                gameScene = mSceneFactory.createScene(SceneType.GAME, mEngine, mEngine.getCamera());
                setScene(gameScene);
                //ugh, casting sucks, will rewrite later to avoid nastiness
                notifyGameSceneListeners((GameScene) gameScene, true);
            }
        }));

    }

    public void loadMenuScene() {
        setScene(loadScene);
        gameScene.disposeScene();
        mResourceManager.unloadGameTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler timerHandler) {
                mEngine.registerUpdateHandler(timerHandler);
                mResourceManager.loadMenuTextures();
                setScene(menuScene);
            }
        }));

    }

    private void notifyGameSceneListeners(GameScene scene, boolean loaded){
        for(SceneListener.SceneChangeListener<GameScene> listener: mGameSceneListeners){
            if(loaded){
                listener.onSceneLoaded(scene);
            }else{
                listener.onSceneUnloaded(scene);
            }
        }
    }

    public void registerGameSceneChangeListener(SceneListener.SceneChangeListener<GameScene> listener){
        mGameSceneListeners.add(listener);
    }

    public void unregisterGameSceneChangeListener(SceneListener.SceneChangeListener<GameScene> listener){
        mGameSceneListeners.remove(listener);
    }

}
