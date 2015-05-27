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
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.engine.handler.timer.TimerHandler;

import android.graphics.Point;
import android.view.Display;
import android.view.KeyEvent;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    private Game mGame;

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
     * onCreateEngine
     * @return Engine
     *
     * Description:
     *              Return new Engine
     * */
    @Override
    public Engine onCreateEngine(EngineOptions mEngineOptions) {
        return  new LimitedFPSEngine(mEngineOptions, 60);
    }

    @Override
    protected synchronized void onCreateGame() {
        mGame = new Game(this, this.mEngine);
        mGame.start();
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        //not used, handled by game
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        //not used, handled by game
    }

    @Override
    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws Exception {
        //not used handled by Game
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
            //TODO: dispatch back key pressed event to scene manager
        }
        return false;
    }

}
