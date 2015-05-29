package com.cerealkillers.rootrunner.GameObjects.Items;

import com.cerealkillers.rootrunner.GameWorld.Map;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public interface ToolBehavior
{
    /**
     * The behavior of the tool when used on a target.
     * @param item The target item.
     */
    public void use(InteractiveItem item);
}
