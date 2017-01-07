import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by michal on 06.01.17.
 */
public class BoardTest {
    Board board01;
    Board board02;
    int[][] testBlocks01 = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    int[][] testBlocks02 = new int[][]{{5, 4, 7}, {6, 0, 8}, {2, 3, 1}};

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
        Assert.assertEquals(2, method.invoke(board01, 0, 1));
        Assert.assertEquals(3, method.invoke(board01, 0, 2));
        Assert.assertEquals(4, method.invoke(board01, 1, 0));
        Assert.assertEquals(5, method.invoke(board01, 1, 1));
        Assert.assertEquals(6, method.invoke(board01, 1, 2));
        Assert.assertEquals(7, method.invoke(board01, 2, 0));
        Assert.assertEquals(8, method.invoke(board01, 2, 1));
        Assert.assertEquals(0, method.invoke(board01, 2, 2));
    }

    @Test
    public void getDistanceToDefaultPosition() throws Exception {
        Method method = board02.getClass().getDeclaredMethod("getDistanceToDefaultPosition", int.class, int.class);
        method.setAccessible(true);
        Assert.assertEquals(2, method.invoke(board02, 0, 0));
        Assert.assertEquals(0, method.invoke(board02, 0, 1));
        Assert.assertEquals(0, method.invoke(board02, 0, 2));
        Assert.assertEquals(2, method.invoke(board02, 1, 0));
        Assert.assertEquals(0, method.invoke(board02, 1, 1));
        Assert.assertEquals(0, method.invoke(board02, 1, 2));
        Assert.assertEquals(1, method.invoke(board02, 2, 0));
        Assert.assertEquals(1, method.invoke(board02, 2, 1));
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
        int[][] testBlocks = new int[][]{{1, 0}, {2, 3}};
        Board board = new Board(testBlocks);
        Assert.assertEquals(2, board.hamming());

        int[][] testBlocksCase2 = new int[][]{{5, 8, 7}, {1, 4, 6}, {3, 0, 2}};
        Board boardCase2 = new Board(testBlocksCase2);
        Assert.assertEquals(7, boardCase2.hamming());
    }

    @Test
    public void manhattanTest() throws Exception {
        int[][] testBlocks = new int[][]{{2, 3}, {1, 0}};
        Board board = new Board(testBlocks);
        Assert.assertEquals(2, board.manhattan());
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

}