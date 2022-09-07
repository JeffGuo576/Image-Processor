package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

/**
 * Paints the histogram rectangle bars for all the components.
 */
public class DrawHistogram extends JPanel {

  private List<HashMap<Integer, Integer>> loHistogram;
  private Color rColor;
  private Color gColor;
  private Color bColor;
  private Color iColor;

  private int maxPixels;

  /**
   * Draws a histogram containing 4 histograms.
   * @param loHistogram is the list of histograms that will be in this histogram.
   * @param maxPixels is the highest amount of pixels out of all the histograms.
   */
  public DrawHistogram(List<HashMap<Integer, Integer>> loHistogram, int maxPixels) {
    this.rColor = new Color(255, 0, 0, 60);
    this.gColor = new Color(0, 255, 0, 60);
    this.bColor = new Color(0, 0, 255,60);
    this.iColor = new Color(200, 60, 200, 60);
    this.loHistogram = loHistogram;
    this.maxPixels = maxPixels / 50;
  }

  /**
   * Paints the histogram.
   * @param g the <code>Graphics</code> object to protect.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D image = (Graphics2D) g;
    int x = 50;
    int y = maxPixels + 50;
    this.setBackground(Color.white);
    int histogramType = 0;
    for (HashMap<Integer, Integer> histogram: this.loHistogram) {
      for (Integer value : histogram.keySet()) {
        image.drawLine(x, y, x, y - (histogram.get(value) / 50));
        image.setStroke(new BasicStroke(2));
        switch (histogramType) {
          case 0:
            g.setColor(rColor);
            break;
          case 1:
            g.setColor(gColor);
            break;
          case 2:
            g.setColor(bColor);
            break;
          case 3:
            g.setColor(iColor);
            break;
          default:
            break;
        }
        x += 2;
      }
      histogramType += 1;
      x = 50;
    }
    g.setColor(Color.black);
    g.drawLine(x - 1, y + 1,   256 * 2 + x, y + 1);
    g.drawString("Frequency", (256 * 2 + 49) / 2, y + 20);
    g.setColor(Color.black);
    g.drawLine(x - 1, y - maxPixels + 1, x - 1, y + 1);
    g.drawString("Value", 10, (y + 1) / 2);
  }
}