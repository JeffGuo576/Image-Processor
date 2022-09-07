package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.ImageModel;
import view.ImageView;

/**
 * Represents an image controller implementation that reads client's commands from a
 * readable and directs which method to call from the model and what messages to render to the view.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageModel model;
  private final ImageView view;
  private final Readable in;

  /**
   * Creates an image controller implementation.
   * @param model is the image model.
   * @param view is the image view.
   * @param in is where the controller is reading the commands.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Cannot have any parameters as null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws IllegalStateException {
    Scanner scan = new Scanner(in);
    String command = null;
    String filename = null;
    String pathOrDesName = null;
    int increment = 0;
    boolean quit = false;
    try {
      this.view.renderMessage("Welcome to our image processor!" + System.lineSeparator()
              + "Type any of these commands in this specific format" + System.lineSeparator()
              + "To load an image or file: load A-File-Name The-Path-Of-FileName"
              + System.lineSeparator()
              + "To save and image or file: save A-File-Name The-Path-Of-FileName"
              + System.lineSeparator()
              + "To apply a component (type the name of desired component: red, green, "
              + "blue, value, "
              + "intensity, or luma): componentName-component AFileName TheDestinationOfFileName"
              + System.lineSeparator()
              + "To flip horizontally, vertically, blur, sharpen, grey scale, or "
              + "sepia tone an image: command referred-File-Name The-Destination-File-Name"
              + System.lineSeparator()
              + "To stop or quit the program: quit" + System.lineSeparator()
              + "Note that it will take some time to process these commands"
              + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    while (!quit && scan.hasNext()) {
      command = scan.next();
      try {
        switch (command) {
          case "load":
            this.model.load(scan.next(), scan.next());
            this.renderMessages("loaded");
            break;
          case "save":
            this.model.save(scan.next(), scan.next());
            this.renderMessages("saved");
            break;
          case "red-component":
            this.model.redGreyScale(scan.next(), scan.next());
            this.renderMessages("red");
            break;
          case "green-component":
            this.model.greenGreyScale(scan.next(), scan.next());
            this.renderMessages("green");
            break;
          case "blue-component":
            this.model.blueGreyScale(scan.next(), scan.next());
            this.renderMessages("blue");
            break;
          case "value-component":
            this.model.valueGreyScale(scan.next(), scan.next());
            this.renderMessages("value");
            break;
          case "intensity-component":
            this.model.intensityGreyScale(scan.next(), scan.next());
            this.renderMessages("intensity");
            break;
          case "luma-component":
            this.model.lumaGreyScale(scan.next(), scan.next());
            this.renderMessages("luma");
            break;
          case "horizontal-flip":
            this.model.horizontalFlip(scan.next(), scan.next());
            this.renderMessages("horizontally");
            break;
          case "vertical-flip":
            this.model.verticalFlip(scan.next(), scan.next());
            this.renderMessages("vertically");
            break;
          case "brighten":
            this.model.brighten(scan.next(), scan.next(), scan.nextInt());
            if (increment < 0) {
              this.renderMessages("Decreased");
            } else {
              this.renderMessages("Increased");
            }
            break;
          case "blur":
            this.model.blur(scan.next(), scan.next());
            this.renderMessages("blurred");
            break;
          case "sharpen":
            this.model.sharpen(scan.next(), scan.next());
            this.renderMessages("sharpened");
            break;
          case "greyscale":
            this.model.blur(scan.next(), scan.next());
            this.renderMessages("grey scaled");
            break;
          case "sepiaTone":
            this.model.sepiaTone(scan.next(), scan.next());
            this.renderMessages("sepia toned");
            break;
          case "quit":
            this.renderMessages("Exiting program");
            quit = true;
            break;
          default:
            this.renderMessages("Invalid command");
            break;
        }
      } catch (NoSuchElementException | IllegalStateException e) {
        this.renderExceptions();
      }
    }
  }

  /**
   * Renders a message depending on the command message.
   * @param message is the command the client inputted.
   * @throws IllegalStateException if the controller is unable to read input.
   */
  private void renderMessages(String message) throws IllegalStateException {
    if (message.equals("inputs")) {
      try {
        this.view.renderMessage("Must provide a file name and path" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("loaded") || message.equals("saved")) {
      try {
        this.view.renderMessage("Image has been " + message + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("red") || message.equals("green") || message.equals("blue")
            || message.equals("value") || message.equals("intensity") || message.equals("luma")) {
      try {
        this.view.renderMessage("Applied " + message + "-component to image"
                + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("vertically") || message.equals("horizontally")) {
      try {
        this.view.renderMessage("Flipped image " + message + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("Increased") || message.equals("Decreased")) {
      try {
        this.view.renderMessage(message + " brightness" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("Invalid command")) {
      try {
        this.view.renderMessage(message + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("blurred")) {
      try {
        this.view.renderMessage(message + " image" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("sharpened")) {
      try {
        this.view.renderMessage(message + " image" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("grey scaled")) {
      try {
        this.view.renderMessage(message + " image" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("sepia toned")) {
      try {
        this.view.renderMessage(message + " image" + System.lineSeparator());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (message.equals("Exiting program")) {
      try {
        this.view.renderMessage(message);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
  }

  /**
   * Renders a message depending on the command message.
   * @throws IllegalStateException if the controller is unable to read input.
   */
  private void renderExceptions() throws IllegalStateException {
    try {
      this.view.renderMessage("Input right command" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
