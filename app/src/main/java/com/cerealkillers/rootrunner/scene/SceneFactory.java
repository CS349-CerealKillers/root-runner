package com.cerealkillers.rootrunner.scene;

import com.cerealkillers.rootrunner.ResourceManager;
import com.cerealkillers.rootrunner.SceneManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;

/**
 * Created by Benjamin Daschel on 5/21/15.
 */
public class SceneFactory {

    private final ResourceManager mResourceManager;

    public SceneFactory(ResourceManager resourceManager){
        mResourceManager = resourceManager;
    }

    public BaseScene createScene(Engine engine, Camera camera, SceneManager.SceneType type){

        BaseScene scene = null;
        switch (type){
            case GAME:
                scene = new GameScene();
                break;
            case LOAD:
//                scene = new LoadingScene();
                break;
            case MENU:
//                scene = new MainMenuScene();
                break;
            case SPLASH:
//                scene = new SplashScene();
                break;
        }


        scene.resourceManager = mResourceManager;
        //TODO: Redo this to avoid casting. Camera will have to be passed down from main activity where it was created.
        scene.boundCamera = (BoundCamera)camera;
        scene.vertexBufferObjectManager = engine.getVertexBufferObjectManager();

        return scene;
    }

}
