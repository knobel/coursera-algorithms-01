import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by michal on 06.01.17.
 */
public class BoardTest {
    Board board01;
    Board board02;
    int[][] testBlocks01 = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    int[][] testBlocks02 = new int[][]{{5, 4, 7}, {6, 0, 8}, {2, 3, 1}};
    File resourcesDirectory = new File("src/test/resources/data");


    @Before
    public void setUp() throws Exception {
        board01 = new Board(testBlocks01);
        board02 = new Board(testBlocks02);

    }

    @Test
    public void getValueFromArrayIndex() throws Exception {
        Method method = board01.getClass().getDeclaredMethod("getValueFromArrayIndex", int.class, int.class);
        method.setAccessible(true);
        Assert.assertEquals(1, method.invoke(board01, 0, 0));
        Assert.assertEquals(2, method.invoke(board01, 1, 0));
        Assert.assertEquals(3, method.invoke(board01, 2, 0));
        Assert.assertEquals(4, method.invoke(board01, 0, 1));
        Assert.assertEquals(5, method.invoke(board01, 1, 1));
        Assert.assertEquals(6, method.invoke(board01, 2, 1));
        Assert.assertEquals(7, method.invoke(board01, 0, 2));
        Assert.assertEquals(8, method.invoke(board01, 1, 2));
        Assert.assertEquals(0, method.invoke(board01, 2, 2));
    }

    @Test
    public void getDistanceToDefaultPositionTest() throws Exception {
        Method method = board02.getClass().getDeclaredMethod("getDistanceToDefaultPosition", int.class, int.class);
        method.setAccessible(true);
        System.out.println(board02.toString());
        Assert.assertEquals(2, method.invoke(board02, 0, 0));
        Assert.assertEquals(2, method.invoke(board02, 1, 0));
        Assert.assertEquals(4, method.invoke(board02, 2, 0));
        Assert.assertEquals(2, method.invoke(board02, 0, 1));
        Assert.assertEquals(0, method.invoke(board02, 1, 1));
        Assert.assertEquals(2, method.invoke(board02, 2, 1));
        Assert.assertEquals(3, method.invoke(board02, 0, 2));
        Assert.assertEquals(3, method.invoke(board02, 1, 2));
        Assert.assertEquals(4, method.invoke(board02, 2, 2));
    }

    @Test
    public void toStringTest() throws Exception {
        String expected = "3\n" +
                " 0  1  3  \n" +
                " 4  2  5  \n" +
                " 7  8  6  ";
        System.out.println(board01.toString());
        Assert.assertEquals(expected, board01.toString());
    }

    @Test
    public void equalsTest() throws Exception {
        Object nullObj = null;
        Assert.assertFalse(board01.equals(nullObj));

        Object object = new Object();
        Assert.assertFalse(board01.equals(object));

        int[][] testBlocksWrongSize = new int[][]{{5, 4, 7, 9}, {6, 0, 8, 10}, {2, 3, 1, 11}};
        Board boardWrongSize = new Board(testBlocksWrongSize);
        Assert.assertFalse(board01.equals(boardWrongSize));

        int[][] testBlocksWrongValues = new int[][]{{5, 3, 7}, {6, 0, 8}, {2, 3, 1}};
        Board boardWrongValues = new Board(testBlocksWrongValues);
        Assert.assertFalse(board01.equals(boardWrongValues));

        int[][] testBlocksCorrectValues = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board boardCorrectValues = new Board(testBlocksCorrectValues);
        Assert.assertTrue(board01.equals(boardCorrectValues));
    }

    @Test
    public void hammingTest() throws Exception {
        int[][] testBlocks = new int[][]{{1, 3}, {2, 0}};
        Board board = new Board(testBlocks);
        Assert.assertEquals(2, board.hamming());

        int[][] testBlocksCase2 = new int[][]{{5, 1, 3}, {8, 4, 0}, {7, 6, 2}};
        Board boardCase2 = new Board(testBlocksCase2);
        Assert.assertEquals(6, boardCase2.hamming());

        int[][] testBlockCase3 = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board boardCase3 = new Board(testBlockCase3);
        Assert.assertEquals(4, boardCase3.hamming());
    }

    @Test
    public void hammingTest02() throws Exception {
        Board board = new Board(getTilesFromFile("puzzle04.txt"));
        System.out.println(board.toString());

        Assert.assertEquals(4, board.hamming());
    }

    @Test
    public void manhattanTest() throws Exception {
        int[][] testBlocks = new int[][]{{2, 1}, {3, 0}};
        Board board = new Board(testBlocks);
        Assert.assertEquals(2, board.manhattan());

        int[][] testBlockCase2 = new int[][]{{1, 0}, {2, 3}};
        Board boardCase02 = new Board(testBlockCase2);
        Assert.assertEquals(3, boardCase02.manhattan());


        int[][] testBlockCase3 = new int[][]{{0, 1}, {3, 2}};
        Board boardCase03 = new Board(testBlockCase3);
        Assert.assertEquals(2, boardCase03.manhattan());

    }

    @Test
    public void getDefaultRowIndexFromValueTest() throws Exception {
        Method method = board02.getClass().getDeclaredMethod("getDefaultRowIndexFromValue", int.class, int.class);
        method.setAccessible(true);
        Assert.assertEquals(0, method.invoke(board02, 1, 3));
        Assert.assertEquals(0, method.invoke(board02, 2, 3));
        Assert.assertEquals(0, method.invoke(board02, 3, 3));
        Assert.assertEquals(1, method.invoke(board02, 4, 3));
        Assert.assertEquals(1, method.invoke(board02, 5, 3));
        Assert.assertEquals(1, method.invoke(board02, 6, 3));
        Assert.assertEquals(2, method.invoke(board02, 7, 3));
        Assert.assertEquals(2, method.invoke(board02, 8, 3));

        Assert.assertEquals(0, method.invoke(board02, 2, 2));
    }

    @Test
    public void getDefaultColumnIndexFromValueTest() throws Exception {
        Method method = board02.getClass().getDeclaredMethod("getDefaultColumnIndexFromValue", int.class, int.class);
        method.setAccessible(true);
        Assert.assertEquals(0, method.invoke(board02, 1, 3));
        Assert.assertEquals(1, method.invoke(board02, 2, 3));
        Assert.assertEquals(2, method.invoke(board02, 3, 3));
        Assert.assertEquals(0, method.invoke(board02, 4, 3));
        Assert.assertEquals(1, method.invoke(board02, 5, 3));
        Assert.assertEquals(2, method.invoke(board02, 6, 3));
        Assert.assertEquals(0, method.invoke(board02, 7, 3));
        Assert.assertEquals(1, method.invoke(board02, 8, 3));


        Assert.assertEquals(0, method.invoke(board02, 1, 2));
        Assert.assertEquals(1, method.invoke(board02, 2, 2));
        Assert.assertEquals(0, method.invoke(board02, 3, 2));
    }

    @Test
    public void getNeighboursCountTest() throws Exception {
        int[][] testBlocks = new int[][]{{2, 1}, {3, 0}};
        Board board = new Board(testBlocks);
        int size = 0;
        for (Board b : board.neighbors()) {
            size++;
        }
        Assert.assertEquals(2, size);

        int[][] testBlocksCase02 = new int[][]
                        {{1, 6, 3},
                        {4, 2, 5},
                        {7, 8, 0}};

        size = 0;
        board = new Board(testBlocksCase02);
        for (Board b : board.neighbors()) {
            size++;
        }
        Assert.assertEquals(2, size);
    }

    @Ignore
    @Test
    public void getNeighboursTest1() throws Exception {
        int[][] testBlocks = new int[][]{{2, 1},
                                        {3, 0}};
        Iterable<Board> neighbors = new Board(testBlocks).neighbors();
        for (Board b: neighbors) {
            System.out.println(b.toString());
        }
    }

    @Ignore
    @Test
    public void getNeighboursTest2() throws Exception {
        int[][] testBlocks = new int[][]{{0, 2},
                {1, 3}};
        Iterable<Board> neighbors = new Board(testBlocks).neighbors();
        for (Board b: neighbors) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void twinTest() throws Exception {
        int[][] testBlocks = new int[][]{{0, 2},
                {1, 3}};
        Board twin = new Board(testBlocks).twin();
        System.out.println(twin);
    }
    
    @Test
    public void twinTest02() throws Exception {
        int[][] tiles = getTilesFromFile("puzzle04.txt");

        Board testBoard = new Board(tiles);
        System.out.println(testBoard);
        System.out.println(testBoard.twin());
    }

    private int[][] getTilesFromFile(String fileName) throws IOException {
        String filePath = resourcesDirectory.getCanonicalPath() + File.separator + fileName;
        In in = new In(filePath);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        return tiles;
    }

    @Test
    public void isGoal() throws Exception {
        Board board = new Board(getTilesFromFile("puzzle00.txt"));

        Assert.assertTrue(board.isGoal());
    }
}