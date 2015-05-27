package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/13/15.
 *
 * Implements singleton pattern :)
 */


import android.content.res.AssetManager;
import android.graphics.Color;

import org.andengine.engine.Engine;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
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

public class ResourceManager {

    /* Assorted */
    public Engine engine;
    public VertexBufferObjectManager vertexBufferObjectManager;
    private Font menuFont;
    private TextureManager mTextureManager;
    private AssetManager mAssetManager;
    private FontManager mFontManager;

    /* Textures and Regions */
    public ITextureRegion splashTextureRegion;
    private BitmapTextureAtlas splashTextureAtlas;
    public ITextureRegion menuBackgroundRegion;
    public ITextureRegion playRegion;
    public ITextureRegion optionRegion;
    private BuildableBitmapTextureAtlas menuTextureAtlas;
    private BitmapTextureAtlas playerBitmapTextureAtlas;
    private TiledTextureRegion playerTiledTextureRegion;
    public BitmapTextureAtlas onScreenControlTexture;
    public ITextureRegion onScreenControlBaseRegion;
    public ITextureRegion onScreenControlKnobRegion;

    public ResourceManager(TextureManager textureManager, AssetManager assetManager, FontManager fontManager) {
        mTextureManager = textureManager;
        mAssetManager = assetManager;
        mFontManager = fontManager;
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    }

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
        splashTextureAtlas = new BitmapTextureAtlas(mTextureManager, 256, 256, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, mAssetManager, "i_love_8_bit.png", 0, 0);
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
        menuTextureAtlas = new BuildableBitmapTextureAtlas(mTextureManager, 1024, 1024, TextureOptions.BILINEAR);
        menuBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mAssetManager, "menu_background.png");
        playRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mAssetManager, "menu_ok.png");
        optionRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mAssetManager, "menu_back.png");

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

        // load player
        playerBitmapTextureAtlas = new BitmapTextureAtlas(mTextureManager, 72, 128, TextureOptions.DEFAULT);
        playerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(playerBitmapTextureAtlas, mAssetManager, "player.png", 0, 0, 3, 4);
        playerBitmapTextureAtlas.load();

        // load digital on screen control
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        onScreenControlTexture = new BitmapTextureAtlas(mTextureManager, 256, 128, TextureOptions.BILINEAR);
        onScreenControlBaseRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(onScreenControlTexture, mAssetManager, "onscreen_control_base.png", 0, 0);
        onScreenControlKnobRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(onScreenControlTexture, mAssetManager, "onscreen_control_knob.png", 128, 0);
        onScreenControlTexture.load();

        //todo load other game graphics
    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    private void loadMenuFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(mTextureManager, 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        menuFont = FontFactory.createStrokeFromAsset(mFontManager, mainFontTexture, mAssetManager, "LCD.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
        getMenuFont().load();
    }
    /* stub out resource loaders */

    /* Gets and Sets */
    public float getPlayerTextureWidth() {
        return getPlayerTiledTextureRegion().getWidth();
    }

    public float getPlayerTextureHeight() {
        return getPlayerTiledTextureRegion().getHeight();
    }

    public Font getMenuFont() {
        if(menuFont == null){
            loadMenuFonts();
        }
        return menuFont;
    }

    public TiledTextureRegion getPlayerTiledTextureRegion() {
        if(playerTiledTextureRegion == null){
            loadGameGraphics();
        }
        return playerTiledTextureRegion;
    }
}
