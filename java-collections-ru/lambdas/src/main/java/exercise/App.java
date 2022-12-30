package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImageStream(String[][] image) {
        return Arrays.stream(image)
                .flatMap(arr -> Arrays.stream(new String[][]{enlargeRowStream(arr), enlargeRowStream(arr)}))
                .toArray(String[][]::new);
    }

    private static String[] enlargeRowStream(String[] row) {
        return Arrays.stream(row)
                .flatMap(item -> Arrays.stream(new String[]{item, item}))
                .toArray(String[]::new);
    }

    public static String[][] enlargeArrayImageCircle(String[][] image) {
        var enlargedImage = new String[image.length * 2][];
        int idx = 0;
        for (var row : image) {
            enlargedImage[idx] = enlargeRowCircle(row);
            enlargedImage[idx + 1] = enlargeRowCircle(row);
            idx += 2;
        }
        return enlargedImage;
    }

    private static String[] enlargeRowCircle(String[] row) {
        var enlargedRow = new String[row.length * 2];
        var idx = 0;
        for (var element : row) {
            enlargedRow[idx] = element;
            enlargedRow[idx + 1] = element;
            idx += 2;
        }
        return enlargedRow;
    }
}
// END
