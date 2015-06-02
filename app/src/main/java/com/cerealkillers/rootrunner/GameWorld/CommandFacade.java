package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.command.CommandExecutor;
import com.cerealkillers.rootrunner.command.DisplayPlayerHudCommand;
import com.cerealkillers.rootrunner.command.GameCommand;

/**
 * Created by Benjamin Daschel on 6/2/15.
 */
public class CommandFacade {

    private static CommandExecutor<World> sInstance;

    static void registerCommandExecutor(CommandExecutor<World> executor){
        sInstance = executor;
    }

    public static void displayMessage(String message){
        GameCommand command = new DisplayPlayerHudCommand(message);
        sInstance.runCommand(command);
    }


}
