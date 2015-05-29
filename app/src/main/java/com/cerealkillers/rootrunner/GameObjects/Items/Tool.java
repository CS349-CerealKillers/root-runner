package com.cerealkillers.rootrunner.GameObjects.Items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public abstract class Tool extends Item implements ToolBehavior
{
    /**
     * Class constructor for Tools.
     * @param name The name of the tool.
     * @param description The description of the tool.
     * @param id The id of the tool.
     */
    public Tool(String name, String description, int id)
    {
        super(name, description, id, false);
    }
}
