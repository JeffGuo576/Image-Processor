package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Represents an image model implementation.
 */
public class ImageModelImpl implements ImageModel {

  private Map<String, ArrayList<ArrayList<Pixel>>> images;

  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void load(String filename, String path) throws IllegalStateException {
    this.isInvalid(filename, path);
    Scanner sc = null;
    BufferedImage image = null;

    String type = this.getType(path);//

    if (type.equals("ppm")) {
      try {
        sc = new Scanner(new FileInputStream(path));
        this.loadPpm(sc, filename);
      } catch (FileNotFoundException e) {
        throw new IllegalStateException("File " + path + " not found!");
      }
    } else {
      try {
        image = ImageIO.read(new File(path));
        this.loadImage(image, filename);
      } catch (IOException e2) {
        throw new IllegalStateException("File " + path + " not found!");
      }
    }
  }

  /**
   * Loads an Image to the program.
   *
   * @param image    is the image being loaded in.
   * @param filename is the name of the image the program will refer it as.
   */
  private void loadImage(BufferedImage image, String filename) {
    ArrayList<ArrayList<Pixel>> imagePixels = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < image.getHeight(); rowIndex++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int colIndex = 0; colIndex < image.getWidth(); colIndex++) {
        Color color = new Color(image.getRGB(colIndex, rowIndex));//rowIndex
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        row.add(new Pixel(r, g, b, 255));
      }
      imagePixels.add(row);
    }
    this.images.put(filename, imagePixels);
  }


  /**
   * Loads a ppm file to the program.
   *
   * @param sc       is a scanner that scans the file.
   * @param filename si the file name the loaded file will be referred to as in this program.
   */
  private void loadPpm(Scanner sc, String filename) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ArrayList<ArrayList<Pixel>> imagePixels = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < height; rowIndex++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        row.add(new Pixel(r, g, b, maxValue));
      }
      imagePixels.add(row);
    }

    // adds to map of images
    this.images.put(filename, imagePixels);
  }

  /**
   * Gets the type of file of the path.
   *
   * @param path is the image path.
   * @return the type of the path in a string.
   */
  private String getType(String path) {
    String type = "";
    int index = 0;

    while (index < path.length()) {
      if (path.charAt(index) == '.') {
        index++;
        while (index < path.length()) {
          type += path.charAt(index);
          index++;
        }
      }
      index++;
    }
    return type;
  }

  @Override
  public void save(String filename, String path) throws IllegalStateException {
    this.isInvalid(filename, path);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);

    String type = this.getType(path);

    if (type.equals("ppm")) {
      this.savePpm(path, imagePixels);
    } else {
      this.saveImage(path, imagePixels, type);
    }
  }

  /**
   * Saves a file referred from the program as ppm.
   *
   * @param path        is the image path.
   * @param imagePixels is the referred file in a form of 2D list of pixels.
   */
  private void savePpm(String path, ArrayList<ArrayList<Pixel>> imagePixels) {
    FileWriter fileWriter;
    try {
      fileWriter = new FileWriter(path);
      fileWriter.write("P3" + System.lineSeparator());
      fileWriter.write("# Plain ppm" + System.lineSeparator());
      fileWriter.write(imagePixels.get(0).size() + " " + imagePixels.size()
              + System.lineSeparator());
      fileWriter.write((int) imagePixels.get(0).get(0).getMaxValue() + System.lineSeparator());
      for (int row = 0; row < imagePixels.size(); row++) {
        for (int col = 0; col < imagePixels.get(0).size(); col++) {
          Pixel pixel = imagePixels.get(row).get(col);
          fileWriter.write((int) pixel.getR() + System.lineSeparator());
          fileWriter.write((int) pixel.getG() + System.lineSeparator());
          fileWriter.write((int) pixel.getB() + System.lineSeparator());
        }
      }
      fileWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("Invalid path");
    }
  }

  /**
   * Saves an Image referred from the program.
   *
   * @param path        is the image path.
   * @param imagePixels is the referred file in a form of 2D list of pixels.
   * @param type        is the type the image will be saved as.
   */
  private void saveImage(String path, ArrayList<ArrayList<Pixel>> imagePixels, String type) {
    BufferedImage image = this.createImage(imagePixels);
    File file = new File(path);
    try {
      ImageIO.write(image, type, file);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot save image");
    }
  }

  @Override
  public BufferedImage createImage(ArrayList<ArrayList<Pixel>> imagePixels) {
    BufferedImage image = new BufferedImage(imagePixels.get(0).size(), imagePixels.size(),
            TYPE_INT_RGB);

    for (int row = 0; row < imagePixels.size(); row++) {
      for (int col = 0; col < imagePixels.get(0).size(); col++) {
        Pixel pixel = imagePixels.get(row).get(col);
        Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
        image.setRGB(col, row, color.getRGB());
      }
    }
    return image;
  }

  @Override
  public void redGreyScale(String filename, String desName) {
    this.greyScale("red", filename, desName);
  }

  @Override
  public void greenGreyScale(String filename, String desName) {
    this.greyScale("green", filename, desName);
  }

  @Override
  public void blueGreyScale(String filename, String desName) {
    this.greyScale("blue", filename, desName);
  }

  @Override
  public void valueGreyScale(String filename, String desName) {
    this.greyScale("value", filename, desName);
  }

  @Override
  public void intensityGreyScale(String filename, String desName) {
    this.greyScale("intensity", filename, desName);
  }

  @Override
  public void lumaGreyScale(String filename, String desName) {
    this.greyScale("luma", filename, desName);
  }

  @Override
  public void horizontalFlip(String filename, String desName) {
    this.isInvalid(filename, desName);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);
    for (int row = 0; row < imagePixels.size(); row++) {
      Collections.reverse(imagePixels.get(row));
    }
    this.images.put(desName, imagePixels);
  }

  @Override
  public void verticalFlip(String filename, String desName) {
    this.isInvalid(filename, desName);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);
    Collections.reverse(imagePixels);
    this.images.put(desName, imagePixels);
  }

  @Override
  public void brighten(String filename, String desName, int increment) {
    this.isInvalid(filename, desName);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);
    for (int row = 0; row < imagePixels.size(); row++) {
      for (int col = 0; col < imagePixels.get(row).size(); col++) {
        Pixel pixel = imagePixels.get(row).get(col);
        pixel.setR(this.clamp(pixel.getR() + increment, pixel.getMaxValue()));
        pixel.setG(this.clamp(pixel.getG() + increment, pixel.getMaxValue()));
        pixel.setB(this.clamp(pixel.getB() + increment, pixel.getMaxValue()));
      }
    }
    this.images.put(desName, imagePixels);
  }

  /**
   * Finds the max value component in given pixel.
   *
   * @param pixel is a pixel of an image.
   * @return the max value component.
   */
  private int value(Pixel pixel) {
    return Math.max(Math.max(pixel.getR(), pixel.getG()), pixel.getB());
  }

  /**
   * Calculates the intensity of given pixel. In other words the average value of
   * the components.
   *
   * @param pixel is a pixel of an image.
   * @return the average value of the components.
   */
  private int intensity(Pixel pixel) {
    return ((pixel.getR() + pixel.getG() + pixel.getB()) / 3);
  }

  /**
   * Calculates the luma of given pixel.
   *
   * @param pixel is a pixel of an image.
   * @return rounded luma calculation.
   */
  private int luma(Pixel pixel) {
    return (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG() + 0.0722 * pixel.getB());
  }

  /**
   * Gets the map of images in the image processor.
   * @return returns map of images.
   */
  public Map<String, ArrayList<ArrayList<Pixel>>> getImages() {
    Map<String, ArrayList<ArrayList<Pixel>>> copy = new HashMap<>();
    for (String key : this.images.keySet()) {
      ArrayList<ArrayList<Pixel>> imagePixels = this.images.get(key);
      copy.put(key, imagePixels);
    }
    return copy;
  }

  public void clearImages() {
    this.images.clear();
  }

  /**
   * Checks if the referred file in this program exists.
   *
   * @param filename the name of the file.
   * @return returns the pixels of the file.
   * @throws IllegalStateException if the file does not exist.
   */
  private ArrayList<ArrayList<Pixel>> doesFileExist(String filename) throws IllegalStateException {
    ArrayList<ArrayList<Pixel>> getImagePixels = this.images.get(filename);
    if (getImagePixels == null) { // imagePixels
      throw new IllegalStateException("Cannot find file/image");
    } else {
      return this.copy(getImagePixels);
    }
  }

  private ArrayList<ArrayList<Pixel>> copy(ArrayList<ArrayList<Pixel>> getImagePixels) {
    ArrayList<ArrayList<Pixel>> imagePixels = new ArrayList<>();
    for (ArrayList<Pixel> row : getImagePixels) {
      ArrayList<Pixel> rowList = new ArrayList<>();
      for (Pixel pixel : row) {
        Pixel newPixel = new Pixel(pixel.getR(), pixel.getG(), pixel.getB(), pixel.getMaxValue());
        rowList.add(newPixel);
      }
      imagePixels.add(rowList);
    }
    return imagePixels;
  }

  /**
   * Checks if given filename, path, or destination name is a null (invalid).
   *
   * @param filename      the name of the file.
   * @param pathOrDesName is the path or destination name of a file.
   * @throws IllegalStateException if filename or pathOrDesName are null.
   */
  private void isInvalid(String filename, String pathOrDesName) throws IllegalStateException {
    if (pathOrDesName == null || filename == null) {
      throw new IllegalStateException("Invalid file path/destination or filename!");
    }
  }

  /**
   * Creates a kernel with certain values depending on the given feature.
   *
   * @param feature is either blur, sharpen, greyscale, or sepia that is applied to an image.
   * @return a kernel based on the given feature.
   */
  private Double[][] kernel(Feature feature) {
    Double[][] kernel = null;
    switch (feature) {
      case Blur:
        kernel = new Double[][]{
                {0.0625, 0.125, 0.0625},
                {0.125, 0.25, 0.125},
                {0.0625, 0.125, 0.0625}
        };
        break;
      case Sharpen:
        kernel = new Double[][]{
                {-0.125, -0.125, -0.125, -0.125, -0.125},
                {-0.125, 0.25, 0.25, 0.25, -0.125},
                {-0.125, 0.25, 1.0, 0.25, -0.125},
                {-0.125, 0.25, 0.25, 0.25, -0.125},
                {-0.125, -0.125, -0.125, -0.125, -0.125}
        };
        break;
      case GreyScale:
        kernel = new Double[][]{
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}
        };
        break;
      case SepiaTone:
        kernel = new Double[][]{
                {0.393, 0.769, 0.189},
                {0.349, 0.686, 0.168},
                {0.275, 0.534, 0.131}
        };
        break;
      default:
        break;
    }
    return kernel;
  }

  @Override
  public void blur(String filename, String desName) {
    this.applyFeature(filename, desName, "blur");
  }

  @Override
  public void sharpen(String filename, String desName) {
    this.applyFeature(filename, desName, "sharpen");
  }

  /**
   * Applies given feature to referred file name in the program.
   *
   * @param filename is the referred file name in the program.
   * @param desName  is the desired name the program will refer to.
   * @param feature  is either blur, sharpen, greyscale, or sepia that is applied to an image.
   */
  private void applyFeature(String filename, String desName, String feature) {
    this.isInvalid(filename, desName);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);
    ArrayList<ArrayList<Pixel>> edited = this.applyFeatureHelper(imagePixels, feature);

    this.images.put(desName, edited);
  }

  /**
   * Decides which kernel to use to apply the specified feature to the image and gives the
   * results of the image after applying the feature.
   *
   * @param imagePixels is the referred file in a form of 2D list of pixels.
   * @param feature     is either blur, sharpen, greyscale, or sepia.
   * @return the 2D array list of pixels of the image after applying specified feature.
   */
  private ArrayList<ArrayList<Pixel>> applyFeatureHelper(ArrayList<ArrayList<Pixel>> imagePixels,
                                                         String feature) {
    Double[][] kernel = null;
    Pixel[][] kernelPixels;
    ArrayList<ArrayList<Pixel>> edited = new ArrayList<>();
    int innerLength = 0;
    switch (feature) {
      case "blur":
        kernel = this.kernel(Feature.Blur);
        innerLength = 1;
        break;
      case "sharpen":
        kernel = this.kernel(Feature.Sharpen);
        innerLength = 2;
        break;
      case "greyscale":
        kernel = this.kernel(Feature.GreyScale);
        innerLength = 1;
        break;
      case "sepia":
        kernel = this.kernel(Feature.SepiaTone);
        innerLength = 1;
        break;
      default:
        break;
    }

    kernelPixels = new Pixel[kernel.length][kernel[0].length];
    edited = this.editImage(imagePixels, innerLength, feature, kernelPixels, kernel);
    return edited;

  }

  /**
   * Edits referred image or file by applying a feature. Changes all pixels' components.
   *
   * @param imagePixels  is the referred file in a form of 2D list of pixels.
   * @param innerLength  is the length between the center pixel and the edges of the kernel
   * @param feature      is either blur, sharpen, greyscale, or sepia.
   * @param kernelPixels is the 2D list of pixels that are within the kernel.
   * @param kernel       is the 2D list of kernel values.
   * @return the edited image or file in the form of a 2D array list of Pixels.
   */
  private ArrayList<ArrayList<Pixel>> editImage(ArrayList<ArrayList<Pixel>> imagePixels,
                                                int innerLength, String feature,
                                                Pixel[][] kernelPixels, Double[][] kernel) {

    ArrayList<ArrayList<Pixel>> edited = new ArrayList<>();
    for (int row = 0; row < imagePixels.size(); row++) {
      ArrayList<Pixel> featuredRow = new ArrayList<>();
      for (int col = 0; col < imagePixels.get(0).size(); col++) {
        Pixel pixel = imagePixels.get(row).get(col);

        // get pixels within kernel
        int x = 0;
        int y = 0;
        for (int curRow = -innerLength; curRow <= innerLength; curRow++) {
          for (int curCol = -innerLength; curCol <= innerLength; curCol++) {
            // add pixels to kernelPixels
            if (!isOutOfBounds(imagePixels, row + curRow, col + curCol)) {
              kernelPixels[x][y] = imagePixels.get(row + curRow).get(col + curCol);
            }
            y++;
          }
          x++;
          y = 0;
        }

        Pixel newPixel = this.calculate(kernel, kernelPixels, pixel, feature);
        featuredRow.add(newPixel);
      }
      edited.add(featuredRow);
    }
    return edited;
  }

  /**
   * Calculates the new color values for the new Pixel depending on which feature is being applied.
   *
   * @param kernel       is the 2D list of kernel values.
   * @param kernelPixels is the 2D list of pixels that are within the kernel.
   * @param pixel        is the center pixel within the kernel.
   * @param feature      is either blur, sharpen, greyscale, or sepia.
   * @return the new Pixel with calculated new components.
   */
  private Pixel calculate(Double[][] kernel, Pixel[][] kernelPixels, Pixel pixel, String feature) {
    double summationR = 0;
    double summationG = 0;
    double summationB = 0;
    //ArrayList<Double> rowKernelValues = new ArrayList<>();
    switch (feature) {
      case "blur":
      case "sharpen":
        // for every kernel cell
        for (int kRow = 0; kRow < kernel.length; kRow++) {
          for (int kCol = 0; kCol < kernel[0].length; kCol++) {
            double kernelValue = kernel[kRow][kCol];
            Pixel p = kernelPixels[kRow][kCol];
            if (p != null) {
              summationR = summationR + kernelValue * p.getR();
              summationG = summationG + kernelValue * p.getG();
              summationB = summationB + kernelValue * p.getB();
            }
          }
        }
        break;
      case "greyscale":
      case "sepia":
        summationR = kernel[0][0] * pixel.getR() + kernel[0][1] * pixel.getG()
                + kernel[0][2] * pixel.getB();
        summationG = kernel[1][0] * pixel.getR() + kernel[1][1] * pixel.getG()
                + kernel[1][2] * pixel.getB();
        summationB = kernel[2][0] * pixel.getR() + kernel[2][1] * pixel.getG()
                + kernel[2][2] * pixel.getB();
        break;
      default:
        break;
    }

    // clamp and round
    summationR = this.clamp((int) summationR, pixel.getMaxValue());
    summationG = this.clamp((int) summationG, pixel.getMaxValue());
    summationB = this.clamp((int) summationB, pixel.getMaxValue());
    Pixel newPixel = new Pixel((int) summationR, (int) summationG,
            (int) summationB, pixel.getMaxValue());
    return newPixel;
  }

  @Override
  public void greyscale(String filename, String desName) {
    this.applyFeature(filename, desName, "greyscale");
  }

  @Override
  public void sepiaTone(String filename, String desName) {
    this.applyFeature(filename, desName, "sepia");
  }

  /**
   * Makes sure a pixel's color value doesn't go negative or over max value.
   *
   * @param colorValue is the value of given color component.
   * @param maxValue   is the max value that the color value can be.
   * @return 0 if color value is negative.
   *         maxValue if color value is beyond the range of the max value.
   *         colorValue if color value is in range of 0 to max value.
   */
  private int clamp(int colorValue, int maxValue) {
    if (colorValue < 0) {
      return 0;
    } else if (colorValue > maxValue) {
      return maxValue;
    }
    return colorValue;
  }

  /**
   * Checks if given position (row and column) is out of bounds. By out-of-bounds meaning is beyond
   * the dimensions of the referred image or file.
   *
   * @param imagePixels is the referred file in a form of 2D list of pixels.
   * @param row         is the row of the pixel.
   * @param col         is the column of the pixel.
   * @return true if the position of pixel is out of bounds, otherwise false.
   */
  private boolean isOutOfBounds(ArrayList<ArrayList<Pixel>> imagePixels, int row, int col) {
    return row < 0 || row >= imagePixels.size()
            || col < 0 || col >= imagePixels.get(0).size();
  }

  /**
   * Applies greyscale to referred file name in this program, loads the update and
   * refer it as given destination name.
   *
   * @param way      is the type of greyscale way.
   * @param filename is the image name that is being referred to in the program
   * @param desName  is the destination path.
   * @throws IllegalStateException if the file does not exist.
   */
  private void greyScale(String way, String filename, String desName)
          throws IllegalStateException {
    this.isInvalid(filename, desName);
    ArrayList<ArrayList<Pixel>> imagePixels = this.doesFileExist(filename);
    for (int row = 0; row < imagePixels.size(); row++) {
      for (int col = 0; col < imagePixels.get(row).size(); col++) {
        Pixel pixel = imagePixels.get(row).get(col);
        switch (way) {
          case "red":
            pixel.setG(pixel.getR());
            pixel.setB(pixel.getR());
            break;
          case "green":
            pixel.setR(pixel.getG());
            pixel.setB(pixel.getG());
            break;
          case "blue":
            pixel.setR(pixel.getB());
            pixel.setG(pixel.getB());
            break;
          case "value":
            int maxvalue = pixel.value();
            pixel.setR(maxvalue);
            pixel.setG(maxvalue);
            pixel.setB(maxvalue);
            break;
          case "intensity":
            int intensity = pixel.intensity();
            pixel.setR(intensity);
            pixel.setG(intensity);
            pixel.setB(intensity);
            break;
          case "luma":
            int luma = pixel.luma();
            pixel.setR(luma);
            pixel.setG(luma);
            pixel.setB(luma);
            break;
          default:
            break;
        }
      }
    }
    this.images.put(desName, imagePixels);
  }
}