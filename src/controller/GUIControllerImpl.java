package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageModel;
import model.Pixel;
import view.GUIView;

/**
 * Represents a GUI controller that works with the image model
 * and the GUI view.
 */
public class GUIControllerImpl implements GUIController {

  private final GUIView view;

  private final ImageModel model;

  private String feature;

  /**
   * Creates a GUI controller.
   * @param model is an image model for the image processor.
   * @param view is where the image processor will be displayed.
   */
  public GUIControllerImpl(ImageModel model, GUIView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Cannot have any parameters as null");
    }
    this.model = model;
    this.view = view;
    this.feature = null;
  }

  /**
   * When a button is pressed, it performs a certain action depending on which button is
   * pressed by the client.
   *
   * @param event the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    String filename = null;
    String path = null;
    String brighten = null;
    ImageIcon image = null;
    try {
      switch (command) {
        case "load": {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "ppm, JPG, JPEG, PNG", "ppm", "jpg", "JPEG", "png");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog(this.view);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            image = new ImageIcon(f.getAbsolutePath());
            this.view.renderWindowMessage(command);
            this.view.renderImage(image);
          }
        }
        break;
        case "brighten":
          this.view.renderWindowMessage(command);
          feature = "brighten";
          break;
        case "load enter":
          filename = this.view.getInput("file name");
          path = this.view.getInput("load path");
          this.model.load(filename, path);
          image = new ImageIcon(this.model.createImage(this.model.getImages().get(filename)));
          this.view.renderImage(image);
          this.view.renderMessage("Image has been loaded");
          Map<String, ArrayList<ArrayList<Pixel>>> imagePixels = this.model.getImages();
          this.view.renderHistograms(filename, imagePixels);
          break;
        case "save enter":
          filename = this.view.getInput("file name");
          path = "res\\" + this.view.getInput("dest name");
          this.model.save(filename, path);
          this.view.renderMessage("Image has been saved");
          break;
        case "enter":
          this.applyFeature(feature);
          break;
        default:
          this.view.renderWindowMessage(command);
          feature = command;
          break;
      }
    } catch (IllegalStateException e) {
      this.view.renderMessage(e + " Feature was not applied");
    }
  }

  /**
   * Applies a feature to an image depending on the given feature.
   *
   * @param feature is a feature that can be applied or modified to an image.
   */
  private void applyFeature(String feature) {
    String filename = this.view.getInput("file name");
    String dest = this.view.getInput("dest name");
    if (filename.length() < 1 || dest.length() < 1 || feature == null) {
      this.view.renderMessage("Must have at least a character for a file or destination name");
      return;
    }
    String arg = null;
    try {
      switch (feature) {
        case "red":
          this.model.redGreyScale(filename, dest);
          this.view.renderMessage("Applied Red Grey Scale To Image");
          break;
        case "green":
          this.model.greenGreyScale(filename, dest);
          this.view.renderMessage("Applied Green Grey Scale To Image");
          break;
        case "blue":
          this.model.blueGreyScale(filename, dest);
          this.view.renderMessage("Applied Blue Grey Scale To Image");
          break;
        case "value":
          this.model.valueGreyScale(filename, dest);
          this.view.renderMessage("Applied Value Grey Scale To Image");
          break;
        case "intensity":
          this.model.intensityGreyScale(filename, dest);
          this.view.renderMessage("Applied Intensity Grey Scale To Image");
          break;
        case "luma":
          this.model.lumaGreyScale(filename, dest);
          this.view.renderMessage("Applied Luma Grey Scale To Image");
          break;
        case "horizontal":
          this.model.horizontalFlip(filename, dest);
          this.view.renderMessage("Applied Horizontal-Flip To Image");
          break;
        case "vertical":
          this.model.verticalFlip(filename, dest);
          this.view.renderMessage("Applied Vertical-Flip To Image");
          break;
        case "brighten":
          arg = this.view.getInput("num");
          this.model.brighten(filename, dest, Integer.parseInt(arg));
          if (Integer.parseInt(arg) < 0) {
            this.view.renderMessage("Darkened Image");
          } else {
            this.view.renderMessage("Brightened Image");
          }
          break;
        case "blur":
          this.model.blur(filename, dest);
          this.view.renderMessage("Blurred Image");
          break;
        case "sharpen":
          this.model.sharpen(filename, dest);
          this.view.renderMessage("Sharpened Image");
          break;
        case "sepia":
          this.model.sepiaTone(filename, dest);
          this.view.renderMessage("Applied Sepia Tone To Image");
          break;
        case "grey scale":
          this.model.greyscale(filename, dest);
          this.view.renderMessage("Applied Grey Scale To Image");
          break;
        default:
          break;
      }
    } catch (IllegalStateException e) {
      this.view.renderMessage(e.getMessage());
    }

    Map<String, ArrayList<ArrayList<Pixel>>> imagePixels = this.model.getImages();
    ImageIcon image = new ImageIcon(this.model.createImage(this.model.getImages().get(dest)));
    this.view.renderImage(image);
    this.view.renderHistograms(dest, imagePixels);
  }
}
