package controller;

import java.awt.event.ActionEvent;

/**
 * Represents a GUI controller that receives and sends data to the GUI view and image model.
 */
public interface GUIController {

  /**
   * Reads the action event performed by the client and adds a feature to the image depening on
   * what event is done.
   * @param event is the client's input or event like clicking on a button.
   */
  void actionPerformed(ActionEvent event);

}
