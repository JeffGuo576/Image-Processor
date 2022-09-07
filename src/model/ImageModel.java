package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents an Image model that is in charge of all the functionality of all the commands
 * this image processor will perform.
 */
public interface ImageModel {

  /**
   * Loads an image from specified path and refer to it as given image name. Can load a ppm file
   * and any image file.
   *
   * @param filename is the image name that is being referred to in the program
   * @param path     is the image path.
   * @throws IllegalStateException if arguments are null.
   */
  void load(String filename, String path) throws IllegalStateException;

  /**
   * Saves a file with the given name referred to as in the program to a specified path which
   * should include the name of the file. It can save a file as a ppm file or and image file.
   *
   * @param filename is the image name that is being referred to in the program
   * @param path     is the image path.
   * @throws IllegalStateException if arguments are null.
   */
  void save(String filename, String path) throws IllegalStateException;

  /**
   * Changes green and blue components to the same value as the red, loads this update
   * and refer it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void redGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Changes red and blue components to the same value as the green, loads this update
   * and refer it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void greenGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Changes red and green components to the same value as the blue, loads this update
   * and refer it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void blueGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Changes RGB components to the same value as the max value of all the components, loads
   * this update and refer it to as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void valueGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Changes RGB components to the same value as the intensity value of all the components,
   * loads this update and refer it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void intensityGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Changes RGB components to the same value as the luma value of all the components,
   * loads this update and refer it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void lumaGreyScale(String filename, String desName) throws IllegalStateException;

  /**
   * Flips the specified referred file name in the program horizontally,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void horizontalFlip(String filename, String desName) throws IllegalStateException;

  /**
   * Flips the specified referred file name in the program vertically,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   * @throws IllegalStateException if arguments are null.
   */
  void verticalFlip(String filename, String desName) throws IllegalStateException;

  /**
   * Brightens or darkens referred file in the program, loads this update and refers it
   * as given destination name.
   *
   * @param filename  is the image name that is being referred to in the program.
   * @param desName   is the destination path.
   * @param increment is the brightness value the specified referred file will be increased
   *                  or decrease by.
   * @throws IllegalStateException if arguments are null.
   */
  void brighten(String filename, String desName, int increment)
          throws IllegalStateException;

  /**
   * Gets the map of the file.
   *
   * @return the copy of the map or images field.
   */
  Map<String, ArrayList<ArrayList<Pixel>>> getImages();

  /**
   * It clears the images field which is a map.
   */
  void clearImages();

  /**
   * Blurs the specified referred file name in the program,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   */
  void blur(String filename, String desName);

  /**
   * Sharpens the specified referred file name in the program,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   */
  void sharpen(String filename, String desName);

  /**
   * Grey scales the specified referred file name in the program,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   */
  void greyscale(String filename, String desName);

  /**
   * Applies sepia tone on the specified referred file name in the program,
   * loads this update and refers it as given destination name.
   *
   * @param filename is the image name that is being referred to in the program.
   * @param desName  is the destination path.
   */
  void sepiaTone(String filename, String desName);

  BufferedImage createImage(ArrayList<ArrayList<Pixel>> imagePixels);
}