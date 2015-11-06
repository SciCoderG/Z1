package de.zcience.zengine.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Use this System to actually give input orders to a ButtonControlled Entity
 * 
 * @author Zcience
 *
 */
public class ButtonControlledSystem extends IteratingSystem implements ZKeyboardListener
{

    @SuppressWarnings("unchecked")
    public ButtonControlledSystem()
    {
        super(Family.all(ButtonControlledComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {

    }

    @Override
    public boolean keyDown(int keycode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
