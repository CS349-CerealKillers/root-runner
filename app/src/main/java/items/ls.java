package items;

import android.util.Log;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class ls extends  Tool
{
    /**
     * Class constructor for the ls tool.
     * @param id The id of the tool.
     */
    public ls(int id)
    {
        super("ls", "Reveal information  about  the FILEs in the current directory.", id);
    }

    /**
     * Reveals any hidden files or directories on the map.
     * @param id the id of an object on the map. (Unused for the ls tool.)
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        // TODO Implement me plz.
        Log.d(ls.class.getSimpleName(), "Used ls.");
    }
}
