package com.cerealkillers.rootrunner;

/**
 * Written By Josh Harshman
 * 5/4/2015
 * */


import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
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
import org.andengine.util.Constants;
import org.andengine.util.debug.Debug;
import android.opengl.GLES20;


public class MainActivity extends SimpleBaseGameActivity {

    private enum PlayerDirection {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private PlayerDirection playerDirection = PlayerDirection.DOWN;

    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;

    private BoundCamera mBoundChaseCamera;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mPlayerTextureRegion;
    private TMXTiledMap mTMXTiledMap;

    // digital on screen control
    private DigitalOnScreenControl mDigitalOnScreenControl;
    private BitmapTextureAtlas mOnScreenControlTexture;
    private ITextureRegion mOnScreenControlBaseTextureRegion;
    private ITextureRegion mOnScreenControlKnobTextureRegion;


    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mBoundChaseCamera);
    }

    @Override
    public void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        // load player from asset
        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 72, 128, TextureOptions.DEFAULT);
        this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "player.png", 0, 0, 3, 4);
        this.mBitmapTextureAtlas.load();

        // digital on screen control
        this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
        this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_base.png", 0, 0);
        this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_knob.png", 128, 0);
        this.mOnScreenControlTexture.load();

    }

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();

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
        scene.attachChild(tmxLayer);


		/* Make the camera not exceed the bounds of the TMXEntity. */
        this.mBoundChaseCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
        this.mBoundChaseCamera.setBoundsEnabled(true);

		/* Calculate the coordinates for the face, so its centered on the camera. */
        final float centerX = (CAMERA_WIDTH - this.mPlayerTextureRegion.getWidth()) / 2;
        final float centerY = (CAMERA_HEIGHT - this.mPlayerTextureRegion.getHeight()) / 2;

		/* Create the sprite and add it to the scene. */
        final AnimatedSprite player = new AnimatedSprite(centerX, centerY, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());
        this.mBoundChaseCamera.setChaseEntity(player);

        // Setup physics handler for sprite
        final PhysicsHandler physicsHandler = new PhysicsHandler(player);
        player.registerUpdateHandler(physicsHandler);


        /*
        final Path path = new Path(5).to(0, 160).to(0, 500).to(600, 500).to(600, 160).to(0, 160);

        player.registerEntityModifier(new LoopEntityModifier(new PathModifier(30, path, null, new IPathModifierListener() {
            @Override
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

            }

            @Override
            public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
                switch(pWaypointIndex) {
                    case 0:
                        player.animate(new long[]{200, 200, 200}, 6, 8, true);
                        break;
                    case 1:
                        player.animate(new long[]{200, 200, 200}, 3, 5, true);
                        break;
                    case 2:
                        player.animate(new long[]{200, 200, 200}, 0, 2, true);
                        break;
                    case 3:
                        player.animate(new long[]{200, 200, 200}, 9, 11, true);
                        break;
                }
            }

            @Override
            public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {

            }

            @Override
            public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {

            }
        }))); */

        scene.attachChild(player);

        // setup controls
        this.mDigitalOnScreenControl = new DigitalOnScreenControl(0, CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight(), this.mBoundChaseCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, this.getVertexBufferObjectManager(), new BaseOnScreenControl.IOnScreenControlListener() {
            @Override
            public void onControlChange(BaseOnScreenControl baseOnScreenControl, float v, float v2) {

                if(v2 == 1) {
                    //up
                    if(playerDirection != PlayerDirection.UP) {
                        player.animate(new long[]{200,200,200},6,8,true);
                        playerDirection = PlayerDirection.UP;
                    }
                } else if(v2 == -1) {
                    //down
                    if(playerDirection != PlayerDirection.DOWN) {
                        player.animate(new long[]{200,200,200},0,2,true);
                        playerDirection = PlayerDirection.DOWN;
                    }
                } else if(v == -1) {
                    //left
                    if(playerDirection != PlayerDirection.LEFT) {
                        player.animate(new long[]{200,200,200},9,11,true);
                        playerDirection = PlayerDirection.LEFT;
                    }
                } else if(v == 1) {
                    //right
                    if(playerDirection != PlayerDirection.RIGHT) {
                        player.animate(new long[]{200,200,200},3,5,true);
                        playerDirection = PlayerDirection.RIGHT;
                    }
                } else {
                    if(player.isAnimationRunning()) {
                        player.stopAnimation();
                        playerDirection = PlayerDirection.NONE;
                    }
                }

                physicsHandler.setVelocity(v*100, v2*100);
            }
        });
	    this.mDigitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.mDigitalOnScreenControl.getControlBase().setAlpha(0.5f);
        this.mDigitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
       	this.mDigitalOnScreenControl.getControlBase().setScale(1.25f);
       	this.mDigitalOnScreenControl.getControlKnob().setScale(1.25f);
       	this.mDigitalOnScreenControl.refreshControlKnobPosition();

        scene.setChildScene(mDigitalOnScreenControl);

        return scene;
    }


}
