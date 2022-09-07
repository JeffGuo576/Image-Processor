import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the pixels. Which represent the pixels of an image.
 */
public class PixelTest {

  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;

  private void init() {
    pixel1 = new Pixel(143, 24, 0, 255);
    pixel2 = new Pixel(34, 154, 3, 255);
    pixel3 = new Pixel(143,24, 0, 255);
  }

  @Test
  public void testExceptionPixel() {
    try {
      pixel1 = new Pixel(-1, 143, 2, 255);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Cannot have a negative value", e.getMessage());
    }

    try {
      pixel1 = new Pixel(1, -143, 2, 255);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Cannot have a negative value", e.getMessage());
    }

    try {
      pixel1 = new Pixel(1, 143, -2, 255);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Cannot have a negative value", e.getMessage());
    }

    try {
      pixel1 = new Pixel(-1, -143, -2, 255);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Cannot have a negative value", e.getMessage());
    }
  }

  @Test
  public void testPixel() {
    init();
    assertTrue(pixel1.equals(pixel1));
    assertTrue(pixel1.equals(pixel3));
    assertFalse(pixel1.equals(pixel2));
    assertFalse(pixel2.equals(pixel3));
  }

  @Test
  public void testHashCode() {
    init();
    assertEquals(167, pixel1.hashCode());
    assertEquals(953, pixel2.hashCode());
    assertEquals(167, pixel3.hashCode());
  }
}
