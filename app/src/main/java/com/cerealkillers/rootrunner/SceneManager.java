package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 */

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
public class SceneManager {

    /* Different Scenes */
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadScene;

    /* variables */
    private static final SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SPLASH;
    private BaseScene currentScene;
    private Engine engine = ResourceManager.getInstance().engine;

    public enum SceneType {
        SPLASH,
        MENU,
        GAME,
        LOAD
    }

    /* class logic */
    public void setScene(BaseScene scene) {
        engine.setScene(scene);
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

    public static SceneManager getInstance() {
        return INSTANCE;
    }
    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
    public BaseScene getCurrentScene() {
        return currentScene;
    }
    public void createSplashScene(OnCreateSceneCallback onCreateSceneCallback) {
        ResourceManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        onCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    private void disposedSplashScene() {
        ResourceManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
}
