package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.GameScene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;

/**
 * Loads a TMX file into a Map used by the game.
 */
public class MapLoader {

    private static final String MAP_PORTALS = "portals";
    private static final String MAP_ITEMS = "items";
    private static final String MAP_CHARACTERS = "characters";

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

    public Map load(String mapName, BaseScene scene){

        TMXTiledMap tmxTiledMap = null;
        try {
             tmxTiledMap = mTmxLoader.loadFromAsset("tmx/" + mapName);
        } catch (TMXLoadException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<TMXLayer> layers = tmxTiledMap.getTMXLayers();

        if(layers.size() > 0){
            TMXLayer baseLayer = layers.get(0);
            scene.attachChild(baseLayer);
            scene.setLayerHeight(baseLayer.getHeight());
            scene.setLayerWidth(baseLayer.getWidth());
        }

        Map gameMap = new Map(scene);

        ArrayList<TMXObjectGroup> objectGroups = tmxTiledMap.getTMXObjectGroups();

        /*
            This section creates sprites for each of the "entities" on the map.
            //TODO: also create physics bounds for detecting collisions btwn the player and objects.
         */
        for(TMXObjectGroup group: objectGroups){

            //some group level logic here
            for (TMXObject object: group.getTMXObjects()){
                //load sprites for individual entities
                int gid = object.getGid();
                ITextureRegion texture = tmxTiledMap.getTextureRegionFromGlobalTileID(gid);
                Sprite sprite = new Sprite(object.getX(), object.getY(), texture, mVertexBuffer);

                MapObject mapObject = new MapObject(object.getId(), sprite);

                gameMap.addMapObject(mapObject);
            }
        }

        scene.createScene();

        return gameMap;
    }

}
