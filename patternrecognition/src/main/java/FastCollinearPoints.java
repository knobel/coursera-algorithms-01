import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 18.12.16.
 */
public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new LinkedList<>();

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new NullPointerException();
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Point[] points1 = Arrays.copyOf(points, points.length);
        Arrays.sort(points1);

        for (int i = 0; i < points1.length; i++) {
            Point[] points2 = Arrays.copyOfRange(points1, i + 1, points1.length);
            Arrays.sort(points2, points1[i].slopeOrder());
            int counter = 0;
            for (int j = 0; j < points2.length; j++) {
                if (j != points2.length - 1 && points2[j].slopeTo(points1[i]) == points2[j + 1].slopeTo(points1[i]))
                    counter++;
                else {
                    if (counter >= 2) {
                        Point[] points3 = Arrays.copyOfRange(points2, j - counter - 1, j);
                        Arrays.sort(points3);

                        lineSegments.add(new LineSegment(points1[i], points3[points3.length - 1]));
                    }
                    counter = 0;
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[lineSegments.size()];
        lineSegments.toArray(result);
        return result;

    }
}
