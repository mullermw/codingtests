package codeeval.gameoflife;

import java.io.*;
import java.nio.file.*;
import java.util.BitSet;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Main {
    public static void main (String[] args) throws IOException {

        Board board = Board.from(Files.lines(Paths.get(args[0])));

        System.out.println(board);
        System.out.println();
        for (int i=0; i<10; ++i) {
            board = board.next();
        }
        System.out.println(board);
    }
}

class Board {
    private final BitSet bitSet;
    private final int xdim;
    private final int ydim;

    private static final char ALIVE_TOKEN = '*';
    private static final String NEWLINE = String.format("%n");
    private static int[][] neighborOffsets = new int[][] {
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
    };

    private Board(BitSet bitSet, int xdim, int ydim) {
        this.bitSet = bitSet;
        this.xdim = xdim;
        this.ydim = ydim;
    }

    private Board(int xdim, int ydim) {
        this.bitSet = new BitSet(xdim * ydim);
        this.xdim = xdim;
        this.ydim = ydim;
    }

    public boolean isAlive(int x, int y) {
        return bitSet.get(y * xdim + x);
    }

    public void setAlive(int x, int y, boolean alive) {
        bitSet.set(y * xdim + x, alive);
    }

    public Board next() {
        Board next = new Board(xdim, ydim);
        for (int i=0; i<bitSet.length(); ++i) {
            int x=i % xdim;
            int y=i / xdim;

            //count live neighbors
            int liveNeigbors = 0;
            for (int[] neighborOffset : neighborOffsets) {
                int nx = x + neighborOffset[0];
                if (nx >= 0 && nx < xdim) {
                    int ny = y + neighborOffset[1];
                    if (ny >= 0 && ny < ydim && isAlive(nx, ny)) {
                        ++liveNeigbors;
                        if (liveNeigbors == 4) break; //4 or more is treated the same.
                    }
                }
            }

            boolean alive = (liveNeigbors == 3)
                    || (isAlive(x, y) && liveNeigbors == 2);
            next.setAlive(x, y, alive);
        }
        return next;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0, l=xdim*ydim; i<l; ++i) {
            builder.append(bitSet.get(i) ? ALIVE_TOKEN : '.');
            if (i % xdim == xdim - 1 && i != l-1) {
                builder.append(NEWLINE);
            }
        }
        return builder.toString();
    }

    public static Board from(Stream<String> stream) {
        StringConsumerBoardBuilder boardBuilder = new StringConsumerBoardBuilder();
        stream.forEachOrdered(boardBuilder);
        return boardBuilder.build();
    }

    private static class StringConsumerBoardBuilder implements Consumer<String> {

        int xdim = -1;
        int ydim = 0;
        int size = 0;
        BitSet bitSet = new BitSet();

        @Override
        public void accept(String s) {

            if (xdim == -1) {
                xdim = s.length();
            } else if (xdim != s.length()) {
                throw new RuntimeException("inconsistent line lengths.");
            }

            ++ydim;
            s.chars().forEachOrdered(c -> bitSet.set(size++, c == ALIVE_TOKEN));

        }

        public Board build() {
            return new Board(bitSet, Math.max(xdim,0), ydim);
        }
    }

}



