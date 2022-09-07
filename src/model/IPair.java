package model;


/**
 * IPair represents a pair. A pair holds the row and column of a pixel (or the position).
 */
public interface IPair {
  /**
   * Gets the row of pair.
   *
   * @return
   */
  int getRow();

  /**
   * Gets the col of pair.
   *
   * @return
   */
  int getCol();
}
