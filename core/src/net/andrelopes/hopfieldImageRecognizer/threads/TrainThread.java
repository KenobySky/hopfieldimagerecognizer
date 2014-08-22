package net.andrelopes.hopfieldImageRecognizer.threads;

import com.badlogic.gdx.files.FileHandle;
import net.andrelopes.hopfieldImageRecognizer.logic.Hopfield;
import net.andrelopes.hopfieldImageRecognizer.logic.ImageController;
import net.andrelopes.hopfieldImageRecognizer.screens.Controller;

public class TrainThread extends Thread {

    private Controller controller;
    private Hopfield hopfield;
    private ImageController imageController;

    public FileHandle filePath;

    private boolean running;
    private boolean neverRan = true;

    public TrainThread(Controller controller, FileHandle path, Hopfield hopfield) {
        imageController = new ImageController();
        this.controller = controller;
        this.filePath = path;
        this.hopfield = hopfield;
    }

    @Override
    public void run() {
        running = true;
        neverRan = true;

        System.out.println("Calculating the hopfield boolean vector...");
        boolean[] hopfieldInputData = imageController.calculateBoolean(filePath);

        System.out.println("Finished calculating the hopfield boolean vector,Commencing training...");
        hopfield.train(hopfieldInputData);

        System.out.println("Finished training . Ready To Procceed");

        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isNeverRan() {
        return neverRan;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        neverRan = true;
    }

}
