package codeeval.happynumber;

import java.io.*;
import java.util.TreeSet;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            int i = Integer.parseInt(line);
            System.out.println(isHappy(i) ? 1 : 0);
        }
    }

    private static boolean isHappy(int num) {
        long n = num;
        TreeSet<Long> seen = new TreeSet<>();
        seen.add(n);
        while (true) {
            long ssq = sumSquares(toDigits(n));
            if (ssq == 1) return true;
            if (seen.contains(ssq)) return false;
            seen.add(ssq);
            n = ssq;
        }
    }

    private static int[] toDigits(long num) {
        char[] s = Long.toString(num).toCharArray();
        int[] digits = new int[s.length];
        for (int i=0; i<digits.length; ++i) {
            digits[i] = s[i] - '0';
        }
        return digits;
    }

    private static long sumSquares(int[] ints) {
        long sum = 0;
        for (int i=0; i<ints.length; ++i) {
            sum += ints[i] * ints[i];
        }
        return sum;
    }
}