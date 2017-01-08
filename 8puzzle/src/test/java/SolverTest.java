import org.junit.Assert;
import org.junit.Test;

/**
 * Created by michal on 08.01.17.
 */
public class SolverTest extends AbstractTest {

    @Test
    public void getMoves1() throws Exception {
        Solver solver = new Solver(new Board(getTilesFromFile("puzzle01.txt")));
        Assert.assertEquals(1,solver.moves());
    }

    @Test
    public void getMoves2() throws Exception {
        Solver solver = new Solver(new Board(getTilesFromFile("puzzle2x2-unsolvable1.txt")));
        Assert.assertEquals(-1,solver.moves());
    }

    @Test
    public void getMoves3() throws Exception {
        Solver solver = new Solver(new Board(getTilesFromFile("puzzle2x2-unsolvable1-twin.txt")));
        Assert.assertEquals(3,solver.moves());
    }

    @Test
    public void getMoves4() throws Exception {
        Solver solver = new Solver(new Board(getTilesFromFile("puzzle19.txt")));
        Assert.assertEquals(19,solver.moves());
    }

    @Test
    public void getMoves5() throws Exception {
        Solver solver = new Solver(new Board(getTilesFromFile("puzzle30.txt")));
        Assert.assertEquals(30,solver.moves());
    }


}