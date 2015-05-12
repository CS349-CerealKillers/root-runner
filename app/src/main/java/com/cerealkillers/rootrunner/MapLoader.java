package com.cerealkillers.rootrunner;

import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.debug.Debug;

import java.util.ArrayList;

/**
 * Loads a TMX file into a Map used by the game.
 */
public class MapLoader {

    private TMXLoader mTmxLoader;

    public MapLoader(TMXLoader loader){
        mTmxLoader = loader;
    }

    public GameMap load(String mapName, Scene scene){

        TMXTiledMap map = null;
        try {
             map = mTmxLoader.loadFromAsset("tmx/" + mapName);
        } catch (TMXLoadException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<TMXLayer> layers = map.getTMXLayers();

        for(TMXLayer layer: layers){
            scene.attachChild(layer);
        }

        /*
            TODO
            Not sure what to do yet with the objects.  Still need
            to figure out how to convert these into entities.
         */
        ArrayList<TMXObjectGroup> objects = map.getTMXObjectGroups();

        return null;
    }

}
