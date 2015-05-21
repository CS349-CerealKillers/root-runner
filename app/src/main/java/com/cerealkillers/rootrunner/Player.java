package com.cerealkillers.rootrunner;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jharshman on 5/18/15.
 */
public class Player extends AnimatedSprite {

    /* Variables */
    BoundCamera boundCamera = ResourceManager.getInstance().boundCamera;

    /* Constructor */
    public Player(float centerX, float centerY, ITiledTextureRegion playerTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        super(centerX, centerY, playerTextureRegion, vertexBufferObjectManager);
        this.boundCamera.setChaseEntity(this);
    }

    /*
    //basics on creating an animated sprite:
    final float centerX = (CAMERA_WIDTH - this.mPlayerTextureRegion.getWidth()) / 2;
    final float centerY = (CAMERA_HEIGHT - this.mPlayerTextureRegion.getHeight()) / 2;
     final AnimatedSprite player = new AnimatedSprite(centerX, centerY, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());
        this.mBoundChaseCamera.setChaseEntity(player);
        mScene.attachChild(player);
        return player;
    */

    //todo stub out needed methods for player

    /* abstract methods */
    //abstract void onDie();
    //abstract void initPlayer();
    //abstract void getPlayerType();

}
