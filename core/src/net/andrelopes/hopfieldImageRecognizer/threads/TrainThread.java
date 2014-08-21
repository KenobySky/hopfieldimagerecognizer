package net.andrelopes.hopfieldImageRecognizer.threads;

import net.andrelopes.hopfieldImageRecognizer.logic.Hopfield;
import net.andrelopes.hopfieldImageRecognizer.screens.ViewScreen;

public class TrainThread extends Thread {

    private ViewScreen viewScreen;
    private Hopfield hopfield;

    private boolean[] pattern;

    public TrainThread(ViewScreen mvs) {

    }

    @Override
    public void run() {
        hopfield.train(pattern);
    }

}
