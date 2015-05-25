package com.cerealkillers.rootrunner.GameObjects.Items;

import org.andengine.extension.tmx.TMXObject;

/**
 * Created by Benjamin Daschel on 5/24/15.
 */
public class ConcreteItemFactory {

    public static Item itemFromTmxObject(TMXObject tmxObject) {

        String itemType = tmxObject.getType();
        boolean isStatic  = itemType.equals("interactive"); //otherwise it's a pickup type

        ConcreteItem item = new ConcreteItem(tmxObject.getName(),
                tmxObject.getTMXObjectProperty("description"),
                tmxObject.getId(),
                isStatic
        );

        /*
            TODO: attach listeners and other crap to new items based on additional TMX properties
             which aren't defined yet.
             For example, interactive items will listen for certain tools being used against it.
             Pickup items don't really need any special treatment.
         */


        return item;
    }
}
