package net.andrelopes.hopfieldImageRecognizer;

import com.badlogic.gdx.Game;
import net.andrelopes.hopfieldImageRecognizer.screens.ViewScreen;
import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;

/** @author dermetfan */
public class HopfieldImageRecognizer extends Game {

	@Override
	public void create () {
		Assets.load();
		setScreen(new ViewScreen());
	}

}
