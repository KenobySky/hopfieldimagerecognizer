package net.andrelopes.hopfieldImageRecognizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;

/**
 * @Revised by Robin Stu
 *
 * @author André Vinícius Lopes
 */
public class ViewScreen extends ScreenAdapter {

    private Stage stage = new Stage();
    private Table buttonsTable = new Table();

    private Controller controller;

    private TextField messageField;

    @Override

    public void show() {
        controller = new Controller(this);
        Gdx.input.setInputProcessor(stage);

        Skin skin = Assets.getSkin();

        TextButton train = new TextButton("Train", skin);
        TextButton exit = new TextButton("Exit", skin);
        TextButton presentPattern = new TextButton("Present Pattern", skin);

        TextButton stopPresenting = new TextButton("Stop Presenting", skin);
        TextButton stopTraining = new TextButton("Stop Training", skin);

        messageField = new TextField("", skin);
        messageField.setDisabled(true);
        train.setSize(5, 15);

        exit.setSize(5, 15);
        presentPattern.setSize(5, 15);

        buttonsTable.setPosition(Gdx.graphics.getWidth() / 2, 45);

        buttonsTable.add(train).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(presentPattern).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(exit).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.row();
        buttonsTable.add(stopPresenting).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(stopTraining).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.row();
        buttonsTable.add(messageField).size(Gdx.graphics.getWidth(), 30).colspan(3);
        stage.addActor(buttonsTable);

        train.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.train();
            }
        });

        presentPattern.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.presentPattern();
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.updateFSM();
        stage.act(delta);

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    public void showResult(boolean[] result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void showMessage(String msg) {
        messageField.setText(msg);

    }

}
