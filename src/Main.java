import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Jekton on 8/15/2015.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("invalid arguments");
            System.exit(-1);
        }

        String fileName;
        if (args[0].contains("/")) {
            fileName = args[0].substring(args[0].lastIndexOf("/") + 1, args[0].length());
        } else if (args[0].contains("/")) {
            fileName = args[0].substring(args[0].lastIndexOf("\\") + 1, args[0].length());
        } else {
            fileName = args[0];
        }

        Readable reader;
        if (fileName.toLowerCase().endsWith("pdf")) {
            reader = new PDFReader(args[0]);
        } else {
            reader = new EleXplFileReader(args[0]);
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(args[1]), "GBK")) {
            writer.write(reader.readString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
