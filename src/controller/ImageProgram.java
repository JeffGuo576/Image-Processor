package controller;

import java.io.InputStreamReader;
import java.io.Reader;

import model.ImageModel;
import model.ImageModelImpl;
import view.ImageTextView;
import view.ImageView;

/**
 * Represents an Image Processor. It loads, saves, or make changes to the photo
 * based on the desired command line.
 */
public class ImageProgram {

  /**
   * This is the main method which is what the program will call to run the Image Processor.
   *
   * @param args are the arguments from the command line.
   */
  public static void main(String []args) {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageTextView(model);
    Reader in = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.start();
  }
}
