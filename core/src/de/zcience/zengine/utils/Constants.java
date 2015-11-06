package de.zcience.zengine.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static float PHYSICS_TIMESTEP = 1.0f / 60.0f;
	public static float EPSILON = 0.000001f;
	
	public static Vector2 B2D_GRAVITY = new Vector2(0, -10.0f);
	public static int B2D_VELOCITY_ITERATIONS = 10;
	public static int B2D_POSITION_ITERATIONS = 8;
	public static float B2D_UNITS_PER_PIXEL = 1.0f / 16.0f;
}
