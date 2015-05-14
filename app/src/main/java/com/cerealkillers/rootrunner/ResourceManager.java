package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 *
 * Implements singleton pattern :)
 */

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourceManager {

    /* Variables */
    private static final ResourceManager INSTANCE = new ResourceManager();

    public Engine engine;
    public MainActivity activity;
    public BoundCamera boundCamera;
    public VertexBufferObjectManager vertexBufferObjectManager;

    /* Textures and Regions */

    /* Logic */
    public void loadGameResources() {

    }



    /* stub out resource loaders */

    /* prep manager */
    public static void prepareManager(Engine engine, MainActivity activity, BoundCamera boundCamera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().boundCamera = boundCamera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }
    public static ResourceManager getInstance() {
        return INSTANCE;
    }


}
