package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 */

import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.GameScene;
import com.cerealkillers.rootrunner.scene.LoadingScene;
import com.cerealkillers.rootrunner.scene.MainMenuScene;
import com.cerealkillers.rootrunner.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.engine.handler.timer.TimerHandler;

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

    public void createMenuScene() {
        ResourceManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene();
        loadScene = new LoadingScene();
        //SceneManager.getInstance().setScene(menuScene);
        setScene(menuScene);
        disposeSplashScene();
    }
    private void disposeSplashScene() {
        ResourceManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public void loadGameScene(final Engine engine) {
        setScene(loadScene);
        ResourceManager.getInstance().unloadMenuTextures();
        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler timerHandler) {
                engine.unregisterUpdateHandler(timerHandler);
                ResourceManager.getInstance().loadGameResources();
                gameScene = new GameScene();
                setScene(gameScene);
            }
        }));

    }

    public void loadMenuScene(final Engine engine) {
        setScene(loadScene);
        gameScene.disposeScene();
        ResourceManager.getInstance().unloadGameTextures();
        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler timerHandler) {
                engine.registerUpdateHandler(timerHandler);
                ResourceManager.getInstance().loadMenuTextures();
                setScene(menuScene);
            }
        }));

    }
}
