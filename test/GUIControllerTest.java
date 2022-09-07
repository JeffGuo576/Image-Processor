import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import model.ImageModel;
import model.Pixel;

/**
 * Represents tests for the GUI controller.
 */
public class GUIControllerTest {

  class MockModel implements ImageModel {
    StringBuilder log;

    public MockModel(StringBuilder stringBuilder) {
      this.log = stringBuilder;
    }

    @Override
    public void load(String filename, String path) throws IllegalStateException {
      this.log.append("loaded in " + filename + System.lineSeparator());
    }

    @Override
    public void save(String filename, String path) throws IllegalStateException {
      this.log.append("saved " + filename + " in " + path + System.lineSeparator());
    }

    @Override
    public void redGreyScale(String filename, String desName) {
      this.log.append("red-component on " + filename + System.lineSeparator());
    }

    @Override
    public void greenGreyScale(String filename, String desName) {
      this.log.append("green-component on " + filename + System.lineSeparator());
    }

    @Override
    public void blueGreyScale(String filename, String desName) {
      this.log.append("blue-component on " + filename + System.lineSeparator());
    }

    @Override
    public void valueGreyScale(String filename, String desName) {
      this.log.append("value-component on " + filename + System.lineSeparator());
    }

    @Override
    public void intensityGreyScale(String filename, String desName) {
      this.log.append("intensity-component on " + filename + System.lineSeparator());
    }

    @Override
    public void lumaGreyScale(String filename, String desName) {
      this.log.append("luma-component on " + filename + System.lineSeparator());
    }

    @Override
    public void horizontalFlip(String filename, String desName) {
      this.log.append("horizontal-flip on " + filename + System.lineSeparator());
    }

    @Override
    public void verticalFlip(String filename, String desName) {
      this.log.append("vertical-flip on " + filename + System.lineSeparator());
    }

    @Override
    public void brighten(String filename, String desName, int increment) {
      this.log.append("incremented or decreased brightness on " + filename
              + System.lineSeparator());
    }

    @Override
    public Map<String, ArrayList<ArrayList<Pixel>>> getImages() {
      this.log.append("received map images field " + System.lineSeparator());
      return null;
    }

    @Override
    public void clearImages() {
      this.log.append("cleared map images field " + System.lineSeparator());
    }

    @Override
    public void blur(String filename, String desName) {
      this.log.append("blurred image " + System.lineSeparator());
    }

    @Override
    public void sharpen(String filename, String desName) {
      this.log.append("sharpened image " + System.lineSeparator());
    }

    @Override
    public void greyscale(String filename, String desName) {
      this.log.append("grey scaled image " + System.lineSeparator());
    }

    @Override
    public void sepiaTone(String filename, String desName) {
      this.log.append("sepia toned image " + System.lineSeparator());
    }

    @Override
    public BufferedImage createImage(ArrayList<ArrayList<Pixel>> imagePixels) {
      return null;
    }
  }

}
