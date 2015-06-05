package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 6/3/2015.
 */
public class Ln extends MapTool
{
    public Ln(Sprite sprite)
    {
        super(sprite, "ln", "Create links to other directories.");
    }

    public void use(float x, float y)
    {
        Log.d("LN", "detected ln tool use");
        PortalFactory pf = new PortalFactory();
        Portal portal = pf.getPortal(x, y);
    }
}
