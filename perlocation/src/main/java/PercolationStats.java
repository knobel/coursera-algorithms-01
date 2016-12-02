/**
 * Created by Michal Knobel on 01.12.2016.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  int numberOfLoops[];
  double openedTosizeRatio[];


  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    numberOfLoops = new int[trials];
    openedTosizeRatio = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      int openedCells = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
          openedCells++;
        }
      }
      numberOfLoops[i] = openedCells;
      openedTosizeRatio[i] = (double) openedCells / (n * n);
    }
  }

  public static void main(String[] args)    // test client (described below)
  {
    int N = 120;
    int T = 500;
    PercolationStats ps = new PercolationStats(N, T);

    String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
    StdOut.println("mean                    = " + ps.mean());
    StdOut.println("stddev                  = " + ps.stddev());
    StdOut.println("95% confidence interval = " + confidence);
  }

  public double mean()                          // sample mean of percolation threshold
  {
    return StdStats.mean(openedTosizeRatio);
  }

  public double stddev()                        // sample standard deviation of percolation threshold
  {
    return StdStats.stddev(openedTosizeRatio);
  }

  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  {
    return mean() - (1.96 * stddev()) / Math.sqrt(openedTosizeRatio.length);
  }

  public double confidenceHi()                  // high endpoint of 95% confidence interval
  {
    return mean() - (1.96 * stddev()) / Math.sqrt(openedTosizeRatio.length);
  }
}
