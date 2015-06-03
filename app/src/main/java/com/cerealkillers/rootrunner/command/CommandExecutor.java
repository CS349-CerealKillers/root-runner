package com.cerealkillers.rootrunner.command;

/**
 * Created by Benjamin Daschel on 6/2/15.
 */
public interface CommandExecutor<T> {
    public void runCommand(GameCommand<T> command);
}

