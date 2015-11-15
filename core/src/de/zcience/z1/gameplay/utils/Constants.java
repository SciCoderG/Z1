package de.zcience.z1.gameplay.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static float PHYSICS_TIMESTEP = 1.0f / 60.0f;
	public static float EPSILON = 0.000001f;

	public static Vector2 B2D_GRAVITY = new Vector2(0, -15.0f);
	public static int B2D_VELOCITY_ITERATIONS = 10;
	public static int B2D_POSITION_ITERATIONS = 8;
	public static float B2D_UNITS_PER_PIXEL = 1.0f / 64.0f;

	public static float PLAYER_MAX_VELOCITY = 5.0f;

	public static float CAMERA_SPRINGCONSTANT = 100.0f;
	public static float CAMERA_MASS = 0.1f;
	public static float CAMERA_UPDATE_TIMER = 1.0f / 60.0f;
	public static float CAMERA_MAX_DISTANCE = 10.0f;
	
	public static final float INPUT_DOUBLE_CLICK_TIMER = 0.5f;

}
