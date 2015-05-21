package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/15/15.
 */

import android.opengl.GLES20;

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
import org.andengine.extension.tmx.TMXLayer;

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
    private Text scoreText;
    private int score;
    private PlayerDirection playerDirection;
    private Player player;
    private DigitalOnScreenControl digitalOnScreenControl;

    @Override
    public void createScene() {
        //use simple background placeholder
        //createBackground();
        loadMap();
        createHUD();
        //createPhysics();
    }
    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(engine);
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

        scoreText = new Text(20, 420, resourceManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        scoreText.setText("Score: 0");
        gameHUD.attachChild(scoreText);

        boundCamera.setHUD(gameHUD);

    }

    private void createBackground() {
        setBackground(new Background(Color.BLUE));
    }

    private void loadMap() {
        final TMXLayer tmxLayer = ResourceManager.getInstance().tmxTiledMap.getTMXLayers().get(0);
        attachChild(tmxLayer); // attach child to scene
        ResourceManager.getInstance().boundCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
        ResourceManager.getInstance().boundCamera.setBoundsEnabled(true);

        //initPlayer
        float centerX = (MainActivity.CAMERA_WIDTH - resourceManager.getPlayerTextureWidth()) / 2;
        float centerY = (MainActivity.CAMERA_HEIGHT - resourceManager.getPlayerTextureHeight()) / 2;
        player = new Player(centerX, centerY, resourceManager.playerTiledTextureRegion, vertexBufferObjectManager);
        attachChild(player);

        //load DOSC
        PhysicsHandler physicsHandler = new PhysicsHandler(player);
        player.registerUpdateHandler(physicsHandler);
        initDOSC(player, physicsHandler);

    }

    public void initDOSC(final Player player, final PhysicsHandler physicsHandler) {
        digitalOnScreenControl = new DigitalOnScreenControl(MainActivity.CAMERA_WIDTH - (resourceManager.onScreenControlBaseRegion.getWidth() + 40), MainActivity.CAMERA_HEIGHT - resourceManager.onScreenControlBaseRegion.getHeight(), boundCamera, resourceManager.onScreenControlBaseRegion, resourceManager.onScreenControlKnobRegion, 0.1f, vertexBufferObjectManager, new BaseOnScreenControl.IOnScreenControlListener() {
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

        attachChild(digitalOnScreenControl);
    }


    public void adjustToSceneBoundary() {
        TMXLayer tmxLayer = resourceManager.tmxTiledMap.getTMXLayers().get(0);
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

    //keep track of score.  This is an example.  Tweak slightly to keep track of health
    private void addToScore(int i) {
        score += i;
        scoreText.setText("Score: " + score);
    }

    /* create physics */
    private void createPhysics() {
        //need PhysicsWorld Extension
    }


}
