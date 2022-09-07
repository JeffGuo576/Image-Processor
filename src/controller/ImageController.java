package controller;

/**
 * Represents an Image Controller that decides which command or method to use from the model
 * and sends information to the image view to render messages.
 */
public interface ImageController {

  /**
   * Starts the image programmer.
   *
   * @throws IllegalStateException if controller is unable to read input.
   */
  void start() throws IllegalStateException;
}
