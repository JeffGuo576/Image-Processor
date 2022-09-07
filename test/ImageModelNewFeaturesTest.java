import org.junit.Test;

import java.util.ArrayList;

import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for image model continued. Tests for the new features and new imports and
 * exports.
 */
public class ImageModelNewFeaturesTest {
  ImageModel model = new ImageModelImpl();
  ImageModel model1 = new ImageModelImpl();
  ArrayList<ArrayList<Pixel>> example5000list;
  ArrayList<Pixel> row0;
  ArrayList<Pixel> row1;

  private void init() {
    example5000list = new ArrayList<>();
    row0 = new ArrayList<Pixel>();
    row1 = new ArrayList<Pixel>();
    row0.add(new Pixel(100, 100, 130, 255));
    row0.add(new Pixel(40, 40, 150, 255));
    row1.add(new Pixel(50, 50, 100, 255));
    row1.add(new Pixel(60, 60, 130, 255));
    example5000list.add(row0);
    example5000list.add(row1);
  }

  @Test
  public void testLoadImage() {
    model.load("MJ", "res/michael-jordan.jpg");
    model1.load("MJ", "res/MJ.JPEG");
    assertEquals(true, model.getImages().containsKey("MJ"));
    assertEquals(true, model1.getImages().containsKey("MJ"));
  }

  @Test
  public void testLoadImageException() {
    try {
      model.load(null, "res/koala.jpg");
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.load("MJ", null);
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.load(null, null);
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.load("MJ", "res/koala.jpg");
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("File res/koala.jpg not found!", e.getMessage());
    }
  }

  @Test
  public void testSaveImage() {
    model.load("MJ", "res/michael-jordan.jpg");
    assertEquals(true, model.getImages().containsKey("MJ"));
    model.save("MJ", "res/MJ.JPEG");
  }

  @Test
  public void testSaveImageException() {
    model.load("MJ", "res/michael-jordan.jpg");
    try {
      model.save(null, "res/koala.jpg");
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.save("MJ", null);
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.save(null, null);
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Invalid file path/destination or filename!", e.getMessage());
    }

    try {
      model.save("MJ-", "res/michael-jordan.jpg");
      fail("File does not exist. Should've thrown exception");
    } catch (IllegalStateException e) {
      assertEquals("Cannot find file/image", e.getMessage());
    }
  }

  @Test
  public void testSaveImageAsPpm() {
    model.load("mj-ppm", "res/michael-jordan.ppm");
    assertEquals(true, model.getImages().containsKey("mj-ppm"));
    model.save("mj-ppm", "res/mj-ppm-to-image.jpg");
    model.load("mj-vertical-ppm", "res/michael-vertical.ppm");
    assertEquals(true, model.getImages().containsKey("mj-vertical-ppm"));
    model.save("mj-vertical-ppm", "res/mj-vertical-ppm-to-image.jpg");
    model.load("mj-jpg", "res/michael-jordan.jpg");
    assertEquals(true, model.getImages().containsKey("mj-jpg"));
    model.save("mj-jpg", "res/mj-image-to-ppm.ppm");
    model1.load("mj-remake-image", "res/mj-image-to-ppm.ppm");
    assertEquals(true, model1.getImages().containsKey("mj-remake-image"));
    model1.save("mj-remake-image", "res/mj-remake-image.jpg");
  }

  @Test
  public void testBlur() {
    model.load("MJ", "res/michael-jordan.jpg");
    model.blur("MJ", "MJ-blur");
    model.save("MJ-blur", "res/MJ-blur.jpg");
    model1.load("MJ-Original", "res/MJ-blur.jpg");
    //Test cannot compare because of lossy compression from jpg
    assertEquals(model1.getImages().get("MJ-Original"), model.getImages().get("MJ-blur"));


  }

  @Test
  public void testSharpen() {
    // test sharpen
    init();

    model.load("MJ", "res/michael-jordan.jpg");
    model.sharpen("MJ", "MJ-sharpen");
    model.load("MJ-Original", "res/michael-sharpen.jpg");
    //Test cannot compare because of lossy compression from jpg
    assertEquals(model1.getImages().get("MJ-Original"), model.getImages().get("MJ-sharpen"));

  }

  @Test
  public void testGreyScale() {
    model.load("MJ", "res/michael-jordan.jpg");
    model.greyscale("MJ", "MJ-greyscale");
    model.load("MJ-Original", "res/MJ-greyscale.jpg");
    //Test cannot compare because of lossy compression from jpg
    assertEquals(model1.getImages().get("MJ-Original"), model.getImages().get("MJ-greyscale"));

  }

  @Test
  public void testSepiaTone() {
    model.load("MJ", "res/michael-jordan.jpg");
    model.sepiaTone("MJ", "MJ-sepia");
    model.load("MJ-Original", "res/MJ-sepia.jpg");
    //Test cannot compare because of lossy compression from jpg
    assertEquals(model1.getImages().get("MJ-Original"), model.getImages().get("MJ-sepia"));
  }

  @Test
  public void testSepiaToneException() {
    model.load("MJ", "res/michael-jordan.jpg");
    // test for loading the file without a name.
    try {
      model.sepiaTone(null, "MJ-red");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      model.sepiaTone("MJ", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      model.sepiaTone(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testBlurException() {
    model.load("MJ", "res/michael-jordan.jpg");

    // test for loading the file without a name.
    try {
      model.blur(null, "MJ-red");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      model.blur("MJ", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      model.blur(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testSharpenException() {
    model.load("MJ", "res/michael-jordan.jpg");

    // test for loading the file without a name.
    try {
      model.blur(null, "MJ-red");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      model.blur("MJ", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      model.blur(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testGreyScaleException() {
    model.load("MJ", "res/michael-jordan.jpg");

    // test for loading the file without a name.
    try {
      model.greyscale(null, "MJ-red");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      model.greyscale("MJ", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      model.greyscale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testConvertPpmToJpg() {
    model.load("mj-ppm-to-jpg", "res/michael-jordan.ppm");
    assertEquals(true, model.getImages().containsKey("mj-ppm-to-jpg"));
    model.save("mj-ppm-to-jpg", "res/MJ-ppm-to-jpg.jpg");
  }

  @Test
  public void testConvertJpgToPpm() {
    model.load("mj-jpg-to-ppm", "res/michael-jordan.jpg");
    assertEquals(true, model.getImages().containsKey("mj-jpg-to-ppm"));
    model.save("mj-jpg-to-ppm", "res/MJ-jpg-to-ppm.ppm");
  }
}
