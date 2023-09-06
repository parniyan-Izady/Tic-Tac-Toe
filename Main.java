import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int f = 20;
        while (f != 3) {
            Scanner scanner = new Scanner(System.in);
            menu();
            f = scanner.nextInt();
            if (f == 1) {
                int x = 10;
                while (x != 1) {
                    char[] str = new char[16];
                    getRandomNumbers1(str);
                    onePlayerMode(str);
                    System.out.println("-------------------------------------");
                    System.out.println("1)Return to first menu!");
                    System.out.println("2)Restart this game!");
                    System.out.println("Enter your number...");
                    x = scanner.nextInt();
                    if (x == 1) {
                        break;
                    }
                    while (x > 2) {
                        System.out.println("Invalid number!");
                        System.out.println("Enter your number...");
                        x = scanner.nextInt();
                    }
                }
            }
            if (f == 2) {
                int x = 0;
                while (x != 1) {
                    char[] str = new char[16];
                    getRandomNumbers1(str);
                    twoPlayersMode(str);
                    System.out.println("-------------------------------------");
                    System.out.println("1)Return to first menu!");
                    System.out.println("2)Restart this game!");
                    System.out.println("Enter your number...");
                    x = scanner.nextInt();
                    if (x == 1) {
                        break;
                    }
                    while (x > 2) {
                        System.out.println("Invalid number!");
                        System.out.println("Enter your number...");
                        x = scanner.nextInt();
                    }
                }
            }
        }
    }

    //Menu surface
    public static void menu() {
        System.out.println("1) Play with computer");
        System.out.println("2) Two players");
        System.out.println("3) Exit");
        System.out.println("Enter your number...");
    }

    //Playing with computer
    public static void onePlayerMode(char str[]) {
        int p = 0;
        showString(str);
        readNumber(str, "Enter your number please:", 'x');
        {
            while (p < 6) {
                if (p < 6) {
                    showString(str);
                    getRandomNumbers2(str);
                    p = winner(str, "Computer wins!", 'o', p);
                }
                if (p < 6) {
                    showString(str);
                    readNumber(str, "Enter your number please", 'x');
                    p = winner(str, "You win!", 'x', p);
                    p++;
                }
            }
            showString(str);
            if (p == 6) {
                System.out.println("It's a tie");
            }
        }
    }

    //Playing with another player
    public static void twoPlayersMode(char str[]) {
        int p = 0;
        showString(str);
        readNumber(str, "Player 1:", 'x');
        while (p < 6) {
            if (p < 6) {
                showString(str);
                readNumber(str, "player 2:", 'o');
                p = winner(str, "Player 2 wins!", 'o', p);
            }
            
            if (p < 6) {
                showString(str);
                readNumber(str, "player 1:", 'x');
                p = winner(str, "Player 1 wins!", 'x', p);
                p++;
            }
        }
        showString(str);
        if (p == 6) {
            System.out.println("It's a tie!");
        }
    }

    //Getting three random numbers for locking
    public static void getRandomNumbers1(char[] str) {
        int i = 0;
        while (i != 3) {
            Random random = new Random();
            int value = random.nextInt(16);
            if (str[value] != '#') {
                str[value] = '#';
                i++;
            }
        }
    }

    //Getting random numbers for computer
    public static void getRandomNumbers2(char[] str) {
        while (true) {
            Random random2 = new Random();
            int value = random2.nextInt(16);
            if (str[value] != '#' && str[value] != 'x' && str[value] != 'o') {
                str[value] = 'o';
                break;
            }
        }
    }

    //Winner algorithm
    public static int checkWinner(char[] str, String prompt2, char chr, int p, int u, int z) {
        if (p < 6) {
            if (str[u] == chr && str[u + z] == chr && str[u + 2 * z] == chr) {
                System.out.println(prompt2);
                p = 10;
            }
        }
        return p;
    }

    //Winner
    public static int winner(char[] str, String prompt2, char chr, int p) {
        //checking the game algorithm with step 1
        for (int y = 0; y < 4; y++) {
            p = checkWinner(str, prompt2, chr, p, 4 * y, 1);
            p = checkWinner(str, prompt2, chr, p, 4 * y + 1, 1);
        }

        //checking the game algorithm with step 3
        p = checkWinner(str, prompt2, chr, p, 2, 3);
        p = checkWinner(str, prompt2, chr, p, 3, 3);
        p = checkWinner(str, prompt2, chr, p, 6, 3);
        p = checkWinner(str, prompt2, chr, p, 7, 3);

        //checking the game algorithm with step 4
        for (int u = 0; u < 8; u++) {
            p = checkWinner(str, prompt2, chr, p, u, 4);
        }

        //checking the game algorithm with step 5
        p = checkWinner(str, prompt2, chr, p, 0, 5);
        p = checkWinner(str, prompt2, chr, p, 1, 5);
        p = checkWinner(str, prompt2, chr, p, 4, 5);
        p = checkWinner(str, prompt2, chr, p, 5, 5);
        return p;
    }

    //Showing the appearance of the game
    public static void showString(char[] str) {
        System.out.println("-------------------------------------");
        for (int z = 0; z < 4; z++) {
            System.out.print("|   ");
            for (int y = 4 * z; y < 4 * (z + 1); y++) {
                if (str[y] != '#' && str[y] != 'x' && str[y] != 'o') {
                    if (y < 9) {
                        System.out.print(0);
                    }
                    System.out.print(y + 1);
                    System.out.print("   |   ");
                } else {
                    System.out.print(" ");
                    System.out.print(str[y]);
                    System.out.print("   |   ");
                }
            }
            System.out.println();
            System.out.println("-------------------------------------");
        }
    }

    //Getting the numbers and checking its validity
    public static void readNumber(char[] str, String prompt, char value3) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(prompt);
            int value2 = scanner.nextInt();
            value2 = value2 - 1;
            if (value2 >= 0 && value2 <= 15 && str[value2] != '#' && str[value2] != 'x' && str[value2] != 'o') {
                str[value2] = value3;
                break;
            }
            System.out.println("Invalid number!");
        }
    }
}
