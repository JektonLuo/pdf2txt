import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Jekton Luo
 * @version 0.01 6/5/2015.
 */
public class StringUtils {

    private StringUtils() {}



    /**
     * Extract the orderID from a string
     * @param line string that contain a number
     * @return return a int that contained in the given line
     */
    public static int extractOrderID(String line) {

        String id = line.replaceAll("\\D", "");
//        System.out.println("#" + id + "#");
        return Integer.parseInt(id);
    }



    public static String readFromInputStream(InputStream inputStream) throws IOException {
        return readFromInputStream(inputStream, "UTF-8");
    }


    public static String readFromInputStream(InputStream inputStream, String encoding)
            throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();
    }

}
