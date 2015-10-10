package codeeval.luckyticket;

import java.io.*;
import java.math.BigDecimal;

public class Main {

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            int num = Integer.parseInt(line);
            System.out.println(luckyTickets(num));
        }
    }

    private static BigDecimal luckyTickets(int num) {
        BigDecimal[] prev = {new BigDecimal(1)};
        for (int n=1; n<=num/2; n++) {
            BigDecimal[] arr = new BigDecimal[1+9*n];
            for (int s=0; s<arr.length; ++s) {
                arr[s] = sum(prev, s-9, s+1);
            }
            prev = arr;
        }
        return sumSquares(prev);
    }

    private static BigDecimal sum(BigDecimal[] arr, int from, int to) {
        BigDecimal sum = new BigDecimal(0);
        for (int i=Math.max(0, from); i<to && i<arr.length; ++i) {
            sum = sum.add(arr[i]);
        }
        return sum;
    }

    private static BigDecimal sumSquares(BigDecimal[] arr ) {
        BigDecimal sum = new BigDecimal(0);
        for (int i=0; i<arr.length; ++i) {
            sum = sum.add(arr[i].pow(2));
        }
        return sum;
    }
}