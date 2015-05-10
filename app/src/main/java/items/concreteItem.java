package items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class concreteItem extends Item
{
    private boolean isStatic;

    public concreteItem(String name, String description, int id, boolean isStatic)
    {
        super(name, description, id);
        this.isStatic = isStatic;
    }

    /**
     * If an item isStatic it cannot be put into the players inventory.
     * @return Returns true if item is static. Returns false if item can be picked up.
     */
    public boolean isStatic()
    {
        return this.isStatic;
    }
}
