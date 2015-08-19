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

        try {
            String dataString = reader.readString();

            int orderId = StringUtils.extractOrderID(dataString.substring(0, dataString.indexOf("\n")));
            int date = DateUtils.getOrderDate();
            String dateAndOrderId = String.format("%05d%04d", date, orderId);

            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(args[1]), "GBK")) {
                writer.write(dataString + dateAndOrderId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
