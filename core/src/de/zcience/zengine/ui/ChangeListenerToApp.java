package de.zcience.zengine.ui;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.zcience.ZApplication;

/**
 * Used to be able to change the application from a changed Widget, such as the
 * Start Button telling the application to switch to the GameScreen
 * 
 * @author David_000
 *
 */
abstract public class ChangeListenerToApp extends ChangeListener {

	private ZApplication application;

	public ChangeListenerToApp(ZApplication app) {
		super();
		this.setApplication(app);
	}

	public ZApplication getApplication() {
		return application;
	}

	public void setApplication(ZApplication application) {
		this.application = application;
	}
}
