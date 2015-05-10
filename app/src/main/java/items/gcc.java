package items;

import android.util.Log;

/**
 * Created by Tyler Herrin on 5/10/2015.
 */
public class gcc extends Tool
{
    public gcc(int id)
    {
        super("gcc", "When you invoke GCC, it does preprocessing, compilation, assembly and linking.", id);
    }

    /**
     * Compiles source code into a tool.
     * @param id the id of the target object.
     * @param map the current map.
     */
    public void use(int id, Map map)
    {
        //TODO Implement me plz
        Log.d(gcc.class.getSimpleName(), "Used gcc.");
    }
}
