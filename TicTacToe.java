import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static int moveCounter = 0;
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        do {
            clearBoard();
            moveCounter = 0;
            currentPlayer = "X";

            boolean gameWon = false;
            while (moveCounter < ROWS * COLS) {
                displayBoard();
                int row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter col (1-3): ", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("That space is already taken. Try again.");
                    row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(console, "Enter col (1-3): ", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;
                moveCounter++;

                if (moveCounter >= 5 && isWin(currentPlayer)) {
                    displayBoard();
                    System.out.println(currentPlayer + " wins!");
                    gameWon = true;
                    break;
                }

                if (isTie()) {
                    displayBoard();
                    System.out.println("The game is a tie!");
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        } while (SafeInput.getYNConfirm(console, "Play again?"));
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void displayBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("  -----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        if (moveCounter < ROWS * COLS) {
            return false;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
