
// SelectWord.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;


public class SelectWord {

    private static final String WORD_FILE = "words.txt";
    private static final Random rand = new Random();

    public static String getRandomWord() {
        try {
            List<String> words = Files.readAllLines(Path.of(WORD_FILE));
            int index = rand.nextInt(words.size());
            System.out.println("THE WORD IS " + words.get(index));
            return words.get(index);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
