package net.andrelopes.hopfieldImageRecognizer.screens;

import net.andrelopes.hopfieldImageRecognizer.threads.PresentThread;
import net.andrelopes.hopfieldImageRecognizer.threads.TrainThread;
import net.dermetfan.utils.libgdx.scene2d.ui.FileChooser;

/**
 *
 * @author Andre Vinícius Lopes
 */
public class Controller {

    private PresentThread presentThread;
    private TrainThread trainThread;

    /**
     *
     * Reference to Main Screen
     *
     * @see ViewScreen
     */
    private ViewScreen viewScreen;

    public Controller(ViewScreen screen) {
        this.viewScreen = screen;

    }

    /**
     *
     * Pressed Train From ViewScreen
     */
    public String train() {

        selectFile();

        //.. Still working on it
        return "Failed";
    }

    /**
     *
     * @Robin
     *
     */
    public void selectFile() {
        FileChooser fs = new FileChooser(null) {

            @Override
            protected void build() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        // Should return a gdx.files or a pixmap . Will be used to select a .jpg or .png or whatever.
    }

}
