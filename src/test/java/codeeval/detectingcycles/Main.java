package codeeval.detectingcycles;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] fields = line.split("\\s+");
            int[] numbers = parseInts(fields);
            System.out.println(format(detectCycles(numbers)));
        }
    }

    private static int[] detectCycles(int[] data) {

        HashMap<Integer, Integer> indexes = new HashMap<>(); //key = number, value = first position
        int n=0; //will be second occurence of repeat
        int p=-1; //will be first occurence of repeat
        while (n<data.length && p == -1) {
            Integer prev = indexes.put(data[n], n);
            if (prev != null) {
                p = prev;
                break;
            }
            ++n;
        }

        if (p == -1) { //no cycles found
            return new int[] {};
        }

        int c = 0; //determine the length of the repeat
        while (p+c < n && data[n+c] == data[p+c]) {
            ++c;
        }

        //create the return value.
        int[] ret = new int[c];
        for (int i=0; i<c; ++i) {
            ret[i] = data[p+i];
        }
        return ret;
    }

    private static int[] parseInts(String[] strings) {
        int[] ints = new int[strings.length];
        for (int i=0; i<strings.length; ++i) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    private static String format(int[] ints) {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<ints.length; ++i) {
            b.append(ints[i]);
            if (i < ints.length-1)
                b.append(" ");
        }
        return b.toString();
    }
}