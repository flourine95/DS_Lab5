package Task2AndTaskAdvanced;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char CHAR_X = 'X';
    private static final char CHAR_O = 'O';
    private final int SIZE;
    private final char[][] board;
    private char marked;

    public enum TypeFilled {
        FULL_CHAR_X, RANDOM, FILLED_DIAGONAL, FILLED_ROW, FILLED_COLUMN
    }

    public TicTacToe() {
        // default Tic Tac Toe
        this.SIZE = 3;
        this.board = new char[SIZE][SIZE];
        this.marked = CHAR_X;
    }

    public TicTacToe(int size) {
        // custom Tic Tac Toe
        this.SIZE = size;
        this.board = new char[SIZE][SIZE];
        this.marked = CHAR_X;
    }

    public TicTacToe(int size, TypeFilled typeFilled) {
        // genarate test Tic Tac Toe
        this.SIZE = size;
        this.board = new char[SIZE][SIZE];
        char[] charArray = {EMPTY, CHAR_X, CHAR_O};
        switch (typeFilled) {
            case FULL_CHAR_X -> Arrays.stream(this.board).forEach(chars -> Arrays.fill(chars, CHAR_X));
            case RANDOM -> {
                for (int i = 0; i < this.SIZE; i++) {
                    for (int j = 0; j < this.SIZE; j++) {
                        this.board[i][j] = charArray[getRandomChars(charArray)];
                    }
                }
            }
            case FILLED_DIAGONAL -> {
                // main diagonal or antidiagonal
                if (new Random().nextBoolean()) {
                    for (int i = 0; i < this.SIZE; i++) {
                        for (int j = 0; j < this.SIZE; j++) {
                            if (i != j) {
                                this.board[i][j] = charArray[getRandomChars(charArray)];
                            } else {
                                this.board[i][j] = CHAR_X;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < this.SIZE; i++) {
                        for (int j = 0; j < this.SIZE; j++) {
                            if (i != j - i) {
                                this.board[i][j] = charArray[getRandomChars(charArray)];
                            } else {
                                this.board[i][j] = CHAR_X;
                            }
                        }
                    }
                }
            }
            case FILLED_ROW -> {
                for (int i = 0; i < this.SIZE; i++) {
                    if (i == new Random().nextInt(this.SIZE)) {
                        Arrays.fill(this.board[i], CHAR_X);
                    }
                }
            }
            case FILLED_COLUMN -> {
                for (int i = 0; i < this.SIZE; i++) {
                    if (i == new Random().nextInt(this.SIZE)) {
                        for (int j = 0; j < this.SIZE; j++) {
                            this.board[j][i] = CHAR_X;
                        }
                    }
                }
            }
        }
        this.marked = CHAR_X;
    }

    private static int getRandomChars(char[] charArray) {
        return new Random().nextInt(charArray.length);
    }


    public void switchMarked() {
        this.marked = this.marked == CHAR_X ? CHAR_O : CHAR_X;
    }

    /*
        This method checks all rows and returns true
        if any of them are marked with all of a single player's markers.
        Otherwise, returns false.
    */
    public boolean checkRows() {
        for (char[] row : board) {
            boolean isMarked = true;
            for (char c : row) {
                if (c != marked) {
                    isMarked = false;
                    break;
                }
            }
            if (isMarked) {
                return true;
            }
        }
        return false;
    }

    /*
        This method checks all columns and returns true if any
        of them are marked with all of a single player's.
        Otherwise, returns false.
    */
    public boolean checkColumns() {
        for (int i = 0; i < board.length; i++) {
            boolean isMarked = true;
            for (char[] chars : board) {
                if (chars[i] != marked) {
                    isMarked = false;
                    break;
                }
            }
            if (isMarked) {
                return true;
            }
        }
        return false;
    }

    /*
        This method checks both diagonals and returns true if
        any of them are marked with all of a single player's markers.
        Otherwise, returns false.
    */
    public boolean checkDiagonals() {
        char firstDiagonal = marked;
        char secondDiagonal = marked;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != firstDiagonal) {
                firstDiagonal = '\0'; // Reset first diagonal if not a match
            }

            if (board[i][board.length - 1 - i] != secondDiagonal) {
                secondDiagonal = '\0'; // Reset second diagonal if not a match
            }
        }

        return (firstDiagonal == marked && firstDiagonal != '\0')
                || (secondDiagonal == marked && secondDiagonal != '\0');
    }

    public void play() {
        initBoard();
        int whoseTurn = 0;
        displayBoard();
        while (true) {
            if (whoseTurn % 2 == 0) {
                turnPlayer();
            } else {
                turnBot();
            }
            /*
             * The minimum condition to win
             * size = 2 -> 3 turn
             * size = 3 -> 5 turn
             * size = 4 -> 7 turn
             * size = 5 -> 9 turn
             * => 2 * size - 1
             * */
            if (whoseTurn >= 2 * this.SIZE - 2) {
                if (checkWinner()) {
                    if (this.marked == CHAR_X) {
                        System.out.println("Bạn đã thắng");
                        System.out.println("Bot đã thua");
                    } else {
                        System.out.println("Bot đã thắng");
                        System.out.println("Chỉ sợ lòng không bền, không sợ việc khó");
                        System.out.println("Làm lại từ đầu nào");
                    }
                    break;
                }
                if (isFull()) {
                    System.out.println("Trò chơi hòa");
                    break;
                }
            }
            switchMarked();
            whoseTurn++;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public void turnBot() {
        System.out.print("Đến lượt BOT, ");
        int row, col;
        do {
            row = new Random().nextInt(SIZE);
            col = new Random().nextInt(SIZE);
        } while (this.board[row][col] != EMPTY);
        this.board[row][col] = this.marked;
        System.out.println("BOT chọn vị trí: " + row + " " + col);
        displayBoard();
    }

    public void turnPlayer() {
        Scanner sc = new Scanner(System.in);
        int row, col;
        System.out.print("Nhập hàng và cột, cách nhau bởi dấu cách: ");
        row = sc.nextInt();
        col = sc.nextInt();
        while (row >= this.SIZE || col >= this.SIZE || row < 0 || col < 0 || this.board[row][col] != EMPTY) {
            if (row >= this.SIZE || col >= this.SIZE) {
                System.out.println("Không được nhập vị trí lớn hơn bàn cờ!!!");
                System.out.print("Nhập lại nào: ");
                row = sc.nextInt();
                col = sc.nextInt();
            } else if (row < 0 || col < 0) {
                System.out.println("Không được nhập vị trí âm!!!");
                System.out.print("Nhập lại nào: ");
                row = sc.nextInt();
                col = sc.nextInt();
            } else {
                if (this.board[row][col] != EMPTY) {
                    System.out.println("Không nhập vào những ô đã điền rồi!!!");
                    System.out.print("Nhập lại nào: ");
                    row = sc.nextInt();
                    col = sc.nextInt();
                }
            }
        }
        this.board[row][col] = this.marked;
        displayBoard();
    }


    public void displayBoard() {
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                System.out.print(this.board[i][j] == EMPTY ? "|" + "---" : "|" + " " + this.board[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public void initBoard() {
        Arrays.stream(this.board).forEach(chars -> Arrays.fill(chars, EMPTY));
    }

}
