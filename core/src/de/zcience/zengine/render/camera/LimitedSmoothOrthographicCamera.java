package de.zcience.zengine.render.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;

/**
 * Extending the standard orthographic camera to keep it inside the limits of
 * the map and to make the movement smooth. Use members to adjust movement of
 * the camera
 * 
 * uses the update method of the Camera parent class to do the calculations
 * 
 * @author David_000
 *
 */
public class LimitedSmoothOrthographicCamera extends OrthographicCamera {

	private Map map;

	private float mapWidth, mapHeight;

	private float springConstant, dampingConstant;
	private float mass;
	private Vector2 currentVel = new Vector2();

	/**
	 * @param springConstant
	 *            - The higher, the "harder" the connecting the camera to the
	 *            target is. Meaning : less delay of the camera following the
	 *            target
	 * @param mass
	 *            - THe higher, the heavier the camera gets, meaning it will
	 *            feel heavier
	 */
	public LimitedSmoothOrthographicCamera(float springConstant, float mass) {
		super();
		this.init(springConstant, mass);
	}

	/**
	 * Constructs a new OrthographicCamera, using the given viewport width and
	 * height. For pixel perfect 2D rendering just supply the screen size, for
	 * other unit scales (e.g. meters for box2d) proceed accordingly. The camera
	 * will show the region [-viewportWidth/2, -(viewportHeight/2-1)] -
	 * [(viewportWidth/2-1), viewportHeight/2]
	 * 
	 * @param viewportWidth
	 * @param viewportHeight
	 * @param springConstant
	 *            - The higher, the "harder" the connecting the camera to the
	 *            target is. Meaning : less delay of the camera following the
	 *            target
	 * @param mass
	 *            - THe higher, the heavier the camera gets, meaning it will
	 *            feel heavier
	 */
	public LimitedSmoothOrthographicCamera(float viewportWidth, float viewportHeight, float springConstant,
			float mass) {
		super(viewportWidth, viewportHeight);
		this.init(springConstant, mass);
	}

	private void init(float springConstant, float mass) {
		this.springConstant = springConstant;
		this.mass = mass;
		calcDampingConstant();
	}

	public void integrate(Vector2 target, float deltaTime) {
		// using the normal spring equation

		Vector2 springForce = new Vector2(target);
		springForce.sub(this.position.x, this.position.y);
		springForce.scl(springConstant);

		Vector2 dampingForce = new Vector2(currentVel).scl(dampingConstant);
		springForce.sub(dampingForce);

		Vector2 accel = springForce.scl(deltaTime).scl(1.0f
				/ mass); /* Calculate acceleration out of the Springforce */

		currentVel.add(accel); // integrate velocity
		/* integrate position */
		this.position.set(position.x + currentVel.x * deltaTime, this.position.y + currentVel.y * deltaTime, 0.0f);
	}

	public void setMap(Map map) {
		this.map = map;

		this.mapWidth = map.getProperties().get("width", Integer.class)
				* map.getProperties().get("tilewidth", Integer.class);
		this.mapHeight = map.getProperties().get("height", Integer.class)
				* map.getProperties().get("tileheight", Integer.class);
	}

	public Map getMap() {
		return map;
	}

	/**
	 * Set the spring constant. The higher, the "harder" the connecting the
	 * camera to the target is. Meaning : less delay of the camera following the
	 * target
	 * 
	 * @param springConstant
	 */
	public void setSpringConstant(float springConstant) {
		this.springConstant = springConstant;
		calcDampingConstant();
	}

	/**
	 * Calculates the dampingConstant for critical damping
	 */
	private void calcDampingConstant() {
		this.dampingConstant = 2.0f * (float) Math.sqrt(springConstant);
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

}
