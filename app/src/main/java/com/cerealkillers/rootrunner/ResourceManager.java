package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 *
 * Implements singleton pattern :)
 */


import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.util.debug.Debug;
import org.andengine.engine.camera.Camera;

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

    public ITextureRegion menuBackgroundRegion;
    public ITextureRegion playRegion;
    public ITextureRegion optionRegion;

    private BuildableBitmapTextureAtlas menuTextureAtlas;



    /* Logic */
    public void loadMenuResources() {
        loadMenuGraphics();
        //loadMenuAudio();
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
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        menuBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_background.png");
        playRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_ok.png");
        optionRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_back.png");

        try {
            this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,0));
            this.menuTextureAtlas.load();
        }catch(final TextureAtlasBuilderException e) {
            Debug.e(e);
        }

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
