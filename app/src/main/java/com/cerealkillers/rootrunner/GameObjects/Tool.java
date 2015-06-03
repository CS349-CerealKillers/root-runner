package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public abstract class Tool extends InventoryItem implements ToolBehavior
{
    String name;
    String desc;

    public Tool(int id, Sprite sprite, String name, String desc)
    {
        super(id, sprite);
        this.name = name;
        this.desc = desc;
    }
}
