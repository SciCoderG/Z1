package de.zcience.zengine.input;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ButtonControlledComponent implements Component, Poolable
{
    public boolean isActive = true;

    @Override
    public void reset()
    {
        isActive = true;
    }

}
