package net.andrelopes.hopfieldImageRecognizer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import net.andrelopes.hopfieldImageRecognizer.HopfieldImageRecognizer;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		config.vSyncEnabled = false;
		config.title = "Hopfield Image-Pattern Recognizer 0.2";
		config.initialBackgroundColor = Color.WHITE;
		new LwjglApplication(new HopfieldImageRecognizer(), config);
	}

}
