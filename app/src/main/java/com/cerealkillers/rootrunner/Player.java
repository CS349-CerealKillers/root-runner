package com.cerealkillers.rootrunner;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jharshman on 5/18/15.
 */
public class Player extends AnimatedSprite {

    /* Constructor */
    public Player(float centerX, float centerY, ITiledTextureRegion playerTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        super(centerX, centerY, playerTextureRegion, vertexBufferObjectManager);
    }

    //todo stub out needed methods for player

    /* abstract methods */
    //abstract void onDie();
    //abstract void initPlayer();
    //abstract void getPlayerType();

}
