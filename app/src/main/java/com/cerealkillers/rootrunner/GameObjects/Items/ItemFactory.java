package com.cerealkillers.rootrunner.GameObjects.Items;

import org.andengine.extension.tmx.TMXObject;

/**
 * Created by Benjamin Daschel on 5/21/15.
 */
public class ItemFactory {

    private static ItemFactory sInstance;

    public synchronized static ItemFactory getInstance() {
        if (sInstance == null){
            sInstance = new ItemFactory();
        }
        return sInstance;
    }

    public Item itemFromTmxObject(TMXObject item) {
        // TODO: we probably want to use reflection here to load classes based on a TMX attribute
       throw new RuntimeException("Method not implemented yet!");
        return null;
    }
}
