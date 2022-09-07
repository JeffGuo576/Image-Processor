package controller;

import model.ImageModel;
import model.ImageModelImpl;
import view.GUIView;

/**
 * Creates a GUI program that runs our image processor onto a GUI. It allows the client
 * to see their image that they want to edit and a histogram of the image.
 */
public class GUIProgram {

  /**
   * The main method that runs the image processor in a GUI.
   * @param args are the arguments the main method takes in to run the program.
   */
  public static void main(String []args) {
    ImageModel model = new ImageModelImpl();
    GUIView view = new GUIView(model);
    GUIController controller = new GUIControllerImpl(model, view);
  }
}
