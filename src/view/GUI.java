package view;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Pixel;

/**
 * Represents a GUI. Displays the image processor to the client with functioning buttons for
 * commands.
 */
public interface GUI {

  /**
   * Alerts the client when a feature has been applied or an error to the image.
   * @param message is the message being alerted to the client
   */
  void renderMessage(String message);

  /**
   * Renders an image onto the GUI.
   * @param image is the image being displayed.
   */
  void renderImage(ImageIcon image);

  /**
   * Gets the certain input or text field depending on the type of text field determined by the
   * given.
   * @param type is the type of text field to be returned in a text.
   * @return the value of desired text field.
   */
  String getInput(String type);

  /**
   * Opens a new frame for needed inputs from the client when clicked on a command button.
   * @param message is the type of pop up window to ask the clients for.
   */
  void renderWindowMessage(String message);

  /**
   * Renders the histograms to the GUI.
   * @param filename is the filename of the image.
   * @param imagePixels is the image in a 2D array list of pixels.
   */
  void renderHistograms(String filename, Map<String, ArrayList<ArrayList<Pixel>>> imagePixels);

}
