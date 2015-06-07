package com.cerealkillers.rootrunner.listeners;

import com.cerealkillers.rootrunner.GameObjects.MapObject;
import com.cerealkillers.rootrunner.GameObjects.Tag;
import com.cerealkillers.rootrunner.GameWorld.CommandFacade;

/**
 * Created by Benjamin Daschel on 6/6/15.
 */
public class TouchInteractionDelegate implements InteractionDelegate {

    @Override
    public void onMapObjectTouched(MapObject mapObject) {

        //TODO: Invoke inventory facade to cause an action between the player's tool and
        // the touched map object

        Tag descriptionTag = mapObject.getTag("description");
        if(descriptionTag != null){
            String description = descriptionTag.value;
            CommandFacade.displayMessage(description);

        }
    }
}
