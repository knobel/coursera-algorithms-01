/**
 * Created by Michal Knobel on 01.12.2016.
 */
public class Percolation {
  public Percolation(int n)                // create n-by-n grid, with all sites blocked
  {
    if (n <= 0) throw new IllegalArgumentException();

  }
  public void open(int row, int col)       // open site (row, col) if it is not open already
  {

  }
  public boolean isOpen(int row, int col)  // is site (row, col) open?
  {
    return false;
  }
  public boolean isFull(int row, int col)  // is site (row, col) full?
  {
    return false;
  }
  public boolean percolates()              // does the system percolate?
  {
    return false;
  }
  public static void main(String[] args)   // test client (optional)
  {

  }

}
