import java.io.*;

public class TXTFileReader {
    static File file;
    public TXTFileReader(String filePath) {
        this.file = new File(filePath);
    }

    public String GetContent() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder("");
        String line;
        int linesRead = 0;
        while ((line = br.readLine()) != null)
            content.append(line + " ");
        return content.toString();
    }
}
