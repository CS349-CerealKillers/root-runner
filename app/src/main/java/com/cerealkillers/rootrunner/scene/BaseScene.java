package com.cerealkillers.rootrunner.scene;

/**
 * Created by jharshman on 5/13/15.
 * TODO: remove references to resource manager directly.
 */

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.ResourceManager;
import org.andengine.engine.camera.Camera;

public abstract class BaseScene extends Scene {

    /* variables */
    protected ResourceManager resourceManager;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected BoundCamera boundCamera;
    protected final int DEFAULT = 100;
    protected float layerHeight;
    protected float layerWidth;

    /* constructor */
    protected BaseScene() {
//        this.resourceManager = ResourceManager.getInstance();
    }


    /* abstract methods */
    public abstract void createScene();

    public abstract void disposeScene();

    public abstract void loadResources();

    public abstract void unloadResources();

    /* Getters and Setters */
    public void setLayerHeight(float layerHeight) {
        this.layerHeight = (layerHeight >= 0)?layerHeight:DEFAULT;
    }

    public void setLayerWidth(float layerWidth) {
        this.layerWidth = (layerWidth >= 0)?layerWidth:DEFAULT;
    }

    public void setCameraChaseObject(Sprite sprite){
        if (sprite != null){
            boundCamera.setChaseEntity(sprite);
        }
    }
}
