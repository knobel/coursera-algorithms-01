import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 06.01.17.
 */
public class Board {
    private final int[][] board;
    private List<Board> neighbours = new LinkedList<Board>();

    public Board(int[][] blocks) {
        board = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return board.length;
    }

    public int hamming()                   // number of blocks out of place
    {
        int result = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != getValueFromArrayIndex(i, j) && board[i][j] != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int result = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                result += getDistanceToDefaultPosition(i, j);
            }
        }
        return result;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != getValueFromArrayIndex(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int[][] tempBlocks = copyBlocks();
        for (int i = 0; i < tempBlocks.length; i++) {
            for (int j = 0; j < tempBlocks.length - 1; j++) {
                if (tempBlocks[i][j] != 0 && tempBlocks[i][j + 1] != 0) {
                    int temp = tempBlocks[i][j];
                    tempBlocks[i][j] = tempBlocks[i][j + 1];
                    tempBlocks[i][j + 1] = temp;
                    return new Board(tempBlocks);
                }
            }
        }
        return null;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (!(y instanceof Board) || y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board yBoard = (Board) y;
        if (yBoard.dimension() != this.dimension()) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (yBoard.board[i][j] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        calculateNeighbours();
        return neighbours;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("" + board.length);
        for (int i = 0; i < board.length; i++) {
            sb.append("\n ");
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
                sb.append("  ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

    private int getValueFromArrayIndex(int i, int j) {
        if (board.length - 1 == i && board.length - 1 == j) {
            return 0;
        }
        return board.length * i + j + 1;
    }

    private int getDistanceToDefaultPosition(final int i, final int j) {
        if (board[i][j] == 0) {
            return 0;
        }
        int iFromValue, jFromValue;
        iFromValue = getDefaultColumnIndexFromValue(board[i][j], board.length);
        jFromValue = getDefaultRowIndexFromValue(board[i][j], board.length);
        return Math.abs(i - iFromValue) + Math.abs(j - jFromValue);
    }

    private int getDefaultColumnIndexFromValue(int value, int size) {
        int defaultColumnIndex;
        defaultColumnIndex = value % size - 1;
        if (defaultColumnIndex == -1) {
            defaultColumnIndex = size-1;
        }
        return defaultColumnIndex;
    }

    private int getDefaultRowIndexFromValue(int value, int size) {
        int defaultRowIndex;
        defaultRowIndex = (value - 1)/ size ;
        return defaultRowIndex;
    }

    private void calculateNeighbours() {
        int i0 = -10, j0 = -10;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                }
            }
        }

        final int[][] SHIFTS_TO_NEIGHBOURS = {{0,1}, {0, -1}, {1, 0}, {0, 1}};

        for (int i = 0; i < SHIFTS_TO_NEIGHBOURS.length; i++) {
            int iNeighbour = i0 - SHIFTS_TO_NEIGHBOURS[i][0];
            int jNeighbour = j0 - SHIFTS_TO_NEIGHBOURS[i][1];
            if (iNeighbour >= 0 && jNeighbour >= 0) {
                int[][] boardTemp = copyBlocks();
                boardTemp[i0][j0] = boardTemp[iNeighbour][jNeighbour];
                boardTemp[iNeighbour][jNeighbour] = 0;
                Board neighbourBoard = new Board(boardTemp);
                neighbours.add(neighbourBoard);
            }
        }
    }

    private int[][] copyBlocks() {
        int[][] boardTemp = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardTemp[i][j] = board[i][j];
            }
        }
        return boardTemp;
    }
}