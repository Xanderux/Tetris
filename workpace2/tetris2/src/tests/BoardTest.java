package tests;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import model.Board;
import model.Piece;
import model.PieceType;

public class BoardTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    @SuppressWarnings("deprecation")
	@Test
    public void newBoardIsFullOfEmptyCells() {
        Board board = new Board();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                assertTrue(board.getBoardAt(x, y).isEmpty());
            }
        }
    }

    @Test
    public void moveDown() {

        final Board board = new Board();
        board.setCurrentPiece(Piece.getPiece(PieceType.O));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    private interface Repeater {
        void doAction();
    }

    private void repeat(Repeater repeat, int count) {
        for (int i = 0; i < count; i++) {
            repeat.doAction();
        }
    }

}
