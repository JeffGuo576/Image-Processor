import org.junit.Test;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;
import view.ImageTextView;
import view.ImageView;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the controller portion of the Image Processor.
 */
public class ImageControllerTest {

  ImageModel model;
  Appendable output;
  ImageView view;
  StringBuilder intro;
  Readable in;
  ImageController controller;
  StringBuilder expectedOutput;

  private void init() {
    model = new ImageModelImpl();
    output = new StringBuilder();
    view = new ImageTextView(model, output);
    intro = new StringBuilder("Welcome to our image processor!\n" +
            "Type any of these commands in this specific format\n" +
            "To load an image or file: load A-File-Name The-Path-Of-FileName\n" +
            "To save and image or file: save A-File-Name The-Path-Of-FileName\n" +
            "To apply a component (type the name of desired component: red, green, blue, value, " +
            "intensity, or luma): componentName-component AFileName TheDestinationOfFileName\n" +
            "To flip horizontally, vertically, blur, sharpen, grey scale, or sepia tone an" +
            " image: command referred-File-Name The-Destination-File-Name\n" +
            "To stop or quit the program: quit\n" +
            "Note that it will take some time to process these commands");
  }

  @Test
  public void testInvalidConstructors() {

    // model as null
    try {
      controller = new ImageControllerImpl(null, view, in);
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any parameters as null", e.getMessage());
    }

    // view as null
    try {
      controller = new ImageControllerImpl(model, null, in);
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any parameters as null", e.getMessage());
    }

    // readable as null
    try {
      controller = new ImageControllerImpl(model, view, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any parameters as null", e.getMessage());
    }

    // all parameters as null
    try {
      controller = new ImageControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any parameters as null", e.getMessage());
    }
  }

  @Test
  public void testQuit() {
    init();
    in = new StringReader("quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testLoad() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm load mj res/michael-jordan.jpg quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Image has been loaded\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void test2Load() {
    init();
    in = new StringReader("load mj res/michael-jordan.png quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testInvalidLoad() {
    init();
    in = new StringReader("load mj res/k.ppm load mj res/michael-jordan.JPEG quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro +
            "Input right command\n" +
            "Input right command\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testSave() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm load mj-Image res/MJ.JPEG"
            + System.lineSeparator()
            + "save mj res/MJCopy.ppm quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Image has been loaded\n" +
            "Image has been saved\n" +
            "Image has been saved\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testSaveAfterCommands() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm" + System.lineSeparator()
            + "horizontal-flip mj mjHorizontalFlip" + System.lineSeparator()
            + "load MJ res/michael-jordan.jpg" + System.lineSeparator()
            + "horizontal-flip MJ MJHorizontalFlip" + System.lineSeparator()
            + "save MJHorizontalFlip res/MJHorizontal.ppm quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Flipped image horizontally\n" +
            "Image has been loaded\n" +
            "Flipped image horizontally\n" +
            "Image has been saved\n" +
            "Image has been saved\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testHorizontalFlip() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm" + System.lineSeparator()
            + "load MJ res/michael-jordan.jpg" + System.lineSeparator()
            + "horizontal-flip MJ MJHorizontalFlip" + System.lineSeparator()
            + "horizontal-flip mj MJHorizontalFlip quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Flipped image horizontally\n" +
            "Flipped image horizontally\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testVerticalFlip() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm" + System.lineSeparator()
            + "load MJ res/michael-jordan.jpg" + System.lineSeparator()
            + "vertical-flip MJ MJVerticalFlip" + System.lineSeparator()
            + "vertical-flip mj MJVerticalFlip quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Image has been loaded\n" +
            "Flipped image vertically\n" +
            "Flipped image vertically\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testRedComponent() {
    init();
    in = new StringReader("load mj res/michael-jordan.ppm" + System.lineSeparator()
            + "red-component mj mj-redComponent quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied red-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testGreenComponent() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "green-component MJ greenComponentMJ quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied green-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testBlueComponent() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "blue-component MJ blueComponentMJ quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied blue-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testValueComponent() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "value-component MJ redComponentMJ quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied value-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }


  @Test
  public void testIntensityComponent() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "intensity-component MJ redComponentMJ quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied intensity-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testLumaComponent() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "luma-component MJ redComponentMJ quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Applied luma-component to image\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testInvalidCommand() {
    init();
    in = new StringReader("random quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Invalid command\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testInvalidCommand2() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm random command load koala " +
            "res/example5000.ppm quit");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Invalid command\n" +
            "Invalid command\n" +
            "Image has been loaded\n" +
            "Exiting program");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testEverything() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "blue-component MJ OurBlueMJ" + System.lineSeparator()
            + "vertical-flip OurBlueMJ OurBlueVerticalMJ" + System.lineSeparator()
            + "save OurBlueVerticalMJ res/OurBlueVerticalMJ.ppm" + System.lineSeparator()
            + "green-component MJ GreenMJ" + System.lineSeparator()
            + "value-component GreenMJ OurGreenValueMJ" + System.lineSeparator());
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro +
            "Image has been loaded\n" +
            "Applied blue-component to image\n" +
            "Flipped image vertically\n" +
            "Image has been saved\n" +
            "Applied green-component to image\n" +
            "Applied value-component to image\n");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testNoInputs() {
    init();
    in = new StringReader("");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro);
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testNoMoreInputs() {
    init();
    in = new StringReader("load ");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Missing inputs\n" +
            "Input right command\n");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

  @Test
  public void testNoMoreInputs2() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm save ");
    controller = new ImageControllerImpl(model, view, in);
    expectedOutput = new StringBuilder(intro + "Image has been loaded\n" +
            "Missing inputs\n" +
            "Input right command\n");
    controller.start();
    assertEquals(expectedOutput.toString(), output.toString().replace("\r", ""));
  }

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

  @Test
  public void testControllerInputs() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "blue-component MJ OurBlueMJ" + System.lineSeparator()
            + "vertical-flip OurBlueMJ OurBlueVerticalMJ" + System.lineSeparator()
            + "save OurBlueVerticalMJ res/OurBlueVerticalMJ.ppm" + System.lineSeparator()
            + "green-component MJ GreenMJ" + System.lineSeparator()
            + "value-component GreenMJ OurGreenValueMJ" + System.lineSeparator());
    StringBuilder expectedLog = new StringBuilder(
            "loaded in MJ\n" +
                    "blue-component on MJ\n" +
                    "vertical-flip on OurBlueMJ\n" +
                    "saved OurBlueVerticalMJ in res/OurBlueVerticalMJ.ppm\n" +
                    "green-component on MJ\n" +
                    "value-component on GreenMJ\n");
    StringBuilder log = new StringBuilder();
    MockModel mockModel = new MockModel(log);
    controller = new ImageControllerImpl(mockModel, view, in);
    controller.start();
    assertEquals(expectedLog.toString(), log.toString().replace("\r", ""));
  }

  @Test
  public void testController2Inputs() {
    init();
    in = new StringReader("load MJ res/michael-jordan.ppm" + System.lineSeparator()
            + "blue-component MJ OurBlueMJ" + System.lineSeparator()
            + "vertical-flip OurBlueMJ OurBlueVerticalMJ" + System.lineSeparator()
            + "save OurBlueVerticalMJ res/OurBlueVerticalMJ.ppm" + System.lineSeparator()
            + "green-component MJ GreenMJ" + System.lineSeparator()
            + "value-component GreenMJ OurGreenValueMJ" + System.lineSeparator()
            + "load MJ res/michael-jordan.jpg" + System.lineSeparator()
            + "blur MJ MJ-blur" + System.lineSeparator()
            + "sharpen MJ MJ-sharpen" + System.lineSeparator()
            + "greyscale MJ MJ-greyscale" + System.lineSeparator()
            + "sepiaTone MJ MJ-sepiaTone" + System.lineSeparator());
    StringBuilder expectedLog = new StringBuilder(
            "loaded in MJ\n" +
                    "blue-component on MJ\n" +
                    "vertical-flip on OurBlueMJ\n" +
                    "saved OurBlueVerticalMJ in res/OurBlueVerticalMJ.ppm\n" +
                    "green-component on MJ\n" +
                    "value-component on GreenMJ\n");
    StringBuilder log = new StringBuilder();
    MockModel mockModel = new MockModel(log);
    controller = new ImageControllerImpl(mockModel, view, in);
    controller.start();
    assertEquals(expectedLog.toString(), log.toString().replace("\r", ""));
  }

  @Test
  public void testCommandFromFile() {
    init();
    Readable in = null;
    StringBuilder expectedOut = new StringBuilder();
    try {
      in = new FileReader("res/script-commands.text");
    } catch (FileNotFoundException e) {
      fail("Input file not present");
    }
    expectedOut.append(intro + System.lineSeparator() +
            "Image has been loaded\n" +
            "Image has been loaded\n" +
            "Applied red-component to image\n" +
            "Applied red-component to image\n" +
            "Applied green-component to image\n" +
            "Applied green-component to image\n" +
            "Applied blue-component to image\n" +
            "Applied blue-component to image\n" +
            "Applied value-component to image\n" +
            "Applied value-component to image\n" +
            "Applied intensity-component to image\n" +
            "Applied intensity-component to image\n" +
            "Applied luma-component to image\n" +
            "Applied luma-component to image\n" +
            "Flipped image horizontally\n" +
            "Flipped image horizontally\n" +
            "Flipped image vertically\n" +
            "Flipped image vertically\n" +
            "Increased brightness\n" +
            "Increased brightness\n" +
            "Increased brightness\n" +
            "Increased brightness\n" +
            "blurred image\n" +
            "blurred image\n" +
            "sharpened image\n" +
            "sharpened image\n" +
            "grey scaled image\n" +
            "grey scaled image\n" +
            "sepia toned image\n" +
            "sepia toned image\n" +
            "Image has been saved\n" +
            "Image has been saved\n" +
            "Exiting program");
    Appendable output = new StringBuilder();
    ImageModel model = new ImageModelImpl();
    ImageView game0View = new ImageTextView(model, output);
    ImageController controller = new ImageControllerImpl(model, game0View, in);
    controller.start();
    assertEquals(expectedOut.toString().replace("\r", ""),
            output.toString().replace("\r", ""));

  }
}
