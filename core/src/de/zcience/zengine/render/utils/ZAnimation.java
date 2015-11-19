package de.zcience.zengine.render.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Convenience class, storing the currentStateTime internally and delivering a
 * update method for incrementing the currentStateTime
 * 
 * @author David_000
 *
 */
public class ZAnimation extends Animation {

	private float currentStateTime = 0.0f;

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link TextureRegion}s representing the frames.
	 */
	public ZAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
	}

	/**
	 * Constructor, storing the frame duration, key frames and play type.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link TextureRegion}s representing the frames.
	 * @param playMode
	 *            the animation playback mode.
	 */
	public ZAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
		super(frameDuration, keyFrames, playMode);
	}

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link TextureRegion}s representing the frames.
	 */
	public ZAnimation(float frameDuration, TextureRegion... keyFrames) {
		super(frameDuration, keyFrames);
	}

	/**
	 * Returns a {@link TextureRegion} based on the so called state time. This
	 * is the amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on. The mode specifies
	 * whether the animation is looping or not.
	 * 
	 * @param looping
	 *            whether the animation is looping or not.
	 * @return the TextureRegion representing the frame of animation for the
	 *         given state time.
	 */
	public TextureRegion getKeyFrame(boolean looping) {
		return super.getKeyFrame(currentStateTime, looping);
	}

	/**
	 * Returns a {@link TextureRegion} based on the so called state time. This
	 * is the amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on using the mode
	 * specified by {@link #setPlayMode(PlayMode)} method.
	 * 
	 * @return the TextureRegion representing the frame of animation for the
	 *         given state time.
	 */
	public TextureRegion getKeyFrame() {
		return super.getKeyFrame(currentStateTime);
	}

	/**
	 * Returns the current frame number.
	 * 
	 * @return current frame number
	 */
	public int getKeyFrameIndex() {
		return super.getKeyFrameIndex(currentStateTime);
	}

	/**
	 * Whether the animation would be finished if played without looping
	 * (PlayMode#NORMAL), given the state time.
	 * 
	 * @return whether the animation is finished.
	 */
	public boolean isAnimationFinished() {
		return super.isAnimationFinished(currentStateTime);
	}

	public void updateAnimation(float deltaTime) {
		this.currentStateTime += deltaTime;
	}

	public void resetCurrentStateTime() {
		this.currentStateTime = 0.0f;
	}

	public float getCurrentStateTime() {
		return currentStateTime;
	}

	public void setCurrentStateTime(float currentStateTime) {
		this.currentStateTime = currentStateTime;
	}

}
