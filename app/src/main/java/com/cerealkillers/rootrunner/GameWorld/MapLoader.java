package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.GameObjects.MapObjectFactory;
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

    private final MapObjectFactory mMapObjectFactory;

    private VertexBufferObjectManager mVertexBuffer;
    private TMXLoader mTmxLoader;

    private static final String TMX_GID  = "gid";

    public MapLoader(TMXLoader loader, VertexBufferObjectManager bufferObjectManager) {
        mTmxLoader = loader;
        mVertexBuffer = bufferObjectManager;
        mMapObjectFactory = new MapObjectFactory();
    }

    /**
     * Inflate a TMX map file into a map.
     * Inflated map includes all objects defined in the TMX file.
     * @param mapName file name of the TMX file to load without the extension
     * @param scene backgrounds and sprites will be added to this scene
     * @return a ready to use map with an attached scene
     */
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

                // the group name determines the type of the object
                MapObject mapObject = mMapObjectFactory.createMapObject(object, sprite);
                gameMap.addMapObject(mapObject);
            }
        }

        scene.createScene();

        return gameMap;
    }

}
