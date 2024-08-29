import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int f = 20;
        // Main loop to keep the program running until the user exits
        while (f != 3) {
            Scanner scanner = new Scanner(System.in);
            displayMainMenu();
            f = scanner.nextInt();
            if (f == 1) { // Single-player game loop
                int x = 10;
                while (x != 1) {
                    char[] board = new char[16];
                    initializeLockedCells(board);
                    playOnePlayerGame(board);
                    displayPostGameMenu();
                    x = scanner.nextInt();
                    while (x > 2) { // Handle invalid input for post-game menu
                        System.out.println("Invalid number!");
                        System.out.println("Enter your number...");
                        x = scanner.nextInt();
                    }
                }
            }
            if (f == 2) { // Two-player game loop
                int x = 0;
                while (x != 1) {
                    char[] board = new char[16];
                    initializeLockedCells(board);
                    playTwoPlayersGame(board);
                    displayPostGameMenu();
                    x = scanner.nextInt();
                    while (x > 2) { // Handle invalid input for post-game menu
                        System.out.println("Invalid number!");
                        System.out.println("Enter your number...");
                        x = scanner.nextInt();
                    }
                }
            }
        }
    }

    public static void displayMainMenu() {
        // Displays the main menu options for the user
        System.out.println("1) Play with computer");
        System.out.println("2) Two players");
        System.out.println("3) Exit");
        System.out.println("Enter your number...");
    }

    public static void displayPostGameMenu() {
        // Displays options after a game ends
        System.out.println("-------------------------------------");
        System.out.println("1) Return to main menu!");
        System.out.println("2) Restart this game!");
        System.out.println("Enter your number...");
    }

    public static void playOnePlayerGame(char[] board) {
        int moveCount = 0;
        displayBoard(board); // Show initial board state
        getPlayerMove(board, "Enter your number please:", 'x'); // Player's first move
        while (moveCount < 6) {
            displayBoard(board);
            getComputerMove(board); // Computer's move
            moveCount = checkForWinner(board, "Computer wins!", 'o', moveCount); // Check if computer wins
            if (moveCount < 6) {
                displayBoard(board);
                getPlayerMove(board, "Enter your number please", 'x'); // Player's move
                moveCount = checkForWinner(board, "You win!", 'x', moveCount); // Check if player wins
                moveCount++;
            }
        }
        displayBoard(board); // Final board state
        if (moveCount == 6) {
            System.out.println("It's a tie"); // Declare a tie if no winner
        }
    }

    public static void playTwoPlayersGame(char[] board) {
        int moveCount = 0;
        displayBoard(board); // Show initial board state
        getPlayerMove(board, "Player 1:", 'x'); // Player 1's first move
        while (moveCount < 6) {
            displayBoard(board);
            getPlayerMove(board, "Player 2:", 'o'); // Player 2's move
            moveCount = checkForWinner(board, "Player 2 wins!", 'o', moveCount); // Check if Player 2 wins
            if (moveCount < 6) {
                displayBoard(board);
                getPlayerMove(board, "Player 1:", 'x'); // Player 1's move
                moveCount = checkForWinner(board, "Player 1 wins!", 'x', moveCount); // Check if Player 1 wins
                moveCount++;
            }
        }
        displayBoard(board); // Final board state
        if (moveCount == 6) {
            System.out.println("It's a tie!"); // Declare a tie if no winner
        }
    }

    public static void initializeLockedCells(char[] board) {
        int lockedCells = 0;
        // Randomly lock 3 cells on the board
        while (lockedCells != 3) {
            Random random = new Random();
            int randomIndex = random.nextInt(16);
            if (board[randomIndex] != '#') {
                board[randomIndex] = '#';
                lockedCells++;
            }
        }
    }

    public static void getComputerMove(char[] board) {
        // Computer randomly selects a valid cell for its move
        while (true) {
            Random random = new Random();
            int randomIndex = random.nextInt(16);
            if (board[randomIndex] != '#' && board[randomIndex] != 'x' && board[randomIndex] != 'o') {
                board[randomIndex] = 'o';
                break;
            }
        }
    }

    public static int checkForWinner(char[] board, String message, char playerSymbol, int moveCount) {
        // Check for winning condition in rows
        for (int row = 0; row < 4; row++) {
            moveCount = evaluateWinningCondition(board, message, playerSymbol, moveCount, 4 * row, 1);
        }
        // Check for winning condition in columns
        for (int col = 0; col < 4; col++) {
            moveCount = evaluateWinningCondition(board, message, playerSymbol, moveCount, col, 4);
        }
        // Check for winning condition in diagonals
        moveCount = evaluateWinningCondition(board, message, playerSymbol, moveCount, 0, 5);
        moveCount = evaluateWinningCondition(board, message, playerSymbol, moveCount, 3, 3);
        return moveCount;
    }

    public static int evaluateWinningCondition(char[] board, String message, char playerSymbol, int moveCount, int startIndex, int step) {
        // Evaluate the board for a winning condition based on start index and step size
        if (moveCount < 6) {
            if (board[startIndex] == playerSymbol && board[startIndex + step] == playerSymbol && board[startIndex + 2 * step] == playerSymbol) {
                System.out.println(message);
                moveCount = 10; // Arbitrary value to indicate a win
            }
        }
        return moveCount;
    }

    public static void displayBoard(char[] board) {
        // Display the current state of the game board
        System.out.println("-------------------------------------");
        for (int row = 0; row < 4; row++) {
            System.out.print("|   ");
            for (int col = 4 * row; col < 4 * (row + 1); col++) {
                if (board[col] != '#' && board[col] != 'x' && board[col] != 'o'){
                    if (col < 9) {
                        System.out.print(0);
                    }
                    System.out.print(col + 1);
                    System.out.print("   |   ");
                } else {
                    System.out.print(" ");
                    System.out.print(board[col]);
                    System.out.print("   |   ");
                }
            }
            System.out.println();
            System.out.println("-------------------------------------");
        }
    }

    public static void getPlayerMove(char[] board, String prompt, char playerSymbol) {
        // Get and validate player's move
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(prompt);
            int selectedCell = scanner.nextInt();
            selectedCell = selectedCell - 1;
            if (selectedCell >= 0 && selectedCell <= 15 && board[selectedCell] != '#' && board[selectedCell] != 'x' && board[selectedCell] != 'o') {
                board[selectedCell] = playerSymbol;
                break;
            }
            System.out.println("Invalid number!");
        }
    }
}
