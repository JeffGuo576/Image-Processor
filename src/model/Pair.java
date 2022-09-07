package model;

/**
 * Represents a pair of numbers; row and column which represents the position of a pixel.
 */
public class Pair implements IPair {

  int row;

  int col;

  public Pair(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

}
