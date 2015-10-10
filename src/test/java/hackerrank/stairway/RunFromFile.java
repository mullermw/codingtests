package hackerrank.stairway;

import java.io.FileInputStream;

/**
 * Created by mullerm on 10/10/15.
 */
public class RunFromFile {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        try {
            Solution.run(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }
}
