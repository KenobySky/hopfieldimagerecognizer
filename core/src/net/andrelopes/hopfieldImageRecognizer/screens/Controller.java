package net.andrelopes.hopfieldImageRecognizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import net.andrelopes.hopfieldImageRecognizer.logic.Hopfield;
import net.andrelopes.hopfieldImageRecognizer.threads.PresentThread;
import net.andrelopes.hopfieldImageRecognizer.threads.TrainThread;
import net.andrelopes.hopfieldPatternRecognizer.utils.Assets;
import net.dermetfan.gdx.scenes.scene2d.ui.FileChooser.Listener;
import net.dermetfan.gdx.scenes.scene2d.ui.ListFileChooser;

/**
 *
 * @author Andre Vinícius Lopes
 */
public class Controller {

    /**
     * Status
     */
    private Status currentStatus;

    private PresentThread presentThread;
    private TrainThread trainThread;
    private Hopfield hopfield;

    /**
     * Reference to Main Screen
     *
     * @see ViewScreen
     */
    private ViewScreen viewScreen;

    /**
     *
     * Reference to Correct File
     */
    private FileHandle correctFile;

    /**
     *
     * Reference to Faulty File
     */
    private FileHandle faultyFile;

    public Controller(ViewScreen screen) {
        this.viewScreen = screen;
        hopfield = new Hopfield();
        currentStatus = Status.hopfieldNeverUsed_StartOfCycle;

    }

    public void updateFSM() {

        if (trainThread == null || presentThread == null) {
            currentStatus = Status.UNKNOWN;
        } else {
            if (!trainThread.isRunning() && trainThread.isNeverRan() && !presentThread.isRunning() && presentThread.isNeverRan()) {
                //Start of Cycle. Hopfield Never Used
                currentStatus = Status.hopfieldNeverUsed_StartOfCycle;
            } else {

                if (trainThread.isRunning() && !trainThread.isNeverRan() && !presentThread.isRunning() && presentThread.isNeverRan()) {
                    //Training for the first Time
                    currentStatus = Status.training;
                } else if (!trainThread.isRunning() && !trainThread.isNeverRan() && !presentThread.isRunning() && presentThread.isNeverRan()) {
                    //Finished Training And doing nothing
                    currentStatus = Status.trained;
                } else if (!trainThread.isRunning() && !trainThread.isNeverRan() && presentThread.isRunning() && !presentThread.isNeverRan()) {
                    //Presenting Pattern
                    currentStatus = Status.presenting;
                } else if (!trainThread.isRunning() && !trainThread.isNeverRan() && !presentThread.isRunning() && !presentThread.isNeverRan()) {
                    //Ended Cycle. Showing Result
                    currentStatus = Status.presented_EndOfCycle;
                } else {
                    currentStatus = Status.UNKNOWN;
                }

            }
        }
    }

    public void train() {

        if (correctFile == null) {
            showMessage("Image for training wasn't selected!");
        } else if (!correctFile.exists()) {
            showMessage("Image for training doesn't exists!");
        } else {

            if (trainThread == null || !trainThread.isRunning()) {

                trainThread = new TrainThread(this, correctFile, hopfield);

                Thread t = new Thread(trainThread);
                t.run();

                showMessage("Training Hopfield...");
            } else {
                showMessage("Cant Train because Thread is already Working");
            }
        }

    }

    public void presentPattern() {

        if (!presentThread.isRunning()) {

            presentThread = new PresentThread(this, faultyFile, hopfield);

            Thread t = new Thread(presentThread);
            t.run();

        } else {
            showMessage("Cant Present Pattern because Thread is already Working");
        }
    }

    public void selectFile(final boolean originalImage) {

        Window window = new Window("Choose an Image", Assets.getSkin());
        ListFileChooser listFileChooser = new ListFileChooser(Assets.getSkin(), new Listener() {

            @Override
            public void choose(FileHandle file) {

                if ("jpg".equals(file.extension()) || "bmp".equals(file.extension()) || "png".equals(file.extension())) {

                    if (originalImage) {
                        showMessage("Correct Training Image Selected!", Color.WHITE);
                        viewScreen.showCorrectFile(file.name());
                        correctFile = file;
                    } else {
                        showMessage("Correct Faulty Image Selected!", Color.WHITE);
                        viewScreen.showFaultyFile(file.name());
                        faultyFile = file;
                    }

                    viewScreen.destroySelectFileWindow();
                } else {
                    showMessage("Invalid File!", Color.RED);
                }
            }

            @Override
            public void choose(Array<FileHandle> files) {

            }

            @Override
            public void cancel() {
                viewScreen.destroySelectFileWindow();

            }
        });

        window.add(listFileChooser);
        window.pack();
        window.setHeight(Gdx.graphics.getHeight());
        window.setVisible(true);
        window.setX(Gdx.graphics.getWidth() / 2);

        viewScreen.showSelectFile(window);

    }

    public void showResult(boolean[] result) {
        viewScreen.showResult(result);
    }

    public void showMessage(String msg) {
        viewScreen.showMessage(msg);
    }

    public void showMessage(String msg, Color c) {
        viewScreen.showMessage(msg, c);
    }

    public void stopTraining() {
        try {
            trainThread.interrupt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stopPresent() {
        try {
            presentThread.interrupt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static enum Status {

        hopfieldNeverUsed_StartOfCycle(0f), training(1f), trained(2f), presenting(4f), presented_EndOfCycle(5f), UNKNOWN(10f);

        private float value;

        private Status(float f) {
            value = f;
        }
    }

}
