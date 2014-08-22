package net.andrelopes.hopfieldImageRecognizer.screens;

import com.badlogic.gdx.files.FileHandle;
import net.andrelopes.hopfieldImageRecognizer.logic.Hopfield;
import net.andrelopes.hopfieldImageRecognizer.threads.PresentThread;
import net.andrelopes.hopfieldImageRecognizer.threads.TrainThread;

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

        if (!trainThread.isRunning()) {

            FileHandle selectFile = selectFile();
            trainThread = new TrainThread(this, selectFile, hopfield);

            Thread t = new Thread(trainThread);
            t.run();

        } else {
            showMessage("Cant Train because Thread is already Working");
        }

    }

    public void presentPattern() {

        if (!presentThread.isRunning()) {

            FileHandle selectFile = selectFile();
            presentThread = new PresentThread(this, selectFile, hopfield);

            Thread t = new Thread(presentThread);
            t.run();

        } else {
            showMessage("Cant Present Pattern because Thread is already Working");
        }
    }

    public FileHandle selectFile() {
        //FileChooser fs = new FileChooser(null)
        FileHandle fh = null;
        return fh;
    }

    public void showResult(boolean[] result) {
        viewScreen.showResult(result);
    }

    public void showMessage(String msg) {
        viewScreen.showMessage(msg);
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
