package com.cerealkillers.rootrunner.GameWorld;

import android.util.SparseArray;

import com.cerealkillers.rootrunner.GameObjects.Characters.Character;
import com.cerealkillers.rootrunner.GameObjects.Items.Item;
import com.cerealkillers.rootrunner.GameObjects.Structures.Portal;

/**
 * Created by Benjamin Daschel on 5/6/15.
 */
public class Map {

    SparseArray<Portal> mPortals;
    SparseArray<Item> mItemsOnMap;
    SparseArray<Character> mCharacters;

    public Portal getPortal(int portalId){
        return mPortals.get(portalId);
    }

    public Item getItem(int itemId){
        return mItemsOnMap.get(itemId);
    }

    public Character getCharacter(int characterId){
        return mCharacters.get(characterId);
    }

    public void addCharacter(Character character){
        if(mCharacters.indexOfKey(character.getID()) < 0){
            mCharacters.put(character.getID(), character);
        }
    }

    public void addPortal(Portal portal){
        if(mPortals.indexOfKey(portal.getID()) < 0){
            mPortals.put(portal.getID(), portal);
        }
    }

    public void addItem(Item item){
        if(mItemsOnMap.indexOfKey(item.getID()) < 0){
            mItemsOnMap.put(item.getID(), item);
        }
    }

    public Portal removePortal(int portalId){
        Portal deleted = getPortal(portalId);
        mPortals.remove(portalId);
        return deleted;
    }

    public Item removeItem(int itemId){
        Item deleted = getItem(itemId);
        mItemsOnMap.remove(itemId);
        return deleted;
    }

    public Character removeCharacter(int characterId){
        Character deleted = getCharacter(characterId);
        mCharacters.remove(characterId);
        return deleted;
    }
}
