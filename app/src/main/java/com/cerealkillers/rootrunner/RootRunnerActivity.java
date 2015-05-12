package com.cerealkillers.rootrunner;

import android.app.Activity;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by ben on 5/10/15.
 */
public class RootRunnerActivity extends BaseGameActivity {
    

    @Override
    public EngineOptions onCreateEngineOptions() {
        //TODO
        return null;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback onCreateResourcesCallback) throws Exception {
        //TODO
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback onCreateSceneCallback) throws Exception {
        final Scene scene = new Scene();
        MapLoader mapLoader = MapLoaderFactory.getMapLoader(this, mEngine);
        mapLoader.load("map", scene);
        onCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws Exception {
        //TODO
    }
}
