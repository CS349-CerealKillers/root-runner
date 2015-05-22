package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 *
 * Implements singleton pattern :)
 */


import android.content.res.AssetManager;
import android.graphics.Color;

import org.andengine.engine.Engine;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.util.debug.Debug;
import org.andengine.opengl.font.Font;

import android.content.Context;

public class ResourceManager {

    /* Variables */
    private static final ResourceManager INSTANCE = new ResourceManager();

    /* Assorted */
    public Engine engine;
    public VertexBufferObjectManager vertexBufferObjectManager;
    public Font font;

    /* Textures and Regions */
    public ITextureRegion splashTextureRegion;
    private BitmapTextureAtlas splashTextureAtlas;
    public ITextureRegion menuBackgroundRegion;
    public ITextureRegion playRegion;
    public ITextureRegion optionRegion;
    private BuildableBitmapTextureAtlas menuTextureAtlas;
    private Context myContext;
    private BitmapTextureAtlas playerBitmapTextureAtlas;
    public TiledTextureRegion playerTiledTextureRegion;
    public BitmapTextureAtlas onScreenControlTexture;
    public ITextureRegion onScreenControlBaseRegion;
    public ITextureRegion onScreenControlKnobRegion;

    public TMXTiledMap tmxTiledMap;
    private AssetManager assetManager;

    /* Logic */
    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuFonts();
        //loadMenuAudio();
    }

    public void loadGameResources() {
        loadGameGraphics();
        //loadGameFonts();
        //loadGameAudio();
    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, assetManager, "i_love_8_bit.png", 0, 0);
        splashTextureAtlas.load();
    }

    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }

    public void unloadMenuTextures() {
        menuTextureAtlas.unload();
    }

    public void unloadGameTextures() {
        //todo
    }

    public void loadMenuTextures() {
        menuTextureAtlas.load();
    }

    private void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        menuBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, assetManager, "menu_background.png");
        playRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, assetManager, "menu_ok.png");
        optionRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, assetManager, "menu_back.png");

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
        //load tmx
        try {
            final TMXLoader tmxLoader = new TMXLoader(myContext.getAssets(), engine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, vertexBufferObjectManager);
            this.tmxTiledMap = tmxLoader.loadFromAsset("tmx/desert.tmx");
        } catch (TMXLoadException e) {
            Debug.e(e);
        }

        // load player
        playerBitmapTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 72, 128, TextureOptions.DEFAULT);
        playerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(playerBitmapTextureAtlas, assetManager, "player.png", 0, 0, 3, 4);
        playerBitmapTextureAtlas.load();

        // load digital on screen control
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        onScreenControlTexture = new BitmapTextureAtlas(engine.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
        onScreenControlBaseRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(onScreenControlTexture, assetManager, "onscreen_control_base.png", 0, 0);
        onScreenControlKnobRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(onScreenControlTexture, assetManager, "onscreen_control_knob.png", 128, 0);
        onScreenControlTexture.load();

        //todo load other game graphics
    }
    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    private void loadMenuFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(engine.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        font = FontFactory.createStrokeFromAsset(engine.getFontManager(), mainFontTexture, assetManager, "LCD.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    /* stub out resource loaders */

    /* prep manager */
    public static void prepareManager(Engine engine, Context context) {
        getInstance().engine = engine;
        getInstance().assetManager = context.getAssets();
        getInstance().vertexBufferObjectManager = engine.getVertexBufferObjectManager();
        getInstance().myContext = context;
    }
    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    /* Gets and Sets */
    public float getPlayerTextureWidth() {
        return playerTiledTextureRegion.getWidth();
    }

    public float getPlayerTextureHeight() {
        return playerTiledTextureRegion.getHeight();
    }

}
