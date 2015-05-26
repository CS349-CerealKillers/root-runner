package com.cerealkillers.rootrunner;

/**
 * Written By Josh Harshman
 * 5/4/2015
 *
 * */

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.engine.handler.timer.TimerHandler;

import android.graphics.Point;
import android.view.Display;
import android.view.KeyEvent;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    private Game mGame;

    /**
     * onCreateEngine
     * @return Engine
     *
     * Description:
     *              Return new Engine
     * */
    @Override
    public Engine onCreateEngine(EngineOptions mEngineOptions) {
        Engine engine =  new LimitedFPSEngine(mEngineOptions, 60);
        mGame = new Game(this, engine);
        return engine;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
            //TODO: dispatch back key pressed event to scene manager
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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        BoundCamera boundChaseCamera = new BoundCamera(0, 0, size.x, size.y);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(size.x, size.y), boundChaseCamera);
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
        //TODO: delegate initial resource loading to resource manager through GAME facade
        onCreateResourcesCallback.onCreateResourcesFinished();

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
        //TODO: delegate scene creation to scene manager through Game facade
        /* create splash screen */
//        SceneManager.getInstance().createSplashScene(onCreateSceneCallback);
        onCreateSceneCallback.onCreateSceneFinished(mGame.getInitialScene());

    }

    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws IOException {
        //TODO: delegate scene population to scene manager though Game facade, maybe. This might not even be necessary.
        onPopulateSceneCallback.onPopulateSceneFinished();

    }


}
