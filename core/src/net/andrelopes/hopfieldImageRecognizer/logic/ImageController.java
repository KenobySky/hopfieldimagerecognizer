package net.andrelopes.hopfieldImageRecognizer.logic;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

/**
 *
 * @author André Vinícius Lopes
 */
public class ImageController {

    private Pixmap pixmap;

    private boolean[] pixels;

    public ImageController() {

    }

    public boolean[] calculateBoolean(FileHandle file) {

        pixmap = new Pixmap(file);

        System.out.println("Image Format :" + pixmap.getFormat());

        pixels = getImageBoolean(pixmap);
        pixmap.dispose();

        System.out.println("Finished calculateBoolean()");

        return pixels;

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
                //System.out.println("\n Index");
                //System.out.println("Pixel Value :" + pixel);

                if (pixel == white) {
                    //System.out.println("White Pixel Recognized at [" + x + ";" + y + "]");
                    result[index] = false;
                } else if (pixel == black) {
                    //System.out.println("Black Pixel Recognized at [" + x + ";" + y + "]");
                    result[index] = true;
                } else {
                    //System.out.println("Pixel Not Recognized at [" + x + ";" + y + "]");
                }

                index++;
            }

        }

        return result;
    }

}
