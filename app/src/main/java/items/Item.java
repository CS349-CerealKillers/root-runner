package items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public abstract class Item
{
    private String name;
    private String description;
    private int id;

    public Item(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public int getID()
    {
        return this.id;
    }
}
