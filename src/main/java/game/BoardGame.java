package game;

import java.util.Scanner;

public class BoardGame {

    private final Scanner scanner = new Scanner(System.in);
    private int winner = 0;
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void showBoard() {
        System.out.println("\n" + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            box[i] = ' ';
        }
    }

    public boolean writeResult() {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }
        return false;
    }

    public void movePlayer() {
        int input;
        while (true) {
            input = scanner.nextInt();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    public void checkWinner() {
        boolean boxAvailable = false;

        if (checkLine('X')) {
            winner = 1;
            return;
        }

        if (checkLine('O')) {
            winner = 2;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }

        if (!boxAvailable) {
            winner = 3;
        }
    }

    private boolean checkLine(char player) {
        return (box[0] == player && box[1] == player && box[2] == player) ||
                (box[3] == player && box[4] == player && box[5] == player) ||
                (box[6] == player && box[7] == player && box[8] == player) ||
                (box[0] == player && box[3] == player && box[6] == player) ||
                (box[1] == player && box[4] == player && box[7] == player) ||
                (box[2] == player && box[5] == player && box[8] == player) ||
                (box[0] == player && box[4] == player && box[8] == player) ||
                (box[2] == player && box[4] == player && box[6] == player);
    }

    public void moveComputer() {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * 9);
            if (box[rand] != 'X' && box[rand] != 'O') {
                box[rand] = 'O';
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter box number to select. Enjoy!");
        BoardGame boardGame = new BoardGame();
        boardGame.showBoard();
        boardGame.resetBoard();

        boolean gameOver = false;
        while (!gameOver) {
            boardGame.movePlayer();
            boardGame.checkWinner();
            gameOver = boardGame.writeResult();
            if (gameOver) {
                continue;
            }
            boardGame.moveComputer();
            boardGame.checkWinner();
            gameOver = boardGame.writeResult();
            if (!gameOver) {
                boardGame.showBoard();
            }
        }
    }
}
