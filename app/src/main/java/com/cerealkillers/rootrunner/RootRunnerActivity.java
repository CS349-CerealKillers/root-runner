package com.cerealkillers.rootrunner;

import android.graphics.Point;
import android.view.Display;

import com.cerealkillers.rootrunner.GameWorld.MapLoader;
import com.cerealkillers.rootrunner.GameWorld.MapLoaderFactory;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by ben on 5/10/15.
 */
public class RootRunnerActivity extends BaseGameActivity {
    

    @Override
    public EngineOptions onCreateEngineOptions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        BoundCamera boundChaseCamera = new BoundCamera(0, 0, size.x, size.y);

        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(size.x, size.y), boundChaseCamera);
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback onCreateResourcesCallback) throws Exception {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        onCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback onCreateSceneCallback) throws Exception {
        final Scene scene = new Scene();
        MapLoader mapLoader = MapLoaderFactory.getMapLoader(this, mEngine);
        mapLoader.load("map.tmx", scene);
        onCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws Exception {
        //TODO
    }
}
