package com.cerealkillers.rootrunner;

import android.content.Context;
import android.content.res.AssetManager;

import org.andengine.engine.Engine;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by ben on 5/10/15.
 */
public class MapLoaderFactory {

    public static MapLoader getMapLoader(Context context, Engine engine){
        AssetManager assets = context.getAssets();
        TextureManager textureManager = engine.getTextureManager();
        VertexBufferObjectManager bufferObjectManager = engine.getVertexBufferObjectManager();
        TMXLoader loader = new TMXLoader(assets, textureManager, bufferObjectManager);
        return new MapLoader(loader, bufferObjectManager);
    }
}
