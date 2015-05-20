package com.cerealkillers.rootrunner;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXProperty;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import java.util.ArrayList;

/**
 * Loads a TMX file into a Map used by the game.
 */
public class MapLoader {

    private VertexBufferObjectManager mVertexBuffer;
    private TMXLoader mTmxLoader;

    private static final String TMX_GID  = "gid";

    public MapLoader(TMXLoader loader){
        mTmxLoader = loader;
    }

    public MapLoader(TMXLoader loader, VertexBufferObjectManager bufferObjectManager) {
        mTmxLoader = loader;
        mVertexBuffer = bufferObjectManager;
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


        ArrayList<TMXObjectGroup> objectGroups = map.getTMXObjectGroups();


        /*
            This section creates sprites for each of the "entities" on the map.
            //TODO: also create physics bounds for detecting collisions btwn the player and objects.
         */
        for(TMXObjectGroup group: objectGroups){
            //some group level logic here
            for (TMXObject object: group.getTMXObjects()){
                //load sprites for individual entities

                int gid = object.getGid();
                ITextureRegion texture = map.getTextureRegionFromGlobalTileID(gid);
                Sprite sprite = new Sprite(object.getX(), object.getY(), texture, mVertexBuffer);
                scene.attachChild(sprite);
            }
        }



        return null;
    }

}
