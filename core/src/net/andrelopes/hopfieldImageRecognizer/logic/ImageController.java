package net.andrelopes.hopfieldImageRecognizer.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

/**
 *
 * @author André Vinícius Lopes
 */
public class ImageController {

    private Pixmap correctImage;
    private Pixmap incorrectImage;

    public boolean[] originalImage, fakeImage;

    public ImageController() {

    }

    public void calculateBoolean() {

        incorrectImage = new Pixmap(Gdx.files.internal("etc/blackwhiteFake.png"));
        correctImage = new Pixmap(Gdx.files.internal("etc/blackwhite.png"));

        System.out.println("CorrectImage Format :" + correctImage.getFormat());
        System.out.println("IncorrectImage Format :" + incorrectImage.getFormat());

        originalImage = getImageBoolean(correctImage);
        fakeImage = getImageBoolean(incorrectImage);
        correctImage.dispose();
        incorrectImage.dispose();

        System.out.println("Finished calculateBoolean()");

    }

    public boolean[] getImageBoolean(Pixmap image) {

        boolean[] result = new boolean[image.getHeight() * image.getWidth()];
        int index = 0;

        System.out.println("Size :" + result.length);

        int black = 255;
        int white = -1;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                int pixel = image.getPixel(x, y);
                System.out.println("\n Index");
                System.out.println("Pixel Value :" + pixel);

                if (pixel == white) {
                    System.out.println("White Pixel Recognized at [" + x + ";" + y + "]");
                    result[index] = false;
                } else if (pixel == black) {
                    System.out.println("Black Pixel Recognized at [" + x + ";" + y + "]");
                    result[index] = true;
                } else {
                    System.out.println("Pixel Not Recognized at [" + x + ";" + y + "]");
                }

                index++;
            }

        }

        return result;
    }

}
