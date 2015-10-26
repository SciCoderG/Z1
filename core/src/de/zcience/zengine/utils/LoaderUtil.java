package de.zcience.zengine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Utility class for loading stuff, until stuff is loaded from a file TODO: Make
 * this data drivens
 * 
 * @author David_000
 *
 */
public class LoaderUtil {

	private LoaderUtil() {

	}

	/**
	 * Returns a simple white skin containing a 1x1 Pixmap
	 * 
	 * @return
	 */
	public static Skin makeSimpleSkin() {
		return makeSimpleSkin(Color.WHITE);
	}

	/**
	 * Returns a skin with specified color containing a 1x1 Pixmap
	 * 
	 * @param color
	 * @return
	 */
	public static Skin makeSimpleSkin(Color color) {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		skin.add("default", new Texture(pixmap));
		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());
		setSimpleSkinTextButtonStyle(skin);

		return skin;
	}

	public static void setSimpleSkinTextButtonStyle(Skin toSet) {
		setSimpleSkinTextButtonStyle(toSet, Color.DARK_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, 1, -1);
	}

	public static void setSimpleSkinTextButtonStyle(Skin toSet, Color up, Color down, Color over, int pressedOffsetX,
			int pressedOffsetY) {
		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle;
		boolean isSet = false;
		if (toSet.has("default", TextButtonStyle.class)) {
			isSet = true;
			textButtonStyle = toSet.get("default", TextButtonStyle.class);
		} else {
			textButtonStyle = new TextButtonStyle();
		}
		textButtonStyle.up = toSet.newDrawable("default", up);
		textButtonStyle.down = toSet.newDrawable("default", down);
		textButtonStyle.over = toSet.newDrawable("default", over);
		textButtonStyle.font = toSet.getFont("default");
		textButtonStyle.pressedOffsetX = pressedOffsetX;
		textButtonStyle.pressedOffsetY = pressedOffsetY;
		if (!isSet) {
			toSet.add("default", textButtonStyle);
		}
	}

}
