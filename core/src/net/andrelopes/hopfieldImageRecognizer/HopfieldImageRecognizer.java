package net.andrelopes.hopfieldImageRecognizer;

import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;
import com.badlogic.gdx.Game;
import net.andrelopes.hopfieldImageRecognizer.screens.ViewScreen;

/** @author dermetfan */
public class HopfieldImageRecognizer extends Game {

	@Override
	public void create () {
		Assets.load();
		setScreen(new ViewScreen());
	}

}
