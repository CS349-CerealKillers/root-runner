package com.cerealkillers.rootrunner.command;

/**
 * Created by Benjamin Daschel on 6/2/15.
 */
public interface GameCommand<T> {

    public void run(T context);
}
