package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

import controller.GUIController;
import controller.GUIControllerImpl;
import model.ImageModel;
import model.Pixel;

/**
 * Represents a GUI view that displays image being edited and buttons for the commands.
 */
public class GUIView extends JFrame implements GUI, ActionListener {

  private List<HashMap<Integer, Integer>> loHistograms;
  private final GUIController controller;
  private JFrame msgWindow;
  private JFrame displayMessage;
  private JTextField brightenTextField;
  private JTextField fileNameTextField;
  private JTextField destPathTextField;
  private JPanel histogramPanel;
  private JLabel imageLabel;
  private String imagePath;
  private JLabel histogramLabel;
  private JPanel displayPanel;
  private JPanel loadSavePanel;
  private JPanel mainPanel;
  private JScrollPane scroll2;

  /**
   * Creates a GUI view that allows the client to view the GUI of the image processor.
   * @param m is a image model.
   */
  public GUIView(ImageModel m) {
    super();

    if (m == null) {
      throw new IllegalArgumentException("Cannot have model as null");
    }

    final ImageModel model = m;
    this.controller = new GUIControllerImpl(model, this);

    // set the frame
    setTitle("Image Processing Application");
    setSize(1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setPanels();
    this.setButtons();
    this.revalidate();
    this.repaint();
    setVisible(true);
  }

  /**
   * Sets the panels for the GUI.
   */
  private void setPanels() {
    // set main panel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Display panel (image and histogram)
    displayPanel = new JPanel();
    displayPanel.setLayout(new GridLayout());
    displayPanel.setBorder(BorderFactory.createTitledBorder("Display"));

    // image panel
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setPreferredSize(new Dimension(800, 500));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imageLabel = new JLabel();
    JScrollPane scroll1 = new JScrollPane(imageLabel);
    scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    imagePanel.add(scroll1);

    // histogram panel
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setPreferredSize(new Dimension(500, 500));
    scroll2 = new JScrollPane(histogramLabel);
    scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    histogramPanel.add(scroll2);

    displayPanel.add(imagePanel);
    displayPanel.add(histogramPanel);
    displayPanel.setPreferredSize(new Dimension(720, 500));
    mainPanel.add(displayPanel);

    // load and save file panel:
    loadSavePanel = new JPanel();
    loadSavePanel.setPreferredSize(new Dimension(720, 20));
    loadSavePanel.setBorder(BorderFactory.createTitledBorder("Load And Save"));
  }

  /**
   * Sets all the buttons and adds action listeners to the buttons.
   */
  private void setButtons() {
    JButton loadButton = new JButton("Load File");
    loadButton.setHorizontalAlignment(JLabel.CENTER);
    loadButton.setVerticalAlignment(JLabel.CENTER);
    loadButton.addActionListener(this);
    loadButton.setActionCommand("load");

    JButton saveButton = new JButton("Save File");
    loadButton.setHorizontalAlignment(JButton.CENTER);
    loadButton.setVerticalAlignment(JButton.CENTER);
    saveButton.addActionListener(this);
    saveButton.setActionCommand("save");

    JPanel commandPanel = new JPanel();
    commandPanel.setLayout(new FlowLayout());
    commandPanel.setPreferredSize(new Dimension(720, 40));
    commandPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
    JButton redButton = new JButton("Red Grey Scale");
    redButton.addActionListener(this);
    redButton.setActionCommand("red");

    JButton greenButton = new JButton("Green Grey Scale");
    greenButton.addActionListener(this);
    greenButton.setActionCommand("green");

    JButton blueButton = new JButton("Blue Grey Scale");
    blueButton.addActionListener(this);
    blueButton.setActionCommand("blue");

    JButton valueButton = new JButton("Value Grey Scale");
    valueButton.addActionListener(this);
    valueButton.setActionCommand("value");

    JButton intensityButton = new JButton("Intensity");
    intensityButton.addActionListener(this);
    intensityButton.setActionCommand("intensity");

    JButton lumaButton = new JButton("Luma");
    lumaButton.addActionListener(this);
    lumaButton.setActionCommand("luma");

    JButton horizontalButton = new JButton("Horizontal-Flip");
    horizontalButton.addActionListener(this);
    horizontalButton.setActionCommand("horizontal");

    JButton verticalButton = new JButton("Vertical-Flip");
    verticalButton.addActionListener(this);
    verticalButton.setActionCommand("vertical");

    JButton brightenButton = new JButton("Brighten");
    brightenButton.addActionListener(this);
    brightenButton.setActionCommand("brighten");

    JButton blurButton = new JButton("Blur");
    blurButton.addActionListener(this);
    blurButton.setActionCommand("blur");

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.addActionListener(this);
    sharpenButton.setActionCommand("sharpen");

    JButton greyscaleButton = new JButton("Grey Scale");
    greyscaleButton.addActionListener(this);
    greyscaleButton.setActionCommand("grey scale");

    JButton sepiaButton = new JButton("Sepia Tone");
    sepiaButton.addActionListener(this);
    sepiaButton.setActionCommand("sepia");

    loadSavePanel.add(loadButton);
    loadSavePanel.add(saveButton);
    commandPanel.add(redButton);
    commandPanel.add(greenButton);
    commandPanel.add(blueButton);
    commandPanel.add(valueButton);
    commandPanel.add(intensityButton);
    commandPanel.add(lumaButton);
    commandPanel.add(horizontalButton);
    commandPanel.add(verticalButton);
    commandPanel.add(brightenButton);
    commandPanel.add(blurButton);
    commandPanel.add(sharpenButton);
    commandPanel.add(greyscaleButton);
    commandPanel.add(sepiaButton);
    mainPanel.add(loadSavePanel);
    mainPanel.add(commandPanel);
  }

  @Override
  public void renderWindowMessage(String message) {
    msgWindow = new JFrame();
    msgWindow.setLayout(new FlowLayout());
    msgWindow.setVisible(true);
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    switch (message) {
      case "load":
        label.setText("Give this image a file name: ");
        msgWindow.add(label);
        this.fileNameTextField();
        JButton loadEnterButton = new JButton("Enter");
        loadEnterButton.addActionListener(this);
        loadEnterButton.setActionCommand("load enter");
        msgWindow.add(loadEnterButton);
        break;
      case "save":
        label2.setText("Save as: ");
        msgWindow.add(label2);
        this.destNameTextField();
        JButton saveEnterButton = new JButton("Enter");
        saveEnterButton.addActionListener(this);
        saveEnterButton.setActionCommand("save enter");
        msgWindow.add(saveEnterButton);
        break;
      case "brighten":
        label.setText("File name to save: ");
        msgWindow.add(label);
        this.fileNameTextField();
        label2.setText("Destination path: ");
        msgWindow.add(label2);
        this.destNameTextField();
        label3.setText("Darken or Brighten by: ");
        msgWindow.add(label3);
        brightenTextField = new JTextField();
        brightenTextField.setPreferredSize(new Dimension(150, 40));
        msgWindow.add(brightenTextField);
        this.setEnterButton();
        break;
      default:
        label.setText("File name: ");
        msgWindow.add(label);
        this.fileNameTextField();
        label2.setText("New file name for edited image: ");
        msgWindow.add(label2);
        this.destNameTextField();
        this.setEnterButton();
        break;
    }
    msgWindow.pack();
  }

  /**
   * Sets the enter button for msg window frame.
   */
  private void setEnterButton() {
    JButton enterNameButton = new JButton("Enter");
    enterNameButton.addActionListener(this);
    enterNameButton.setActionCommand("enter");
    msgWindow.add(enterNameButton);
  }

  /**
   * Gets the file name of the image that is currently being displayed on the GUI.
   */
  private void fileNameTextField() {
    fileNameTextField = new JTextField();
    fileNameTextField.setPreferredSize(new Dimension(250, 40));
    msgWindow.add(fileNameTextField);
  }

  /**
   * Gets the destination name of the image that is currently being displayed on the GUI.
   */
  private void destNameTextField() {
    destPathTextField = new JTextField();
    destPathTextField.setPreferredSize(new Dimension(250, 40));
    msgWindow.add(destPathTextField);
  }

  @Override
  public void renderMessage(String message) {
    if (!message.equals("enter")) {
      displayMessage = new JFrame();
      displayMessage.setSize(300, 100);
      displayMessage.setLayout(new FlowLayout());
      JLabel label = new JLabel();
      label.setText(message);
      displayMessage.add(label);
      displayMessage.setVisible(true);
    }
    msgWindow.setVisible(false);
    msgWindow.pack();
  }

  @Override
  public String getInput(String type) {
    String input = null;
    switch (type) {
      case "file name":
        input = fileNameTextField.getText();
        break;
      case "load path":
        input = imagePath;
        break;
      case "dest name":
        input = destPathTextField.getText();
        break;
      case "num":
        input = brightenTextField.getText();
        break;
      default:
        break;
    }
    return input;
  }

  /**
   * Renders the histogram.
   * @param image is the image being displayed.
   */
  @Override
  public void renderImage(ImageIcon image) {
    imagePath = image.toString();
    imageLabel.setIcon(image);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.revalidate();
    imageLabel.repaint();
  }

  /**
   * Displays the histogram onto the GUI.
   * @param filename is the filename of the image.
   * @param imagePixels is the image in a 2D array list of pixels.
   */
  @Override
  public void renderHistograms(String filename, Map<String,
          ArrayList<ArrayList<Pixel>>> imagePixels) {
    if (imagePixels.get(filename) == null) {
      this.renderMessage("Invalid file name");
    }
    this.makeHistograms(filename, imagePixels);
    this.setHistogram();
  }

  /**
   * Initializes the histogram panel, adds to the display Panel, and refreshes the histogram
   * being displayed on the GUI.
   */
  private void setHistogram() {
    int maxPixels = 0;
    for (HashMap<Integer, Integer> histogram: this.loHistograms) {
      maxPixels = Math.max(maxPixels, Collections.max(histogram.values()));
    }
    displayPanel.remove(scroll2);
    displayPanel.remove(histogramPanel);
    DrawHistogram histograms = new DrawHistogram(loHistograms, maxPixels);
    histogramPanel = histograms;
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    scroll2 = new JScrollPane(histogramPanel);
    scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    displayPanel.add(scroll2);
    histogramPanel.setPreferredSize(new Dimension(600, maxPixels / 50 + 80));
    displayPanel.revalidate();
    displayPanel.repaint();
  }

  /**
   * Creates all four histograms, RGB and intensity histogram.
   * @param filename is the filename of the current image.
   * @param imagePixels is the current image in a 2D list of pixels.
   */
  private void makeHistograms(String filename, Map<String,
          ArrayList<ArrayList<Pixel>>> imagePixels) {

    ArrayList<ArrayList<Pixel>> copy = imagePixels.get(filename);
    List<HashMap<Integer, Integer>> loHistogram = new ArrayList<>();
    HashMap<Integer, Integer> redHistogram = new HashMap<>();
    HashMap<Integer, Integer> blueHistogram = new HashMap<>();
    HashMap<Integer, Integer> greenHistogram = new HashMap<>();
    HashMap<Integer, Integer> intensityHistogram = new HashMap<>();

    // creates all four histograms
    for (int x = 0 ; x <= 255; x++) {
      redHistogram.put(x, 0);
      greenHistogram.put(x, 0);
      blueHistogram.put(x, 0);
      intensityHistogram.put(x, 0);
    }

    for (int row = 0; row < copy.size(); row++) {
      for (int col = 0; col < copy.get(row).size(); col++) {
        Pixel pixel =  copy.get(row).get(col);
        int numRPixels = redHistogram.get(pixel.getR()) + 1;
        redHistogram.put(pixel.getR(), numRPixels);
        int numGPixels = greenHistogram.get(pixel.getG()) + 1;
        greenHistogram.put(pixel.getG(), numGPixels);
        int numBPixels = blueHistogram.get(pixel.getB()) + 1;
        blueHistogram.put(pixel.getB(), numBPixels);
        int intensityPixels = intensityHistogram.get(pixel.intensity()) + 1;
        intensityHistogram.put(intensityHistogram.get(pixel.intensity()), intensityPixels);
      }
    }

    loHistogram.add(redHistogram);
    loHistogram.add(greenHistogram);
    loHistogram.add(blueHistogram);
    loHistogram.add(intensityHistogram);
    this.loHistograms = loHistogram;
  }

  /**
   * Calls the controller's action performed method whenever an event happened.
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    controller.actionPerformed(e);
  }

}