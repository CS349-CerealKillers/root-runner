package items;

import android.util.Log;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class rm extends Tool
{
    public rm(int id)
    {
        super("rm", "Remove files or directories.", id);
    }

    /**
     * Removes a file from the map. Damages enemy characters it is used on.
     * @param id the id of the target object.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz.
        Log.d(rm.class.getSimpleName(), "Used rm.");
    }
}
