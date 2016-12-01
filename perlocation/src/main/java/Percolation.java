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

    top = n + 1;
    bottom = n + 2;
    weightedQuickUnionUF = new WeightedQuickUnionUF(size + 2);
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
    if (col > size && isOpen(row + 1, col)) {
      int indexD = returnIndex(row + 1, col);
      weightedQuickUnionUF.union(index, indexD);
    }
  }

  public boolean isOpen(int row, int col) {
    return array[returnIndex(row, col)];
  }

  public boolean isFull(int row, int col) {  // is site (row, col) full?
    int index = returnIndex(row, col);

    return false;
  }

  public boolean percolates()              // does the system percolate?
  {
    return false;
  }

  private int returnIndex(int row, int col) {
    return ((row - 1) * size - 1) + col;
  }

}
