package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/15/15.
 */

import com.cerealkillers.rootrunner.SceneManager.SceneType;

import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import com.cerealkillers.rootrunner.SceneManager.SceneType;
import org.andengine.entity.scene.background.Background;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.andengine.extension.tmx.TMXLayer;

public class GameScene extends BaseScene {

    /* variables */
    private HUD gameHUD;
    private Text scoreText;
    private int score;

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

        //score text
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
