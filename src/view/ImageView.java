package view;

import java.io.IOException;

/**
 * Represents an image view that allows the client to view messages from the program.
 */
public interface ImageView {

  /**
   * Renders a message to the desired destination.
   * @param message is the message that is to be rendered.
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String message) throws IOException;

}
