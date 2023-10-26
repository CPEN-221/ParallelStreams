import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TXTFileReader {
    private File file;
    public TXTFileReader(String filePath) {
        this.file = new File(filePath);
    }

    public String GetContent() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder("");
        String line;
        while ((line = br.readLine()) != null)
            content.append(line + " ");
        return content.toString();
    }
}
