package view;

import java.io.IOException;

import model.ImageModel;

/**
 * Represents an image text view that allows the client to view the program and the rendered
 * messages.
 */
public class ImageTextView implements ImageView {

  private final ImageModel model;
  private final Appendable object;

  /**
   * Creates an image text view given a specified model.
   * @param model is an image model.
   * @throws IllegalArgumentException if model is null.
   */
  public ImageTextView(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Cannot have model as null");
    }
    this.model = model;
    this.object = System.out;
  }

  /**
   * Creates an image text view given a specified model and destination for data.
   * @param model is an image model.
   * @param object is the desired location for output data.
   * @throws IllegalArgumentException if model or object are null.
   */
  public ImageTextView(ImageModel model, Appendable object) throws IllegalArgumentException {
    if (model == null || object == null) {
      throw new IllegalArgumentException("Cannot have any arguments as null");
    }
    this.model = model;
    this.object = object;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.object.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission of board to provided data destination is invalid");
    }
  }
}
