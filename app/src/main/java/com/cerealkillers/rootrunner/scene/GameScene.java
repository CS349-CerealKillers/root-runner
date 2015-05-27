package com.cerealkillers.rootrunner.scene;

/**
 * Created by jharshman on 5/15/15.
 *
 */

import android.opengl.GLES20;

import com.cerealkillers.rootrunner.Player;
import com.cerealkillers.rootrunner.SceneManager.SceneType;

import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.entity.scene.background.Background;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;

public class GameScene extends BaseScene {

    private enum PlayerDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    /* variables */
    private HUD gameHUD;
    private Text healthText;
    private int health;
    private PlayerDirection playerDirection;
    private Player player;
    private DigitalOnScreenControl digitalOnScreenControl;
    private float layerHeight;
    private float layerWidth;

    @Override
    public void createScene() {
        //use simple background placeholder
        //createBackground();
        loadMap();
        createHUD();
        //createPhysics();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAME;
    }

    @Override
    public void disposeScene() {
        boundCamera.setHUD(null);
        boundCamera.setCenter(400,240);

        // code for disposing of scene
        this.detachSelf();
        this.dispose();

        //todo remove all game scene objects
    }

    /* init HUD */
    public void createHUD() {
        gameHUD = new HUD();
        healthText = new Text(20, 420, resourceManager.getMenuFont(), "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        healthText.setText("Score: 0");
        gameHUD.attachChild(healthText);
        boundCamera.setHUD(gameHUD);
    }

    private void createBackground() {
        setBackground(new Background(Color.BLUE));
    }

    private void loadMap() {
//        final TMXLayer tmxLayer = ResourceManager.getInstance().tmxTiledMap.getTMXLayers().get(0);
//        attachChild(tmxLayer); // attach child to scene
//        ResourceManager.getInstance().boundCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
//        ResourceManager.getInstance().boundCamera.setBoundsEnabled(true);

        //initPlayer
        float centerX = (boundCamera.getWidth() - resourceManager.getPlayerTextureWidth()) / 2;
        float centerY = (boundCamera.getHeight() - resourceManager.getPlayerTextureHeight()) / 2;
        player = new Player(centerX, centerY, resourceManager.getPlayerTiledTextureRegion(), vertexBufferObjectManager);
        attachChild(player);

        //load DOSC
        PhysicsHandler physicsHandler = new PhysicsHandler(player);
        player.registerUpdateHandler(physicsHandler);
        initDOSC(player, physicsHandler);

    }

    /* initialize the digital on screen controls */
    public void initDOSC(final Player player, final PhysicsHandler physicsHandler) {
        digitalOnScreenControl = new DigitalOnScreenControl(boundCamera.getWidth() - (resourceManager.onScreenControlBaseRegion.getWidth() + 40),
                boundCamera.getHeight() - resourceManager.onScreenControlBaseRegion.getHeight(),
                boundCamera, resourceManager.onScreenControlBaseRegion, resourceManager.onScreenControlKnobRegion, 0.1f,
                vertexBufferObjectManager, new BaseOnScreenControl.IOnScreenControlListener() {
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
                adjustToSceneBoundary();
            }
        });

        this.digitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.digitalOnScreenControl.getControlBase().setAlpha(0.5f);
        this.digitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
        this.digitalOnScreenControl.getControlBase().setScale(1.25f);
        this.digitalOnScreenControl.getControlKnob().setScale(1.25f);
        this.digitalOnScreenControl.refreshControlKnobPosition();

        setChildScene(digitalOnScreenControl);
    }

    /* Trap player within TMX map boundaries */
    public void adjustToSceneBoundary() {
//        TMXLayer tmxLayer = resourceManager.tmxTiledMap.getTMXLayers().get(0);
//        int tmxWidth = tmxLayer.getWidth();
//        int tmxHeight = tmxLayer.getHeight();

        // adjust X
//        if(player.getX() < 0)
//            player.setX(0);
//        else if(player.getX() + player.getWidth() > tmxWidth)
//            player.setX(tmxWidth - player.getWidth());
//
//        // adjust Y
//        if(player.getY() < 0)
//            player.setY(0);
//        else if(player.getY() + player.getHeight() > tmxHeight)
//            player.setY(tmxHeight - player.getHeight());

    }

    /* Bound Camera to TMX Layer */
    public void onMapLoad() {
        boundCamera.setBounds(0, 0, layerHeight, layerWidth);
        boundCamera.setBoundsEnabled(true);
    }

    /* Health Tracking */
    private void addToHealth(int i) {
        health += i;
        healthText.setText("Health: " + health);
    }
    private void subtractFromHealth(int i) {
        health -= i;
        healthText.setText("Health: " + health);
    }

    /* Getters and Setters */
    public void setLayerHeight(float layerHeight) {
        this.layerHeight = (layerHeight >= 0)?layerHeight:DEFAULT;
    }
    public void setLayerWidth(float layerWidth) {
        this.layerWidth = (layerWidth >= 0)?layerWidth:DEFAULT;
    }

}
