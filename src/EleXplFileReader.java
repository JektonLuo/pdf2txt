
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Jekton Luo
 * @version 0.01 6/5/2015.
 */
public class EleXplFileReader implements Readable{

    private String filePath;
    private String stringContent;

    public EleXplFileReader(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public String readString() throws IOException {
        if (stringContent != null) return stringContent;

        FileInputStream inputStream = new FileInputStream(filePath);

        stringContent = StringUtils.readFromInputStream(inputStream, "GBK").trim()
                .replaceAll("\u001D!\u0011\u001BE\u0001", "")
                .replaceAll("\u001BE", "")
                .replaceAll("!", "")
                .replaceAll("\u0001", "")
                .replaceAll("\0", "")
                .replaceAll("\u001D", "")
                .replaceAll("&[^&;\\n]*;", "");
        stringContent = stringContent.substring(0, stringContent.length() - 1).trim();
        inputStream.close();

        return stringContent;
    }



    public static void main(String[] args) throws IOException {
        EleXplFileReader reader = new EleXplFileReader("E:/test/spl/test.spl");
        System.out.println(reader.readString());
    }
}
