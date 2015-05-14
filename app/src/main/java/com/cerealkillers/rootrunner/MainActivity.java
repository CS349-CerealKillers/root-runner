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
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;
import android.opengl.GLES20;

public class MainActivity extends SimpleBaseGameActivity {

    /*Define Player Direction*/
    private enum PlayerDirection {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private PlayerDirection playerDirection;


    // Camera height and width values
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;

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

     /**
     * onCreateEngineOptions
     * @return EngineOptions
     *
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
    public void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        // load player from asset
        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 72, 128, TextureOptions.DEFAULT);
        this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "player.png", 0, 0, 3, 4);
        this.mBitmapTextureAtlas.load();

        // load digital on screen control from assets
        this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
        this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_base.png", 0, 0);
        this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_knob.png", 128, 0);
        this.mOnScreenControlTexture.load();

    }

    /**
     * onCreateScene
     * @return Scene
     *
     * Description:
     *              Calling function for init* routines pertaining to the current scene.
     * */
    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        this.mScene = new Scene();
        initMap();
        final AnimatedSprite player = initPlayer();
        final PhysicsHandler physicsHandler = new PhysicsHandler(player);
        player.registerUpdateHandler(physicsHandler);
        initDOSC(player, physicsHandler);
        return mScene;
    }

    /**
     * initMap
     *
     * Description:
     *              Initializes TMX Tiled Map and attaches it to the scene.
     *              Throws a TMXLoadException if Map fails to load.
     * */
    void initMap() {
        try {
            final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, this.getVertexBufferObjectManager(), new TMXLoader.ITMXTilePropertiesListener() {
                @Override
                public void onTMXTileWithPropertiesCreated(final TMXTiledMap pTMXTiledMap, final TMXLayer pTMXLayer, final TMXTile pTMXTile, final TMXProperties<TMXTileProperty> pTMXTileProperties) {
                    //do nothing
                }
            });
            this.mTMXTiledMap = tmxLoader.loadFromAsset("tmx/desert.tmx");

        } catch (final TMXLoadException e) {
            Debug.e(e);
        }

        final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
        this.mScene.attachChild(tmxLayer);

        this.mBoundChaseCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
        this.mBoundChaseCamera.setBoundsEnabled(true);
    }

    /**
     * initPlayer
     * @return: Returns initialized object of type AnimatedSprite to calling function.
     *
     * Description:
     *              Initializes player of type AnimatedSprite and attaches the object to the scene.
     * */
    AnimatedSprite initPlayer() {

        final float centerX = (CAMERA_WIDTH - this.mPlayerTextureRegion.getWidth()) / 2;
        final float centerY = (CAMERA_HEIGHT - this.mPlayerTextureRegion.getHeight()) / 2;

        final AnimatedSprite player = new AnimatedSprite(centerX, centerY, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());
        this.mBoundChaseCamera.setChaseEntity(player);

        mScene.attachChild(player);
        return player;
    }

    /**
     * initDOSC
     * @param player: object of type AnimatedSprite
     * @param physicsHandler: object of type PhysicsHandler
     *
     * Description:
     *              Initializes Digital On Screen Controls for the player.
     *              Controls changes in animation throughout runtime.
     * */
    void initDOSC(final AnimatedSprite player, final PhysicsHandler physicsHandler) {
               this.mDigitalOnScreenControl = new DigitalOnScreenControl(0, CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight(), this.mBoundChaseCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, this.getVertexBufferObjectManager(), new BaseOnScreenControl.IOnScreenControlListener() {
            @Override
            public void onControlChange(BaseOnScreenControl baseOnScreenControl, float v, float v2) {

                if(v2 == 1) {
                    //up
                    if(playerDirection != PlayerDirection.UP) {
                        player.animate(new long[]{100,100,100},6,8,true);
                        playerDirection = PlayerDirection.UP;
                    }
                } else if(v2 == -1) {
                    //down
                    if(playerDirection != PlayerDirection.DOWN) {
                        player.animate(new long[]{100,100,100},0,2,true);
                        playerDirection = PlayerDirection.DOWN;
                    }
                } else if(v == -1) {
                    //left
                    if(playerDirection != PlayerDirection.LEFT) {
                        player.animate(new long[]{100,100,100},9,11,true);
                        playerDirection = PlayerDirection.LEFT;
                    }
                } else if(v == 1) {
                    //right
                    if(playerDirection != PlayerDirection.RIGHT) {
                        player.animate(new long[]{100,100,100},3,5,true);
                        playerDirection = PlayerDirection.RIGHT;
                    }
                } else {
                    if(player.isAnimationRunning()) {
                        player.stopAnimation();
                        playerDirection = PlayerDirection.NONE;
                    }
                }

                physicsHandler.setVelocity(v*100, v2*100);
                adjustToSceneBoundary(player);
            }
        });
	    this.mDigitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.mDigitalOnScreenControl.getControlBase().setAlpha(0.5f);
        this.mDigitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
       	this.mDigitalOnScreenControl.getControlBase().setScale(1.25f);
       	this.mDigitalOnScreenControl.getControlKnob().setScale(1.25f);
       	this.mDigitalOnScreenControl.refreshControlKnobPosition();

        mScene.setChildScene(mDigitalOnScreenControl);

    }

    /**
     * adjustToSceneBoundary
     * @param player: object of type AnimatedSprite
     *
     * Description:
     *              Keeps sprite entity bound by dimensions of the TMX Layer.
     * */
    public void adjustToSceneBoundary(AnimatedSprite player) {

        TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
        int tmxWidth = tmxLayer.getWidth();
        int tmxHeight = tmxLayer.getHeight();

        // adjust X
        if(player.getX() < 0)
            player.setX(0);
        else if(player.getX() + player.getWidth() > tmxWidth)
            player.setX(tmxWidth - player.getWidth());

        // adjust Y
        if(player.getY() < 0)
            player.setY(0);
        else if(player.getY() + player.getHeight() > tmxHeight)
            player.setY(tmxHeight - player.getHeight());


    }

}
