import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by michal on 06.01.17.
 */
public class Solver {
    private MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
    private MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();
    private SearchNode solutionNode = null;

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        SearchNode searchNode = new SearchNode(initial, null);
        SearchNode twinSearchNode = new SearchNode(initial.twin(), null);

        queue.insert(searchNode);
        twinQueue.insert(twinSearchNode);

        while (!searchNode.board.isGoal() && !twinSearchNode.board.isGoal()) {

            searchNode = queue.delMin();
            Iterable<Board> neighbors = searchNode.board.neighbors();
            for (Board board: neighbors) {
                if (searchNode.previousSearchNode == null || !board.equals(searchNode.previousSearchNode.board)) {
                    queue.insert(new SearchNode(board, searchNode));
                }
            }

            twinSearchNode = twinQueue.delMin();
            Iterable<Board> twinNeighbors = twinSearchNode.board.neighbors();
            for (Board board: twinNeighbors) {
                if (twinSearchNode.previousSearchNode == null || !board.equals(twinSearchNode.previousSearchNode.board)) {
                    twinQueue.insert(new SearchNode(board, twinSearchNode));
                }
            }
        }

        if (searchNode.board.isGoal()) {
            solutionNode = searchNode;
        }

    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return solutionNode != null ? true : false;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return solutionNode != null ? solutionNode.moves : -1;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> stack = new Stack<Board>();
        SearchNode current = solutionNode;
        while (current != null) {
            stack.push(current.board);
            current = current.previousSearchNode;
        }
        return stack;
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    }

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
            private SearchNode previousSearchNode;
        private int moves;
        private int priority;

        public SearchNode(Board board, SearchNode previousBoard) {
            this.board = board;
            this.previousSearchNode = previousBoard;
            if (previousBoard == null) {
                moves = 0;
            } else {
                moves = previousBoard.moves + 1;
            }
            priority = board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            if (this.priority > that.priority) {
                return 1;
            } else if (this.priority == that.priority) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}

