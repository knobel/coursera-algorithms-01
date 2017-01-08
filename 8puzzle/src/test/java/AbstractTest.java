import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.IOException;

/**
 * Created by michal on 08.01.17.
 */
public abstract class AbstractTest {
    File resourcesDirectory = new File("src/test/resources/data");

    protected int[][] getTilesFromFile(String fileName) throws IOException {
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
}
