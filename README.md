# 🎯 Wordle Game with JavaFX

Welcome to the Wordle game implemented using JavaFX! This project is a desktop version of the popular word puzzle game, Wordle.

## 📑 Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## 📘 Introduction

Wordle is a word puzzle game where the player has six attempts to guess a five-letter word. Each guess must be a valid five-letter word, and after each guess, the game provides feedback indicating which letters are correct and in the correct position.

## ✨ Features

- 🖥️ Interactive GUI built with JavaFX
- ⚡ Real-time feedback on guesses
- ⌨️ Keyboard input for easy gameplay
- 🎨 Highlighting of correct, incorrect, and misplaced letters
- 🎲 Random word selection from a predefined list

## 🚀 Installation

### Prerequisites

- ☕ Java Development Kit (JDK) 8 or higher
- 📦 JavaFX SDK

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/karamhaddad/Wordle.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Wordle
    ```

3. Compile the project:
    ```bash
    javac -cp path/to/javafx-sdk/lib/*:. src/main/java/com/yourpackage/*.java
    ```

4. Run the project:
    ```bash
    java -cp path/to/javafx-sdk/lib/*:. -Djava.library.path=path/to/javafx-sdk/lib com.yourpackage.Main
    ```

## 🎮 Usage

1. Start the game by running the main class.
2. Use the keyboard to input your guesses.
3. The game will provide feedback after each guess:
   - 🟩 **Green** for correct letters in the correct position.
   - 🟨 **Yellow** for correct letters in the wrong position.
   - 🟥 **Gray** for incorrect letters.

## 🤝 Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes. Ensure your code adheres to the project’s coding standards and includes relevant tests.

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
