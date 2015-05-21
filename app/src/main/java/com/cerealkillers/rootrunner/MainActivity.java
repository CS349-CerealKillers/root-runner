package com.cerealkillers.rootrunner;

/**
 * Written By Josh Harshman
 * 5/4/2015
 *
 * */

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.engine.handler.timer.TimerHandler;

import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.view.KeyEvent;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    // Camera height and width values
    public static int CAMERA_WIDTH = 800;
    public static int CAMERA_HEIGHT = 480;

    // Define variables needed for the scene
    private BoundCamera mBoundChaseCamera;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mPlayerTextureRegion;
    private TMXTiledMap mTMXTiledMap;
    private Scene mScene;

    // Define variables needed for digital on screen control
    private DigitalOnScreenControl mDigitalOnScreenControl;
    private BitmapTextureAtlas mOnScreenControlTexture;
    private ITextureRegion mOnScreenControlBaseTextureRegion;
    private ITextureRegion mOnScreenControlKnobTextureRegion;

    private ResourceManager mResourceManager;

    /**
     * onCreateEngine
     * @return Engine
     *
     * Description:
     *              Return new Engine
     * */
    @Override
    public Engine onCreateEngine(EngineOptions mEngineOptions) {
        return new LimitedFPSEngine(mEngineOptions, 60);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }
        return false;
    }

     /**
     * onCreateEngineOptions
     * @return EngineOptions
     *  TODO: Let's make the camera width/ height match the screen of the device instead of fixed.
     * Description:
     *              Set global game engine options.
     * */
    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mBoundChaseCamera);
    }

    /**
     * onCreateResources
     *
     * Description:
     *              Loads all requested resources from assets folder.
     *              Assets loaded here exclude the TMX map as that is part of the scene and is handled in a different method call.
     * */
    @Override
    public void onCreateResources(OnCreateResourcesCallback onCreateResourcesCallback) throws IOException {

        // delegate to resource manager
        ResourceManager.prepareManager(mEngine, this);
        mResourceManager = mResourceManager.getInstance();
        onCreateResourcesCallback.onCreateResourcesFinished();
        // end delegation to Resource manager

    }

    /**
     * onCreateScene
     * @return Scene
     *
     * Description:
     *              Calling function for init* routines pertaining to the current scene.
     * */
    @Override
    public void onCreateScene(OnCreateSceneCallback onCreateSceneCallback) throws IOException {

        /* create splash screen */
        SceneManager.getInstance().createSplashScene(onCreateSceneCallback);

    }

    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws IOException {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler timeHandler) {
                mEngine.unregisterUpdateHandler(timeHandler);
                SceneManager.getInstance().createMenuScene();
                //load shit here
            }
        }));
        onPopulateSceneCallback.onPopulateSceneFinished();

    }


}
