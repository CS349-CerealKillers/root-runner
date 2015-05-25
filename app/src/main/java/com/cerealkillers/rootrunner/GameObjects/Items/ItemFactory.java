package com.cerealkillers.rootrunner.GameObjects.Items;

import org.andengine.extension.tmx.TMXObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

        /*
            There are four basic types of in game items
            - Interactive: cannot be picked up but can be operated on with certain tools
            - Pickup: able to be picked up but not usable on their own
            - Tool: able to be picked up and used against other game objects
         */

        String itemType = tmxObject.getType();

        if (itemType.equals("tool")){ //invoke ToolFactory
            return ToolFactory.itemfromTmxObject(tmxObject);
        }else{ //Invoke ConcreteItemFactory
            return ConcreteItemFactory.itemFromTmxObject(tmxObject);
        }
    }


}
