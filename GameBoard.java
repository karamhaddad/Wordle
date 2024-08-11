
// GameBoard.java
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameBoard {

    private static final int ROWS = 6;
    private static final int COLS = 5;

    private Stage stage;
    private VBox root;
    private GridPane board;
    private HBox keyboard;
    private Label messageLabel;
    private Button resetButton;

    private String[][] shadowData;
    private int currentRow;
    private int currentCol;
    private String targetWord;

    private Stats stats;

    public GameBoard(Stage stage) {
        this.stage = stage;
        this.root = new VBox(10);
        this.board = new GridPane();
        this.keyboard = new HBox(5);
        this.messageLabel = new Label();
        this.resetButton = new Button("Reset");

        this.shadowData = new String[ROWS][COLS];
        this.currentRow = 0;
        this.currentCol = 0;

        this.stats = new Stats();

        initializeBoard();
        initializeKeyboard();

        this.targetWord = SelectWord.getRandomWord();

        resetButton.setOnAction(e -> resetGame());
        stage.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    private void initializeBoard() {
        board.setHgap(5);
        board.setVgap(5);
        board.setPadding(new Insets(10));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                board.add(rect, col, row);
            }
        }
    }

    private void initializeKeyboard() {
        String[] keyboardKeys = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"
        };

        for (String key : keyboardKeys) {
            Button button = new Button(key);
            button.setOnAction(e -> handleKeyboardInput(key));
            keyboard.getChildren().add(button);
        }
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (keyCode.isLetterKey()) {
            String letter = keyCode.getName();
            handleKeyboardInput(letter);
        } else if (keyCode == KeyCode.ENTER) {
            checkGuess();
        } else if (keyCode == KeyCode.BACK_SPACE) {
            deleteLetter();
        }
    }

    private void handleKeyboardInput(String letter) {
        if (currentCol < COLS) {
            Rectangle rect = (Rectangle) board.getChildren().get(currentRow * COLS + currentCol);
            rect.setFill(Color.WHITE);
            Label label = new Label(letter);
            label.setFont(Font.font(20));
            board.add(label, currentCol, currentRow);

            shadowData[currentRow][currentCol] = letter;
            currentCol++;
        }
    }

    private void checkGuess() {
        if (currentCol == COLS) {
            String guess = String.join("", shadowData[currentRow]);
            
            guess = guess.toLowerCase();//added to compare to txt file

            if (guess.equals(targetWord)) {
                messageLabel.setText("Congratulations! You guessed the word.");
                revealWord(Color.GREEN);
                disableInput();
                stats.incrementGamesWon();
            } else {
                for (int col = 0; col < COLS; col++) {
                    System.out.println("I am checking for individual letters");
                    String letter = shadowData[currentRow][col];
                    Rectangle rect = (Rectangle) board.getChildren().get(currentRow * COLS + col);

                    if (targetWord.charAt(col) == letter.charAt(0)) {
                        rect.setFill(Color.GREEN);
                    } else if (targetWord.contains(letter)) {
                        rect.setFill(Color.YELLOW);
                    } else {
                        rect.setFill(Color.GRAY);
                    }
                }

                currentRow++;
                currentCol = 0;

                if (currentRow == ROWS) {
                    messageLabel.setText("Game over! The word was: " + targetWord);
                    disableInput();
                }
            }

            stats.incrementGamesPlayed();
            updateStats();

            Log.logMove(guess);
        }
    }

    private void deleteLetter() {
        if (currentCol > 0) {
            currentCol--;
            Rectangle rect = (Rectangle) board.getChildren().get(currentRow * COLS + currentCol);
            board.getChildren().remove(currentRow * COLS + currentCol + 1);
            rect.setFill(Color.WHITE);
            shadowData[currentRow][currentCol] = null;
        }
    }

    private void revealWord(Color color) {
        for (int col = 0; col < COLS; col++) {
            Rectangle rect = (Rectangle) board.getChildren().get(currentRow * COLS + col);
            rect.setFill(color);
        }
    }

    private void disableInput() {
        keyboard.setDisable(true);
        stage.removeEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    private void resetGame() {
        board.getChildren().clear();
        initializeBoard();

        currentRow = 0;
        currentCol = 0;
        shadowData = new String[ROWS][COLS];

        targetWord = SelectWord.getRandomWord();
        messageLabel.setText("");

        keyboard.setDisable(false);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    private void updateStats() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Stats");
        alert.setHeaderText(null);
        alert.setContentText(
            "Games Played: " + stats.getGamesPlayed() + "\n" +
            "Games Won: " + stats.getGamesWon()
        );
        alert.showAndWait();
    }

    public void show() {
        root.getChildren().addAll(board, messageLabel, keyboard, resetButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Wordle");
        stage.show();
    }
}