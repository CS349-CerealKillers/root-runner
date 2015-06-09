package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.ResourceManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Benjamin Daschel on 6/8/15.
 */
public class SpriteFactory {

    private final ResourceManager mResourceManager;
    private VertexBufferObjectManager mVertexBufferObjectManager;

    public SpriteFactory(VertexBufferObjectManager vertexBufferObjectManager, ResourceManager resourceManager){
        mVertexBufferObjectManager = vertexBufferObjectManager;
        mResourceManager = resourceManager;
    }

    public Sprite createSprite(String spriteName){
        TextureRegion textureRegion = mResourceManager.makeTextureRegion(spriteName);
        return new Sprite(0, 0, textureRegion, mVertexBufferObjectManager);
    }
}
