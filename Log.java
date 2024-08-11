
// Log.java
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static final String LOG_FILE = "log.txt";

    public static void logMove(String move) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(move + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
