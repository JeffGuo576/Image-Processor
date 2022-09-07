import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the functional portion of the Image Processor.
 */
public class ImageModelTest {

  ImageModel michaelJordan;
  ImageModel michaelJordan2;
  ArrayList<ArrayList<Pixel>> example5000list;
  ArrayList<ArrayList<Pixel>> example5000list2;
  ArrayList<ArrayList<Pixel>> example5000list3;
  ArrayList<ArrayList<Pixel>> example5000list4;
  ArrayList<ArrayList<Pixel>> example5000list5;
  ArrayList<ArrayList<Pixel>> example5000list6;
  ArrayList<ArrayList<Pixel>> example5000list7;
  ArrayList<ArrayList<Pixel>> example5000list8;
  ArrayList<ArrayList<Pixel>> example5000list9;
  ArrayList<ArrayList<Pixel>> example5000list10;
  ArrayList<ArrayList<Pixel>> example5000list11;
  ArrayList<ArrayList<Pixel>> example5000list12;
  ArrayList<Pixel> row0;
  ArrayList<Pixel> row1;
  ArrayList<Pixel> row0hori;
  ArrayList<Pixel> row1hori;
  ArrayList<Pixel> row0red;
  ArrayList<Pixel> row1red;
  ArrayList<Pixel> row0green;
  ArrayList<Pixel> row1green;
  ArrayList<Pixel> row0blue;
  ArrayList<Pixel> row1blue;
  ArrayList<Pixel> row0intensity;
  ArrayList<Pixel> row1intensity;
  ArrayList<Pixel> row0luma;
  ArrayList<Pixel> row1luma;
  ArrayList<Pixel> row0bright;
  ArrayList<Pixel> row1bright;
  ArrayList<Pixel> row0dark;
  ArrayList<Pixel> row1dark;
  ArrayList<Pixel> row0value;
  ArrayList<Pixel> row1value;
  ImageModel example5000model;
  ImageModel example5000model2;
  ImageModel example5000model3;
  Map<String, ArrayList<ArrayList<Pixel>>> h1;
  Map<String, ArrayList<ArrayList<Pixel>>> h2;

  private void init() {

    h1 = new HashMap<>();
    h2 = new HashMap<>();
    row0 = new ArrayList<>();
    row1 = new ArrayList<>();
    row0hori = new ArrayList<>();
    row1hori = new ArrayList<>();
    example5000list = new ArrayList<>();
    example5000list2 = new ArrayList<>();
    example5000list3 = new ArrayList<>();
    example5000list7 = new ArrayList<>();

    row0.add(new Pixel(100, 100, 130, 255));
    row0.add(new Pixel(40, 40, 150, 255));
    row1.add(new Pixel(50, 50, 100, 255));
    row1.add(new Pixel(60, 60, 130, 255));

    row0hori.add(new Pixel(40, 40, 150, 255));
    row0hori.add(new Pixel(100, 100, 130, 255));
    row1hori.add(new Pixel(60, 60, 130, 255));
    row1hori.add(new Pixel(50, 50, 100, 255));

    //normal
    example5000list.add(row0);
    example5000list.add(row1);
    //horizontal
    example5000list2.add(row0hori);
    example5000list2.add(row1hori);
    //vertical
    example5000list3.add(row1);
    example5000list3.add(row0);
    //vertical & hori
    example5000list7.add(row1hori);
    example5000list7.add(row0hori);
    // expected
    h1.put("example", example5000list);
    example5000model = new ImageModelImpl();
    example5000model2 = new ImageModelImpl();
    example5000model3 = new ImageModelImpl();
    michaelJordan = new ImageModelImpl();
    michaelJordan2 = new ImageModelImpl();
  }

  private void setup() {

    example5000list4 = new ArrayList<>();
    example5000list5 = new ArrayList<>();
    example5000list6 = new ArrayList<>();
    example5000list8 = new ArrayList<>();
    example5000list9 = new ArrayList<>();
    example5000list10 = new ArrayList<>();
    example5000list11 = new ArrayList<>();
    example5000list12 = new ArrayList<>();
    //red
    row0red = new ArrayList<>();
    row1red = new ArrayList<>();
    row0red.add(new Pixel(100, 100, 100, 255));
    row0red.add(new Pixel(40, 40, 40, 255));
    row1red.add(new Pixel(50, 50, 50, 255));
    row1red.add(new Pixel(60, 60, 60, 255));
    //green
    row0green = new ArrayList<>();
    row1green = new ArrayList<>();
    row0green.add(new Pixel(100, 100, 100, 255));
    row0green.add(new Pixel(40, 40, 40, 255));
    row1green.add(new Pixel(50, 50, 50, 255));
    row1green.add(new Pixel(60, 60, 60, 255));
    //blue
    row0blue = new ArrayList<>();
    row1blue = new ArrayList<>();
    row0blue.add(new Pixel(130, 130, 130, 255));
    row0blue.add(new Pixel(150, 150, 150, 255));
    row1blue.add(new Pixel(100, 100, 100, 255));
    row1blue.add(new Pixel(130, 130, 130, 255));
    //red
    example5000list4.add(row0red);
    example5000list4.add(row1red);
    //green greyscale
    example5000list5.add(row0green);
    example5000list5.add(row1green);
    //blue greyscale
    example5000list6.add(row0blue);
    example5000list6.add(row1blue);
    //intensity
    row0intensity = new ArrayList<>();
    row1intensity = new ArrayList<>();
    row0intensity.add(new Pixel(110, 110, 110, 255));
    row0intensity.add(new Pixel(76, 76, 76, 255));
    row1intensity.add(new Pixel(66, 66, 66, 255));
    row1intensity.add(new Pixel(83, 83, 83, 255));
    example5000list8.add(row0intensity);
    example5000list8.add(row1intensity);
    //luma
    row0luma = new ArrayList<>();
    row1luma = new ArrayList<>();
    row0luma.add(new Pixel(102, 102, 102, 255));
    row0luma.add(new Pixel(47, 47, 47, 255));
    row1luma.add(new Pixel(53, 53, 53, 255));
    row1luma.add(new Pixel(65, 65, 65, 255));
    example5000list9.add(row0luma);
    example5000list9.add(row1luma);
    //value
    row0value = new ArrayList<>();
    row1value = new ArrayList<>();
    row0value.add(new Pixel(130, 130, 130, 255));
    row0value.add(new Pixel(150, 150, 150, 255));
    row1value.add(new Pixel(100, 100, 100, 255));
    row1value.add(new Pixel(130, 130, 130, 255));
    example5000list10.add(row0value);
    example5000list10.add(row1value);
    //brighten
    row0bright = new ArrayList<>();
    row1bright = new ArrayList<>();
    row0bright.add(new Pixel(150, 150, 180, 255));
    row0bright.add(new Pixel(90, 90, 200, 255));
    row1bright.add(new Pixel(100, 100, 150, 255));
    row1bright.add(new Pixel(110, 110, 180, 255));
    example5000list11.add(row0bright);
    example5000list11.add(row1bright);
    //darken
    row0dark = new ArrayList<>();
    row1dark = new ArrayList<>();
    row0dark.add(new Pixel(50, 50, 80, 255));
    row0dark.add(new Pixel(0, 0, 100, 255));
    row1dark.add(new Pixel(0, 0, 50, 255));
    row1dark.add(new Pixel(10, 10, 80, 255));
    example5000list12.add(row0dark);
    example5000list12.add(row1dark);

    example5000model = new ImageModelImpl();
    example5000model2 = new ImageModelImpl();
    example5000model3 = new ImageModelImpl();
    michaelJordan = new ImageModelImpl();
    michaelJordan2 = new ImageModelImpl();
  }

  @Test
  public void testLoad() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    assertEquals(h1, example5000model.getImages());
    assertEquals(example5000list, example5000model.getImages().get("example"));
    assertEquals(example5000list, example5000model.getImages().get("example"));
  }

  @Test
  public void testSave() {
    init();
    // test save after calling load
    example5000model.load("example", "res/example5000.ppm");
    example5000model.save("example", "res/example2.ppm");
    example5000model.clearImages();
    example5000model.load("testSaveExample", "res/example2.ppm");
    assertEquals(example5000list, example5000model.getImages().get("testSaveExample"));

    // test save after calling 1 command
    example5000model.verticalFlip("testSaveExample", "verticalFlip");
    example5000model.save("verticalFlip", "res/verticalFlipExample5000.ppm");
    example5000model.clearImages();
    example5000model.load("testSaveExampleVertical", "res/verticalFlipExample5000.ppm");
    assertEquals(example5000list3, example5000model.getImages().get("testSaveExampleVertical"));
  }

  @Test
  public void testSaveException() {
    init();

    // test for saving without loading anything before
    try {
      example5000model.save("chicken", "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals("Cannot find file/image", e.getMessage());
    }

    // test for path being null
    try {
      example5000model.save("chicken", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for file being null
    try {
      example5000model.save(null, "res/chickenWing.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testLoadException() {
    init();

    // test for loading the file without a name.
    try {
      example5000model.load(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.load("chicken", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.load(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testRedGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.redGreyScale("example", "redComponent");
    assertEquals(example5000list4, example5000model.getImages().get("redComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.redGreyScale("MJ", "MJRed");
    michaelJordan2.load("OriginalMJ", "res/michael-red-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJRed"));
  }

  @Test
  public void testRedException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.redGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.redGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.redGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testGreenGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.greenGreyScale("example", "greenComponent");
    assertEquals(example5000list5, example5000model.getImages().get("greenComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.greenGreyScale("MJ", "MJGreen");
    michaelJordan2.load("OriginalMJ", "res/michael-green-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJGreen"));
  }

  @Test
  public void testGreenException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.greenGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.greenGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.greenGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testBlueGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.blueGreyScale("example", "blueComponent");
    assertEquals(example5000list6, example5000model.getImages().get("blueComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.blueGreyScale("MJ", "MJBlue");
    michaelJordan2.load("OriginalMJ", "res/michael-blue-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJBlue"));
  }

  @Test
  public void testBlueException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.blueGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.blueGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.blueGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testValueGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.valueGreyScale("example", "valueComponent");
    assertEquals(example5000list10, example5000model.getImages().get("valueComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.valueGreyScale("MJ", "MJValue");
    michaelJordan2.load("OriginalMJ", "res/michael-value-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJValue"));
  }

  @Test
  public void testValueException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.valueGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.valueGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.valueGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testIntensityGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.intensityGreyScale("example", "intensityComponent");
    assertEquals(example5000list8, example5000model.getImages().get("intensityComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.intensityGreyScale("MJ", "MJIntensity");
    michaelJordan2.load("OriginalMJ", "res/michael-intensity-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJIntensity"));
  }

  @Test
  public void testIntensityException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.intensityGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.intensityGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.intensityGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testLumaGreyScale() {
    setup();

    example5000model.load("example", "res/example5000.ppm");
    example5000model.lumaGreyScale("example", "lumaComponent");
    assertEquals(example5000list9, example5000model.getImages().get("lumaComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.lumaGreyScale("MJ", "MJLuma");
    michaelJordan2.load("OriginalMJ", "res/michael-luma-greyscale.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJLuma"));
  }

  @Test
  public void testLumaException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.lumaGreyScale(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.lumaGreyScale("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.lumaGreyScale(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testHorizontal() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    example5000model.horizontalFlip("example", "horizontal");
    assertEquals(example5000list2, example5000model.getImages().get("horizontal"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.horizontalFlip("MJ", "MJHori");
    michaelJordan2.load("OriginalMJ", "res/michael-horizontal.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJHori"));
  }

  @Test
  public void testHorizontalException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.horizontalFlip(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.horizontalFlip("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.horizontalFlip(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testVertical() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    example5000model.verticalFlip("example", "vertical");
    assertEquals(example5000list3, example5000model.getImages().get("vertical"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.verticalFlip("MJ", "MJVert");
    michaelJordan2.load("OriginalMJ", "res/michael-vertical.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJVert"));
  }

  @Test
  public void testVeritcalException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.verticalFlip(null, "res/example2000.ppm");
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.verticalFlip("example", null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.verticalFlip(null, null);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testHorizontalVertical() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    example5000model.verticalFlip("example", "vertical");
    example5000model.horizontalFlip("vertical", "verticalhorizontal");
    assertEquals(example5000list7, example5000model.getImages().get("verticalhorizontal"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.verticalFlip("MJ", "MJVert");
    michaelJordan.horizontalFlip("MJVert", "MJHoriVert");
    michaelJordan2.load("OriginalMJ", "res/michael-horizontal-vertical.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJHoriVert"));

  }

  @Test
  public void testBrighten() {
    setup();
    example5000model.load("example", "res/example5000.ppm");
    example5000model.brighten("example", "brightComponent", 50);
    assertEquals(example5000list11, example5000model.getImages().get("brightComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.brighten("MJ", "MJBright50", 50);
    michaelJordan2.load("OriginalMJ", "res/michael-bright50.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJBright50"));
  }

  @Test
  public void testBrightenException() {
    init();
    example5000model.load("example", "res/example5000.ppm");
    // test for loading the file without a name.
    try {
      example5000model.brighten(null, "res/example2000.ppm", 50);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for path being null
    try {
      example5000model.brighten("example", null, 50);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }

    // test for both arguments being null
    try {
      example5000model.brighten(null, null, -50);
      fail("Did not catch exception.");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid file path/destination or filename!");
    }
  }

  @Test
  public void testDarken() {
    setup();
    example5000model.load("example", "res/example5000.ppm");
    example5000model.brighten("example", "darkComponent", -50);
    assertEquals(example5000list12, example5000model.getImages().get("darkComponent"));

    michaelJordan.load("MJ", "res/michael-jordan.ppm");
    michaelJordan.brighten("MJ", "MJDark50", -50);
    michaelJordan2.load("OriginalMJ", "res/michael-dark50.ppm");
    assertEquals(michaelJordan2.getImages().get("OriginalMJ"),
            michaelJordan.getImages().get("MJDark50"));
  }
}