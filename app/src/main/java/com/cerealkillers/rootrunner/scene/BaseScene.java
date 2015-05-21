package com.cerealkillers.rootrunner.scene;

/**
 * Created by jharshman on 5/13/15.
 */

import org.andengine.entity.scene.Scene;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.app.Activity;

import com.cerealkillers.rootrunner.ResourceManager;
import com.cerealkillers.rootrunner.SceneManager.SceneType;
import org.andengine.engine.camera.Camera;

public abstract class BaseScene extends Scene {

    /* variables */
    protected Engine engine;
    protected Activity activity;
    protected ResourceManager resourceManager;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected BoundCamera boundCamera;

    /* constructor */
    public BaseScene() {
        this.resourceManager = ResourceManager.getInstance();
        this.engine = resourceManager.engine;
        this.activity = resourceManager.activity;
        this.vertexBufferObjectManager = resourceManager.vertexBufferObjectManager;
        this.boundCamera = resourceManager.boundCamera;
        createScene();
    }

    /* abstract methods */
    public abstract void createScene();
    public abstract void onBackKeyPressed();
    public abstract SceneType getSceneType();
    public abstract void disposeScene();

}
