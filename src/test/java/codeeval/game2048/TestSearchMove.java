package codeeval.game2048;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mullermw on 8/21/15.
 */
public class TestSearchMove {

    @Test
    public void testSearchMove() {
        int[][] board = {
                {0,0,0,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 0, 0, Direction.DOWN);
        Assert.assertArrayEquals(expected, board);
    }
    @Test
    public void testSearchMove0() {
        int[][] board = {
                {0,0,0,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {1,0,0,0}
        };
        Board.searchMove(board, 0, 3, Direction.UP);
        Assert.assertArrayEquals(expected, board);
    }
    @Test
    public void testSearchMove1() {
        int[][] board = {
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 0, 0, Direction.RIGHT);
        Assert.assertArrayEquals(expected, board);
    }
    @Test
    public void testSearchMove2() {
        int[][] board = {
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,0,1},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 3, 0, Direction.LEFT);
        Assert.assertArrayEquals(expected, board);
    }

    @Test
    public void testSearchMove3() {
        int[][] board = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 2, 0, Direction.LEFT);
        Assert.assertArrayEquals(expected, board);
    }

    @Test
    public void testSearchMove4() {
        int[][] board = {
                {0,0,0,0},
                {0,1,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,0,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 2, 1, Direction.LEFT);
        Assert.assertArrayEquals(expected, board);
    }

    @Test
    public void testSearchMove5() {
        int[][] board = {
                {0,0,0,0},
                {0,2,1,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,0,0},
                {0,0,2,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Board.searchMove(board, 2, 1, Direction.LEFT);
        Assert.assertArrayEquals(expected, board);
    }

    @Test
    public void testSearchMove6() {
        int[][] board = {
                {0,0,0,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        int[][] expected = {
                {0,0,0,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        boolean b = Board.searchMove(board, 2, 1, Direction.LEFT);
        Assert.assertArrayEquals(expected, board);
        Assert.assertFalse(b);
    }
}
