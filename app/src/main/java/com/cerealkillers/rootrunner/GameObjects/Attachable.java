package com.cerealkillers.rootrunner.GameObjects;

/**
 * Created by Benjamin Daschel on 6/6/15.
 */
public interface Attachable<T> {

    public void onAttach(T attached);

    public void onDetach();
}
