package com.cerealkillers.rootrunner.scene;

import org.andengine.entity.scene.Scene;

/**
 * Created by Benjamin Daschel on 5/25/15.
 */
public class SceneListener {

    private SceneListener(){};

    public interface SceneChangeListener<T extends Scene>{
        public void onSceneLoaded(T scene);
        public void onSceneUnloaded(T scene);
    }

}
