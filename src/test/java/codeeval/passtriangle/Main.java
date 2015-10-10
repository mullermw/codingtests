package codeeval.passtriangle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        List<Integer> list = new ArrayList<>();
        int lastRow = -1;
        while ((line = buffer.readLine()) != null) {
            ++lastRow;
            line = line.trim();
            for (String s : line.split("\\s+")) {
                list.add(Integer.parseInt(s));
            }
        }
        System.out.println(maxPathSum(toIntArray(list)));
    }

    public static int maxPathSum(int[] arr) {
        if (arr.length == 0) return 0;

        int lastRowIndex = (int)(Math.sqrt(2*arr.length + 0.25) - 0.05) - 1;
        if (arr.length != ((lastRowIndex+1) * (lastRowIndex + 2) / 2))
            throw new IllegalArgumentException("Not a triangular array");

        int row = lastRowIndex;
        int rowStart = arr.length - lastRowIndex - 1;
        int[] sums = new int[row+1];
        System.arraycopy(arr, rowStart, sums, 0, row+1);
        for (; row > 0; row--) {
            rowStart -= row;
            for (int i=0; i<row; ++i) {
                sums[i] = arr[rowStart+i] + Math.max(sums[i], sums[i+1]);
            }
        }
        return sums[0];
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i=0; i<arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
