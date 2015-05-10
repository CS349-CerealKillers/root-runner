package items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public abstract class Tool extends Item implements ToolBehavior
{
    public Tool(String name, String description, int id)
    {
        super(name, description, id);
    }
}
