package pieces;

import chess.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {

    Cell[][] board;

    @BeforeEach
    public void init() {
        board = new Cell[8][8];
        for(int i =0; i< 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, null);
            }
        }
    }

    String translate(int x, int y) {
        return "(" + x + "," + y + ")";
    }

    @Test
    @DisplayName("No one around the king. Blank board")
    public void testKing1_move() {
        King king = new King("WK","White_King.png",0,1,2);

        board[1][2].setPiece(king);
        ArrayList<Cell> rawMoves = king.move(board, 1, 2);

        Set<String> moves = new HashSet<>();
        for(Cell move: rawMoves) {
            moves.add(translate(move.x, move.y));
        }

        Set<String> expected = new HashSet<>();
        expected.add(translate(1,3));
        expected.add(translate(1,1));
        expected.add(translate(2,3));
        expected.add(translate(2,1));
        expected.add(translate(0,3));
        expected.add(translate(0,1));
        expected.add(translate(0,2));
        expected.add(translate(2,2));
        assertEquals(expected, moves);
    }

    @Test
    @DisplayName("Pawn placed around the king")
    public void testKing2_move() {
        King king = new King("WK","White_King.png",0,1,2);

        board[1][2].setPiece(king);
        board[1][3].setPiece(new Pawn("WP", "White_Pawn.png", 0));

        ArrayList<Cell> rawMoves = king.move(board, 1, 2);

        Set<String> moves = new HashSet<>();
        for(Cell move: rawMoves) {
            moves.add(translate(move.x, move.y));
        }

        Set<String> expected = new HashSet<>();
        expected.add(translate(1,1));
        expected.add(translate(2,3));
        expected.add(translate(2,1));
        expected.add(translate(0,3));
        expected.add(translate(0,1));
        expected.add(translate(0,2));
        expected.add(translate(2,2));
        assertEquals(expected, moves);
    }
}