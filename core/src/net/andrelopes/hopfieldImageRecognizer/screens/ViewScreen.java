package net.andrelopes.hopfieldImageRecognizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;
import net.andrelopes.hopfieldImageRecognizer.logic.ImageController;
import net.andrelopes.hopfieldImageRecognizer.logic.Hopfield;

/**
 * revised by DermetFan :) Or Robin?
 *
 * @author André Vinícius Lopes
 */
public class ViewScreen extends ScreenAdapter {

    public Hopfield hopfield;
    private ImageController imageController;

    private Stage stage = new Stage();
    private Table buttonsTable = new Table();

    @Override
    public void show() {
        hopfield = new Hopfield();
        imageController = new ImageController();

        Gdx.input.setInputProcessor(stage);

        Skin skin = Assets.getSkin();

        TextButton train = new TextButton("Train", skin);
        TextButton exit = new TextButton("Exit", skin);
        TextButton presentPattern = new TextButton("Present Pattern", skin);

        train.setSize(5, 15);

        exit.setSize(5, 15);
        presentPattern.setSize(5, 15);

        buttonsTable.setPosition(Gdx.graphics.getWidth() / 2, 30);
        buttonsTable.add(train).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(presentPattern).size(Gdx.graphics.getWidth() / 3, 30);
        buttonsTable.add(exit).size(Gdx.graphics.getWidth() / 3, 30);

        stage.addActor(buttonsTable);

        //Add listeners
        train.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //This loads the images and calculate the hopfield boolean vector.
                System.out.println("Starting calculating Boolean");
                imageController.calculateBoolean();

                //This trains the Hopfield.
                System.out.println("Finished calculating Boolean , Starting training...");
                hopfield.train(imageController.originalImage);
                System.out.println("Finished training");
            }
        });

        presentPattern.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Starting Presenting Data");
                hopfield.present(imageController.fakeImage);
                System.out.println("Done process..Finished Presenting Data");
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

        stage.act(delta);

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }

}
