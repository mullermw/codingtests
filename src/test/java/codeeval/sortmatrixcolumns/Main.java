package codeeval.sortmatrixcolumns;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            List<List<Byte>> matrix = readMatrix(line);
            //printMatrix(matrix, System.out);
            Collections.sort(matrix, new Comparator<List<Byte>>() {
                @Override
                public int compare(List<Byte> o1, List<Byte> o2) {
                    for (int i=0; i<o1.size(); ++i) {
                        byte i1 = o1.get(i);
                        byte i2 = o2.get(i);
                        if (i1 < i2) return -1;
                        if (i1 > i2) return 1;
                    }
                    return 0;
                }
            });
            printMatrix(matrix, System.out);
        }
    }

    private static List<List<Byte>> readMatrix(String line) {
        String[] split = line.split("\\s*\\|\\s*");
        List<List<Byte>> list = new ArrayList<>(); //col then row
        while (list.size() < split.length) list.add(new ArrayList<Byte>());
        for (String rowString : split) {
            List<Byte> rowBytes = toBytes(rowString.split("\\s+"));
            for (int c=0; c<rowBytes.size(); ++c) {
                list.get(c).add(rowBytes.get(c));
            }
        }
        return list;
    }

    private static List<Byte> toBytes(String[] strings) {
        List<Byte> l = new ArrayList<>();
        for (String s : strings) {
            l.add(Byte.parseByte(s));
        }
        return l;
    }

    private static <T> void  printMatrix(List<List<T>> matrix, PrintStream ps) {
        for (int r=0, l=matrix.size(); r<l; ++r) {
            for (int c=0; c<l; ++c) {
                ps.print(matrix.get(c).get(r));
                if (c+1 < l) ps.print(" ");
            }
            if (r+1 < l) ps.print(" | ");
        }
        ps.println();
    }
}