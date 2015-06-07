package com.cerealkillers.rootrunner.command;

import com.cerealkillers.rootrunner.GameObjects.Portal;
import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.GameWorld.World;

/**
 * Created by Francisco on 6/6/2015.
 */
public class LoadMapCommand implements GameCommand<World> {

    private final Portal cPortal;

    public LoadMapCommand(Portal p) {
        this.cPortal = p;
    }

    @Override
    public void run(World world) {
        world.initializeMap(cPortal.getDestination());
    }

}
