package model;

/**
 * Represents a pixel of an image. This interface gives the power to modify the r,
 * g and b of the pixels.
 */
public interface IPixel {

  /**
   * It gets the value of red.
   *
   * @return the value of the red.
   */
  int getR();

  /**
   * It gets the value of green.
   *
   * @return the value of the green.
   */
  int getG();

  /**
   * It gets the value of blue.
   *
   * @return the value of the blue.
   */
  int getB();

  /**
   * It gets the max color value of the image.
   *
   * @return the max color value of the image.
   */
  int getMaxValue();

  /**
   * Changes the red value of the pixel.
   *
   * @param num value.
   */
  void setR(int num);

  /**
   * Changes the green value of the pixel.
   *
   * @param num value.
   */
  void setG(int num);

  /**
   * Changes the blue value of the pixel.
   *
   * @param num value.
   */
  void setB(int num);

  /**
   * Checks if the object is equal to the given object.
   *
   * @param o object.
   * @return true if the object is equal to the given object.
   */
  boolean equals(Object o);

  /**
   * It creates a hash code of the object.
   *
   * @return hash code of the object.
   */
  int hashCode();

  /**
   * Finds the max value component in given pixel.
   *
   * @return the max value component.
   */
  int value();

  /**
   * Calculates the intensity of given pixel. In other words the average value of
   * the components.
   *
   * @return the average value of the components.
   */
  int intensity();

  /**
   * Calculates the luma of given pixel.
   *
   * @return rounded luma calculation.
   */
  int luma();

}
