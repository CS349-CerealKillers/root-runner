package com.cerealkillers.rootrunner;

import android.content.Context;

import com.cerealkillers.rootrunner.GameObjects.MapObjectFactory;
import com.cerealkillers.rootrunner.GameObjects.Player;
import com.cerealkillers.rootrunner.GameObjects.SpriteFactory;
import com.cerealkillers.rootrunner.GameWorld.MapLoader;
import com.cerealkillers.rootrunner.GameWorld.MapLoaderFactory;
import com.cerealkillers.rootrunner.GameWorld.World;
import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.SceneFactory;
import com.cerealkillers.rootrunner.scene.SceneListener;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/25/15.
 */
public class Game {

    private static Game sInstance;
    private final Engine mEngine;
    private MapLoader mMapLoader;
    private SceneManager mSceneManager;
    private ResourceManager mResourceManager;
    private World mWorld;
    private SceneListener.SceneChangeListener<BaseScene> mGameSceneSceneChangeListener;
    private Player mPlayer;
    private MapObjectFactory mMapObjectFactory;
    private SpriteFactory mSpriteFactory;

    public Game(Context context, Engine engine){
        mMapLoader = MapLoaderFactory.getMapLoader(context, engine);
        mEngine = engine;
        createResourceManager(context);
        createSceneManager();
        createPlayer();
        createSpriteFactory();
        sInstance = this;
    }

    private void createSpriteFactory() {
        mSpriteFactory = new SpriteFactory(mEngine.getVertexBufferObjectManager(), mResourceManager);
    }

    /**
     * This method should be replaced eventually with a dynamic player selection screen.
     * For now, who cares.
     */
    private void createPlayer() {
        AnimatedSprite playerSprite = new AnimatedSprite(0, 0, mResourceManager.getPlayerTiledTextureRegion(), mEngine.getVertexBufferObjectManager());
        mPlayer = new Player(0, playerSprite);
    }

    private void createResourceManager(Context context){
        mResourceManager = new ResourceManager(mEngine.getTextureManager(),
                context.getAssets(), mEngine.getFontManager());
    }

    private void createSceneManager(){
        SceneFactory sceneFactory = new SceneFactory(mResourceManager);
        mSceneManager = new SceneManager(mEngine, sceneFactory);
        mSceneManager.registerSceneChangeListener(mGameSceneSceneChangeListener);
    }

    public void start() {
        mWorld = new World(mMapLoader, mSceneManager);
        mWorld.setPlayer(mPlayer);
        mWorld.initialize();
    }

    public static SpriteFactory getSpriteFactory(){
        return sInstance.mSpriteFactory;
    }
}
