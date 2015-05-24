package com.cerealkillers.rootrunner.GameObjects.Items;

import org.andengine.extension.tmx.TMXObject;

import java.lang.reflect.Constructor;

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

    public Item itemFromTmxObject(TMXObject tmxObject) {

        String className = tmxObject.getTMXObjectProperty("class");
        if(className != null){
            try {
                Class itemClass = Class.forName(className);
                return  (Item) itemClass.newInstance();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
