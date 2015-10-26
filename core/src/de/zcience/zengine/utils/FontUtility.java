package de.zcience.zengine.utils;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Utility Class for BitMap Fonts
 * 
 * @author Zcience
 *
 */
public class FontUtility
{

    private HashMap<String, BitmapFont> fonts;

    public FontUtility()
    {
        fonts = new HashMap<String, BitmapFont>();

        loadBitMaps();
    }

    private void loadBitMaps()
    {
        // TODO: Load this from a file
        BitmapFont mainMenuFont = new BitmapFont();
        fonts.put("MainMenuFont", mainMenuFont);
    }

    public void addFont(String name, BitmapFont font)
    {
        fonts.put(name, font);
    }

    public void removeFont(String name)
    {
        BitmapFont toRemove = fonts.remove(name);
        toRemove.dispose();
    }

    /**
     * Returns specified BitmapFont or null, if BitmapFont was not found.
     * 
     * @param name
     *            of the BitmapFOnt
     * @return Specified BitmapFont or null, if BitmapFont was not found.
     */
    public BitmapFont getFont(String name)
    {
        return fonts.get(name);
    }
    
    public void dispose()
    {
        for(BitmapFont font : fonts.values())
        {
            font.dispose();
        }
        fonts.clear();
    }
}
