import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 06.01.17.
 */
public class Board {
    private final int[] board;
    private int manhattan = -1;
    private int hamming = -1;
    private int size;

    public Board(int[][] blocks) {
        size = blocks.length;
        board = new int[size*size];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i+j*size] = blocks[j][i];  // wczytywane wierszami!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }
    }

    private Board(int[][] blocks, boolean reverted) { // odwrocony format danych
        board = new int[blocks.length*blocks.length];
        size = blocks.length;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i+j*size] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        if (this.hamming == -1) {
            int result = 0;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i+j*size] != getValueFromArrayIndex(i, j) && board[i+j*size] != 0) {
                        result++;
                    }
                }
            }
            this.hamming = result;
            return result;
        } else {
            return this.hamming;
        }
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        if (this.manhattan == -1) {
            int result = 0;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result += getDistanceToDefaultPosition(i, j);
                }
            }
            this.manhattan = result;
            return result;
        } else {
            return this.manhattan;
        }
    }

    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i+j*size] != getValueFromArrayIndex(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        int[][] tempBlocks = copyBlocks();
        for (int i = 0; i < tempBlocks.length; i++) {
            for (int j = 0; j < tempBlocks.length - 1; j++) {
                if (tempBlocks[i][j] != 0 && tempBlocks[i][j + 1] != 0) {
                    int temp = tempBlocks[i][j];
                    tempBlocks[i][j] = tempBlocks[i][j + 1];
                    tempBlocks[i][j + 1] = temp;
                    return new Board(tempBlocks, true);
                }
            }
        }
        return null;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == null || y.getClass() != Board.class) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board yBoard = (Board) y;
        if (yBoard.dimension() != this.dimension()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (yBoard.board[i+j*size] != board[i+j*size]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        return calculateNeighbours();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("" + size);
        for (int i = 0; i < size; i++) {
            sb.append("\n ");
            for (int j = 0; j < size; j++) {
                sb.append(board[j+i*size]); // odwrocone!!!
                sb.append("  ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

    private int getValueFromArrayIndex(int i, int j) {
        if (size - 1 == i && size - 1 == j) {
            return 0;
        }
        return size * j + i + 1;
    }

    private int getDistanceToDefaultPosition(final int i, final int j) {
        if (board[i+j*size] == 0) {
            return 0;
        }
        int iFromValue, jFromValue;
        iFromValue = getDefaultColumnIndexFromValue(board[i+j*size], size);
        jFromValue = getDefaultRowIndexFromValue(board[i+j*size], size);
        return Math.abs(i - iFromValue) + Math.abs(j - jFromValue);
    }

    private int getDefaultColumnIndexFromValue(int value, int boardSize) {
        int defaultColumnIndex;
        defaultColumnIndex = value % boardSize - 1;
        if (defaultColumnIndex == -1) {
            defaultColumnIndex = boardSize - 1;
        }
        return defaultColumnIndex;
    }

    private int getDefaultRowIndexFromValue(int value, int boardSize) {
        int defaultRowIndex;
        defaultRowIndex = (value - 1) / boardSize;
        return defaultRowIndex;
    }

    private Iterable<Board> calculateNeighbours() {

        List<Board> result = new LinkedList<Board>();
        int i0 = -10, j0 = -10;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i+j*size] == 0) {
                    i0 = i;
                    j0 = j;
                }
            }
        }

        final int[][] SHIFTS_TO_NEIGHBOURS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < SHIFTS_TO_NEIGHBOURS.length; i++) {
            int iNeighbour = i0 - SHIFTS_TO_NEIGHBOURS[i][0];
            int jNeighbour = j0 - SHIFTS_TO_NEIGHBOURS[i][1];
            if (iNeighbour >= 0 && jNeighbour >= 0 && iNeighbour < size && jNeighbour < size) {
                int[][] boardTemp = copyBlocks();
                boardTemp[i0][j0] = boardTemp[iNeighbour][jNeighbour];
                boardTemp[iNeighbour][jNeighbour] = 0;
                Board neighbourBoard = new Board(boardTemp, true);
                result.add(neighbourBoard);
            }
        }
        return result;
    }

    private int[][] copyBlocks() {
        int[][] boardTemp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardTemp[i][j] = board[i+j*size];
            }
        }
        return boardTemp;
    }
}
