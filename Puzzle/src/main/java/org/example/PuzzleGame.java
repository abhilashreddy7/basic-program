package org.example;
import java.util.Arrays;
import java.util.Scanner;

public class PuzzleGame {
    private int[][] board;
    private int boardSize;
    private int emptyRow;
    private int emptyCol;

    public PuzzleGame(int size) {
        boardSize = size;
        board = new int[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        int count = 1;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = count;
                count++;
            }
        }
        emptyRow = boardSize - 1;
        emptyCol = boardSize - 1;
        board[emptyRow][emptyCol] = 0;
        shuffleBoard();
    }

    private void shuffleBoard() {
        int numShuffles = boardSize * boardSize * 10;
        for (int i = 0; i < numShuffles; i++) {
            int randomDirection = (int) (Math.random() * 4); // 0: Up, 1: Down, 2: Left, 3: Right
            moveTile(randomDirection);
        }
    }

    public void printBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!isSolved()) {
            System.out.println("Enter a move (WASD):");
            String move = scanner.nextLine().toUpperCase();
            switch (move) {
                case "W":
                    moveTile(0);
                    break;
                case "A":
                    moveTile(2);
                    break;
                case "S":
                    moveTile(1);
                    break;
                case "D":
                    moveTile(3);
                    break;
                default:
                    System.out.println("Invalid move! Use WASD keys.");
            }
            printBoard();
        }
        scanner.close();
        System.out.println("Congratulations! You solved the puzzle.");
    }

    private void moveTile(int direction) {
        int newRow = emptyRow;
        int newCol = emptyCol;

        switch (direction) {
            case 0: // Up
                newRow--;
                break;
            case 1: // Down
                newRow++;
                break;
            case 2: // Left
                newCol--;
                break;
            case 3: // Right
                newCol++;
                break;
        }

        if (newRow >= 0 && newRow < boardSize && newCol >= 0 && newCol < boardSize) {
            board[emptyRow][emptyCol] = board[newRow][newCol];
            board[newRow][newCol] = 0;
            emptyRow = newRow;
            emptyCol = newCol;
        }
    }

    private boolean isSolved() {
        int count = 1;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (row == boardSize - 1 && col == boardSize - 1) {
                    if (board[row][col] != 0) {
                        return false;
                    }
                } else if (board[row][col] != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PuzzleGame game = new PuzzleGame(3); // Create a 3x3 puzzle
        game.printBoard();
        game.playGame();
    }
}
