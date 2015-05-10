package items;

import android.util.Log;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class chmod extends Tool
{
    /**
     * Class constructor for the chmod tool.
     * @param id The id of the tool.
     */
    public chmod(int id)
    {
        super("chmod", "Change file access permissions.", id);
    }

    /**
     * Unlocks an object on the map.
     * @param id the id of the object to be unlocked.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz.
        Log.d(chmod.class.getSimpleName(), "Used chmod.");
    }
}
