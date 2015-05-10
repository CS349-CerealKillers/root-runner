package items;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public interface ToolBehavior
{
    /**
     * The behavior of the tool when used on a target.
     * @param id The id of the target object.
     * @param map The current map where the tool is being used.
     */
    public void use(int id, Map map);
}
