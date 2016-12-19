import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 18.12.16.
 */
public class BruteCollinearPoints {
    private List<LineSegment> lineSegments = new LinkedList<>();

    public BruteCollinearPoints(Point[] points) {
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
            for (int j = i + 1; j < points1.length; j++) {
                for (int k = j + 1; k < points1.length; k++) {
                    for (int l = k + 1; l < points1.length; l++) {

                        double slope1 = points1[i].slopeTo(points1[j]);
                        double slope2 = points1[i].slopeTo(points1[k]);
                        double slope3 = points1[i].slopeTo(points1[l]);

                        if (slope1 == slope2 && slope1 == slope3) {
                            lineSegments.add(new LineSegment(points1[i], points1[l]));
                        }
                    }
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