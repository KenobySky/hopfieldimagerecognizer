package net.andrelopes.hopfieldImageRecognizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;

public class ViewScreen extends ScreenAdapter {

    private Stage stage = new Stage();
    private Table buttonsTable = new Table();

    private Controller controller;

    private TextField messageField;

    private TextField selectedTrainingFile, selectedFaultyFile;

    public Window auxWindow;

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

        TextButton selectTrainingImage = new TextButton("Select Training Image", skin);

        selectTrainingImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                controller.selectFile(true);
            }

        });

        TextButton selectFaultyImage = new TextButton("Select Faulty Image", skin);
        selectFaultyImage.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.selectFile(false);
            }

        });

        messageField = new TextField("Log area", skin);
        messageField.setDisabled(true);
        train.setSize(5, 15);

        selectedTrainingFile = new TextField("Select Training File!", skin);
        selectedTrainingFile.setDisabled(true);
        selectedTrainingFile.setSize(5, 15);
        selectedFaultyFile = new TextField("Select Faulty File!", skin);
        selectedFaultyFile.setDisabled(true);
        selectedFaultyFile.setSize(5, 15);

        exit.setSize(5, 15);
        presentPattern.setSize(5, 15);

        buttonsTable.setPosition(Gdx.graphics.getWidth() / 2, 90);

        buttonsTable.add(train).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(presentPattern).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(exit).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.row();
        buttonsTable.add(stopPresenting).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(stopTraining).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.row();
        buttonsTable.add(messageField).size(Gdx.graphics.getWidth(), 30).colspan(3);
        buttonsTable.row();
        buttonsTable.add(selectedTrainingFile).size(Gdx.graphics.getWidth() / 1.5f, 30).colspan(2).left();
        buttonsTable.add(selectTrainingImage).colspan(3).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.row();
        buttonsTable.add(selectedFaultyFile).size(Gdx.graphics.getWidth() / 1.5f, 30).colspan(2).left();
        buttonsTable.add(selectFaultyImage).colspan(3).size(Gdx.graphics.getWidth() / 3, 30);
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
        if (auxWindow != null) {
            auxWindow.setHeight(height);
            auxWindow.setX(width / 2);
        }
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

    public void showMessage(String msg, Color color) {
        messageField.setColor(color);
        messageField.setText(msg);
    }

    public void showSelectFile(Window window) {
        this.auxWindow = window;
        stage.addActor(auxWindow);
    }

    public void destroySelectFileWindow() {
        auxWindow.remove();
    }

    public void showCorrectFile(String fileName) {
        selectedTrainingFile.setText(fileName);
    }

    public void showFaultyFile(String fileName) {
        selectedFaultyFile.setText(fileName);
    }

}
