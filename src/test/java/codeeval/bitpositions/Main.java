package codeeval.bitpositions;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            int[] fields = parseInts(line.split(","));
            System.out.println(sameBit(fields[0], fields[1], fields[2]));
        }
    }

    private static boolean sameBit(int number, int bit0, int bit1) {
        if (bit0 > 32) throw new IllegalArgumentException();
        if (bit0 > 32) throw new IllegalArgumentException();
        return ((number >> (bit0-1)) & 1) == ((number >> (bit1-1)) & 1);
    }

    private static int[] parseInts(String[] strings) {
        int[] ints = new int[strings.length];
        for (int i=0; i<strings.length; ++i) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    //private static int[] parseIntsStream(String[] strings) {
    //    return Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();
    //}
}