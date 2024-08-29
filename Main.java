import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainMenuOption = 0;

        while (mainMenuOption != 3) { // Main loop to keep the program running until the user exits
            displayMainMenu();
            mainMenuOption = getValidInput(scanner, 1, 3);
            if (mainMenuOption == 1 || mainMenuOption == 2) { // Single-player or Two-player game loop
                int postGameOption = 0;
                while (postGameOption != 1) {
                    char[] board = new char[16];
                    initializeLockedCells(board);
                    playGame(board, mainMenuOption);
                    displayPostGameMenu();
                    postGameOption = getValidInput(scanner, 1, 2);
                }
            }
        }
        scanner.close();
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

    public static void playGame(char[] board, int gameMode) {
        int moveCount = 0;
        displayBoard(board); // Show initial board state
        getPlayerMove(board, gameMode == 1 ? "Enter your number please:" : "Player 1:", 'x'); // First move

        while (moveCount < 6) {
            displayBoard(board);
            if (gameMode == 1) {
                getComputerMove(board); // Computer's move
                moveCount = checkForWinner(board, "Computer wins!", 'o', moveCount); // Check if computer wins
            } else {
                getPlayerMove(board, "Player 2:", 'o'); // Player 2's move
                moveCount = checkForWinner(board, "Player 2 wins!", 'o', moveCount); // Check if Player 2 wins
            }
            if (moveCount < 6) {
                displayBoard(board);
                getPlayerMove(board, gameMode == 1 ? "Enter your number please" : "Player 1:", 'x'); // Player's move
                moveCount = checkForWinner(board, gameMode == 1 ? "You win!" : "Player 1 wins!", 'x', moveCount); // Check if player wins
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
                if (board[col] != '#' && board[col] != 'x' && board[col] != 'o') {
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
            int selectedCell = getValidInput(scanner, 1, 16) - 1;
            if (board[selectedCell] != '#' && board[selectedCell] != 'x' && board[selectedCell] != 'o') {
                board[selectedCell] = playerSymbol;
                break;
            }
            System.out.println("Invalid cell! Choose an available cell.");
        }
    }

    public static int getValidInput(Scanner scanner, int min, int max) {
        // Validates and returns a number within the specified range
        int input = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
            } else {
                scanner.next(); // Skip the invalid input
            }
            System.out.println("Invalid number! Please enter a number between " + min + " and " + max + ":");
        }
    }
}
