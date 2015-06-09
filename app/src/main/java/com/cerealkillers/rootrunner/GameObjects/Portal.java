package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.CommandFacade;
import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.GameWorld.World;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Benjamin Daschel on 5/30/15.
 */
public class Portal extends MapObject {

    private String mDestination;

    public Portal(int id, Sprite sprite) {
        super(id, sprite);
    }

    public void setDestination(String destinationMap){
        mDestination = destinationMap;
    }

    /**
     * Called when the player touches a portal
     *
     */
    @Override
    public void onCollide(MapObject collidedWith){
        Map mapToDestroy = collidedWith.getAttachedMap();
        mapToDestroy.getBaseScene().disposeScene();
        CommandFacade.loadMap(this);
    }

    public String getDestination(){
        return mDestination;
    }

}
