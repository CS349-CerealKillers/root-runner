package com.cerealkillers.rootrunner.command;

import com.cerealkillers.rootrunner.GameWorld.World;
import com.cerealkillers.rootrunner.scene.PlayerHud;

/**
 * Created by Benjamin Daschel on 6/2/15.
 */
public class DisplayPlayerHudCommand implements GameCommand<World>{

    private final String message;

    public DisplayPlayerHudCommand(String message) {
        this.message = message;
    }

    @Override
    public void run(World context) {
        PlayerHud hud = context.getPlayerHud();
        hud.setMessage(message);
    }
}
