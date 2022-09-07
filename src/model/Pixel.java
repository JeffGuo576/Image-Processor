package model;

/**
 * A class that represents a pixel of an image that contains RGB color, and has a max color value.
 */
public class Pixel implements IPixel {

  private int r;
  private int g;
  private int b;
  private int maxValue;

  /**
   * Creates a pixel with RGB colors along with its max color value.
   *
   * @param r is the color red.
   * @param g is the color green.
   * @param b is the color blue.
   */
  public Pixel(int r, int g, int b, int maxValue) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || maxValue < 0) {
      throw new IllegalArgumentException("Cannot have a negative value");
    }
    this.r = r;
    this.g = g;
    this.b = b;
    this.maxValue = maxValue;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public void setR(int num) {
    this.r = num;
  }

  @Override
  public void setG(int num) {
    this.g = num;
  }

  @Override
  public void setB(int num) {
    this.b = num;
  }

  /**
   * Finds the max value component in given pixel.
   *
   * @return the max value component.
   */
  @Override
  public int value() {
    return Math.max(Math.max(this.getR(), this.getG()), this.getB());
  }

  /**
   * Calculates the intensity of given pixel. In other words the average value of
   * the components.
   *
   * @return the average value of the components.
   */
  @Override
  public int intensity() {
    return ((this.getR() + this.getG() + this.getB()) / 3);
  }

  /**
   * Calculates the luma of given pixel.
   *
   * @return rounded luma calculation.
   */
  @Override
  public int luma() {
    return (int) (0.2126 * this.getR() + 0.7152 * this.getG() + 0.0722 * this.getB());
  }


  /**
   * Checks if the object is equal to the given object.
   *
   * @param o object.
   * @return true if the object is equal to the given object.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Pixel)) {
      return false;
    }

    Pixel newPixel = (Pixel) o;
    return this.r == newPixel.getR() && this.g == newPixel.getG()
            && this.b == newPixel.getB() && this.maxValue == newPixel.getMaxValue();

  }

  @Override
  public int hashCode() {
    return this.r + this.g + this.b * this.maxValue;
  }
}