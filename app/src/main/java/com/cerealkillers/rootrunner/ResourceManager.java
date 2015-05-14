package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 *
 * Implements singleton pattern :)
 */


import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourceManager {

    /* Variables */
    private static final ResourceManager INSTANCE = new ResourceManager();

    public Engine engine;
    public MainActivity activity;
    public BoundCamera boundCamera;
    public VertexBufferObjectManager vertexBufferObjectManager;

    /* Textures and Regions */
    public ITextureRegion splashTextureRegion;
    private BitmapTextureAtlas splashTextureAtlas;

    /* Logic */
    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
    }
    public void loadGameResources() {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }
    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "i_love_8_bit.png", 0, 0);
        splashTextureAtlas.load();
    }
    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }
    private void loadMenuGraphics() {

    }
    private void loadMenuAudio() {

    }
    private void loadGameGraphics() {

    }
    private void loadGameFonts() {

    }
    private void loadGameAudio() {

    }



    /* stub out resource loaders */

    /* prep manager */
    public static void prepareManager(Engine engine, MainActivity activity, BoundCamera boundCamera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().boundCamera = boundCamera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }
    public static ResourceManager getInstance() {
        return INSTANCE;
    }


}
