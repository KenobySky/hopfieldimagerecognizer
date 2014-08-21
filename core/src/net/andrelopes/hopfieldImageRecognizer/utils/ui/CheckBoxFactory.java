package net.andrelopes.hopfieldImageRecognizer.utils.ui;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import net.andrelopes.hopfieldImageRecognizer.Assets;

/**
 *
 * @author André Vinícius Lopes
 * @deprecated unused
 */
@Deprecated
public class CheckBoxFactory {

    public static CheckBox createCheckBox(String text) {
        return new CheckBox(" " + text, Assets.getSkin());
    }

}
