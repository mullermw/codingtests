package codeeval.game2048;

import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] fields = line.split("; ");
            Direction direction = Direction.valueOf(fields[0]);
            Board board = Board.newBoard(Integer.parseInt(fields[1]), fields[2]);
            System.out.println(board.move(direction).toSingleLine());
        }
    }
}

enum Direction {

    UP(0,-1),RIGHT(1,0),DOWN(0,1),LEFT(-1,0);

    int x,y;
    Direction opposite;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction opposite() {
        return opposite;
    }

}

class Board {
    private final int[][] board; //x,y:  -1 indicates empty

    private Board(int[][] board) {
        this.board = board;
    }

    public static Board newBoard(int dim, String string) {
        int[][] arr = new int[dim][dim];
        String[] rowFields = string.split("\\|");
        for (int x=0; x<rowFields.length; ++x) {
            String[] colFields = rowFields[x].split("\\s+");
            for (int y=0; y<colFields.length; ++y) {
                arr[x][y] = Integer.parseInt(colFields[y]);
            }
        }
        return new Board(arr);
    }

    public Board move (Direction direction) {
        boolean rowWise = direction.x != 0;
        Direction searchDirection = direction.opposite();
        //parameters for m loop (processing lines)
        int incr = rowWise ? searchDirection.x : searchDirection.y;
        int start;
        int end;
        if (incr == 1) {
            start = 0;
            end = board.length-1;
        } else {
            start = board.length - 1;
            end = 0;
        }

        int x,y,nx,ny;
        for (int l=0; l < board.length; ++l) { //line (row or column) number
            if (rowWise) {
                x = start;
                y = l;
                nx = x + incr;
                ny = y;
            } else {
                x = l;
                y = start;
                nx = x;
                ny = y + incr;
            }
            boolean lineExhausted = false;
            for (int m=start; m != end && !lineExhausted; m+=incr) { //items in a line
                if (rowWise) {
                    x = m;
                    nx = x + incr;
                } else {
                    y = m;
                    ny = y + incr;
                }
                //System.out.println("Neighbor of "+x+","+y+" is "+nx+","+ny);
                if (board[y][x] == 0) {
                    lineExhausted = !searchMove(board, x, y, searchDirection);
                }

                if (board[ny][nx] == 0 && !lineExhausted) {
                    lineExhausted = !searchMove(board, nx, ny, searchDirection);
                }

                if (board[y][x] != 0 && board[y][x] == board[ny][nx]) {
                    board[y][x] *= 2;
                    board[ny][nx] = 0;
                    //System.out.println("Collapsing "+x+","+y+" to "+ board[y][x]);
                }
            }
        }
        //System.out.println(i + " " + row + " " + col + " " + neighborRow + " " + neighborCol);

        return this;

    }

    //searches board from position x,y in searchDirection for a non-zero value.
    //if such a value is found, the value at the found position is moved to x,y and
    // replaced with a zero.
    //returns true if a value is moved.
    static boolean searchMove(int[][] board, int x, int y,
                                     Direction searchDirection //direction from x,y to search in
    ){
        boolean searchX = searchDirection.x != 0;
        int searchStart;
        int searchEnd;
        int searchIncr;
        if (searchX) {//horizontal search
            searchIncr = searchDirection.x;
            searchStart=x+searchIncr;
            searchEnd = searchIncr == 1 ? board.length : -1;
        } else { //vertical search
            searchIncr = searchDirection.y;
            searchStart=y+searchIncr;
            searchEnd = searchIncr == 1 ? board.length : -1;
        }
        int sx=x,sy=y; //lookupCoordinates
        for (int i=searchStart; i != searchEnd; i += searchIncr) { //search and move
            if (searchX) {
                sx = i;
            } else {
                sy = i;
            }
            if (board[sy][sx] != 0) {
                board[y][x] = board[sy][sx];
                board[sy][sx] = 0;
                return true;
            }
        }
        return false; //no move
    }

    public String toGridString() {
        StringBuilder sb = new StringBuilder();
        for (int y=0; y != board.length; ++y) {
            for (int x=0; x != board.length; ++x) {
                sb.append(String.format("%4d", board[y][x]));
            }
            if (y < board.length - 1) {
                sb.append(String.format("%n"));
            }
        }
        return sb.toString();
    }

    public String toSingleLine() {
        StringBuilder sb = new StringBuilder();
        for (int y=0; y != board.length; ++y) {
            for (int x=0; x != board.length; ++x) {
                sb.append(board[y][x]);
                if (x < board.length - 1)
                    sb.append(" ");
            }
            if (y < board.length - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}