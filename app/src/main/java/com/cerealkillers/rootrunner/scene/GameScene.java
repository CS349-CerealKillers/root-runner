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
    private DigitalOnScreenControl digitalOnScreenControl;

    @Override
    public void createScene() {
//        createHUD();
        onMapLoad();
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

    @Override
    public void loadResources() {
        resourceManager.loadGameResources();
    }

    @Override
    public void unloadResources() {
        resourceManager.unloadGameTextures();
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



    /* Bound Camera to TMX Layer */
    public void onMapLoad() {
        boundCamera.setBounds(0, 0, layerWidth, layerHeight);
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

}
