package Task2AndTaskAdvanced;

import Task2AndTaskAdvanced.TicTacToe.TypeFilled;

public class TestMethod {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(5, TypeFilled.FILLED_COLUMN);
        ticTacToe.displayBoard();
        System.out.println(ticTacToe.checkColumns());
        System.out.println(ticTacToe.checkRows());
        System.out.println(ticTacToe.checkDiagonals());
    }
}
