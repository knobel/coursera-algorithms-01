import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michal on 18.12.16.
 */
public class PointTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void slopeTo_45() throws Exception {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        double slope = point1.slopeTo(point2);
        assertEquals(1.0, slope, 0);
    }

    @Test
    public void slopeTo_33() throws Exception {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(3, 2);
        double slope = point1.slopeTo(point2);
        assertEquals(0.5, slope, 0);
    }

    @Test
    public void slopeTo_66() throws Exception {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 3);
        double slope = point1.slopeTo(point2);
        assertEquals(2.0, slope, 0);
    }

    @Test
    public void slopeTo_horizontal() throws Exception {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 1);
        double slope = point1.slopeTo(point2);
        assertEquals(0.0, slope, 0);
    }

    @Test
    public void slopeTo_vertical() throws Exception {
        Point point1 = new Point(2, 1);
        Point point2 = new Point(2, 4);
        double slope = point1.slopeTo(point2);
        assertEquals(Double.POSITIVE_INFINITY, slope, 0);
    }

    @Test
    public void slopeTo_theSamePoint() throws Exception {
        Point point1 = new Point(2, 1);
        Point point2 = new Point(2, 1);
        double slope = point1.slopeTo(point2);
        assertEquals(Double.NEGATIVE_INFINITY, slope, 0);
    }

}