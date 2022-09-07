import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import model.ImageModelImpl;
import view.ImageTextView;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the visual portion of the Image Processor.
 */
public class ImageViewTest {

  @Test
  public void testViewInvalidConstructor() {

    // test view constructor with null model
    try {
      ImageView view = new ImageTextView(null);
      fail("Did not throw exception!");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have model as null", e.getMessage());
    }

    try {
      ImageView view = new ImageTextView(null, System.out);
      fail("Did not throw exception!");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any arguments as null", e.getMessage());
    }

    // test view constructor with null appendable
    try {
      ImageView view = new ImageTextView(new ImageModelImpl(), null);
      fail("Did not throw exception!");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any arguments as null", e.getMessage());
    }

    // test view constructor with null arguments
    try {
      ImageView view = new ImageTextView(null, null);
      fail("Did not throw exception!");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have any arguments as null", e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() {
    try {
      ImageTextView view = new ImageTextView(new ImageModelImpl());
      view.renderMessage("hello");
    } catch (IOException e) {
      assertEquals("Transmission of board to provided data destination is invalid",
              e.getMessage());
    }
  }

  @Test
  public void testRenderMessageException() {
    try {
      ImageView view = new ImageTextView(new ImageModelImpl(), new FileWriter("text.txt"));
      view.renderMessage("hello");
    } catch (IOException e) {
      assertEquals("Transmission of board to provided data destination is invalid"
              , e.getMessage());
    }
  }
}
