package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public abstract class ObjectTool extends InventoryItem implements ObjectToolBehavior
{
    String name;
    String desc;

    /**
     *
     * @param sprite The tool's sprite
     * @param name name of the tool.
     * @param desc description of the too;
     */
    public ObjectTool(Sprite sprite, String name, String desc)
    {
        super(sprite);
        this.name = name;
        this.desc = desc;
    }
}
