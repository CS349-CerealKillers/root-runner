package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.CommandFacade;
import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

import java.util.List;

/**
 * Created by Tyler Herrin on 6/6/2015.
 */
public class LsUpgrade extends MapTool
{
    private Ls mLs;

    /**
     * Ls upgrade consturctor.
     * @param sprite The associated sprite.
     * @param ls The wrapper ls tool.
     */
    public LsUpgrade(Sprite sprite, Ls ls)
    {
        super(sprite, "ls -la", "Lists even more information about a file.");
        mLs = ls;
    }

    /**
     * List all files and hidden files in the current map.
     * @param map The current map
     * @param x The x coordinate of a touch event.
     * @param y The y coordinate of a touch event.
     */
    public void use(Map map, float x, float y)
    {
        Log.d("LsUpgrade", "detected ls -la tool use");
        List<MapObject> visibleObjects = map.findByTag("hidden");
        String files = mLs.getFiles(map);

        for(MapObject m : visibleObjects)
        {
            Tag locked = m.getTag("locked");
            if(locked != null && locked.value != null)
            {
                files += locked.value + " " + m.getName() + "\n";
            }
        }

        CommandFacade.displayMessage(files);
    }
}
