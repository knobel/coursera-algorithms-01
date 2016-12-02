/**
 * Created by Michal Knobel on 01.12.2016.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private boolean array[];
  private WeightedQuickUnionUF weightedQuickUnionUF;
  private int size;
  private int top;
  private int bottom;

  public Percolation(int n) {
    if (n <= 0)
      throw new IllegalArgumentException("Percolation array size shouldn't be lower then 1");

    size = n;
    array = new boolean[size * size];
    for (int i = 0; i < size; i++) {
      array[i] = false;
    }

    top = size * size;
    bottom = size * size + 1;
    weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);
  }

  public static void main(String[] args) {

  }

  public void open(int row, int col) {

    int index = returnIndex(row, col);
    array[index] = true;
    //up
    if (row > 1 && isOpen(row - 1, col)) {
      int indexU = returnIndex(row - 1, col);
      weightedQuickUnionUF.union(index, indexU);
    }
    //left
    if (col > 1 && isOpen(row, col - 1)) {
      int indexL = returnIndex(row, col - 1);
      weightedQuickUnionUF.union(index, indexL);
    }
    //right
    if (col < size && isOpen(row, col + 1)) {
      int indexR = returnIndex(row, col + 1);
      weightedQuickUnionUF.union(index, indexR);
    }
    //down
    if (row < size && isOpen(row + 1, col)) {
      int indexD = returnIndex(row + 1, col);
      weightedQuickUnionUF.union(index, indexD);
    }
    //connect with top and the bottom if the point is in the first or last row
    if (row == 1)
      weightedQuickUnionUF.union(index, top);
    if (row == size)
      weightedQuickUnionUF.union(index, bottom);
  }

  public boolean isOpen(int row, int col) {
    return array[returnIndex(row, col)];
  }

  public boolean isFull(int row, int col) {  // is site (row, col) full?
    if (0 > row && row > size && 0 > row && row > size) {
      throw new IndexOutOfBoundsException();
    }
    int index = returnIndex(row, col);
    return weightedQuickUnionUF.connected(top, index);
  }

  public boolean percolates() {
    return weightedQuickUnionUF.connected(top, bottom);
  }

  private int returnIndex(int row, int col) {
    return ((row - 1) * size - 1) + col;
  }

}
