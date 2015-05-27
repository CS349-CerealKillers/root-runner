package com.cerealkillers.rootrunner.scene;

import com.cerealkillers.rootrunner.SceneManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;

/**
 * Created by Benjamin Daschel on 5/21/15.
 */
public class SceneFactory {

    public static BaseScene createScene(SceneManager.SceneType type, Engine engine, Camera camera){

        BaseScene scene = null;
        switch (type){
            case GAME:
                scene = new GameScene();
                break;
            case LOAD:
                scene = new LoadingScene();
                break;
            case MENU:
                scene = new MainMenuScene();
                break;
            case SPLASH:
                scene = new SplashScene();
                break;
        }

        engine.getCamera();
        scene.boundCamera = camera;
        scene.vertexBufferObjectManager = engine.getVertexBufferObjectManager();
        scene.createScene();

        return scene;
    }

}
