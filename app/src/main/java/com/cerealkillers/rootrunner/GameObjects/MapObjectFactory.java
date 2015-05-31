package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXObject;

import java.util.HashMap;

/**
 * Created by Benjamin Daschel on 5/30/15.
 */
public class MapObjectFactory {

    private static final String MAP_PORTALS = "portals";
    private static final String MAP_ITEMS = "items";
    private static final String MAP_CHARACTERS = "characters";
    private static final String PLAYER_SPAWN = "PlayerSpawn";

    public MapObject createMapObject(TMXObject object, Sprite sprite, String type) {

        MapObject mapObject = null;

        if(type.equals(MAP_PORTALS)){
            Portal portal = new Portal(object.getId(), sprite);
            portal.setDestination(object.getName());
            mapObject = portal;
        }
        //  TODO: add a case for character spawns. This should be used for NPCs, but not the player itself.

        else{
            mapObject = new MapObject(object.getId(), sprite);
        }

        //tags come from the TMX properties of an object
        HashMap<String, String> properties = object.getTMXObjectProperties();
        for (String key: properties.keySet()){
            String value = properties.get(key);
            Tag tag = new Tag(key, value);
            mapObject.addTag(tag);
        }

        return mapObject;
    }
}
