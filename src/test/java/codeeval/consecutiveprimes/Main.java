package codeeval.consecutiveprimes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static long primes = 0b0010100000100000100010100010000010100000100010100010100010101100L;

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            int number = Integer.parseInt(line);
            long start = System.currentTimeMillis();
            int solution = solve(number);
            System.err.println("Took: " + (System.currentTimeMillis() - start));
            System.err.println(primes);
            System.out.println(solution);
        }
    }

    public static final int solve(int n) {
        int[] arr = new int[n];
        for (int i=0; i<n; ++i) {
            arr[i] = i+1;
        }
        return solve(arr, 1);
    }

    private static final int solve(int[] arr, int position) {
        //base case
        if (position == arr.length) {
            return isPrime(arr[arr.length - 1] + arr[0]) ? 1 : 0;
        }

        int previousNumber = arr[position - 1];
        int count = 0;
        if (isPrime(previousNumber + arr[position])) {
            count += solve(arr, position+1);
        }
        for (int i=position+2; i<arr.length; i+=2) {
            if (isPrime(previousNumber + arr[i])) {
                swap(arr, position, i);
                count += solve(arr, position+1);
                swap(arr, i, position);
            }
        }
        return count;
    }

    private static void swap (int[] arr, int p0, int p1) {
        int t = arr[p0];
        arr[p0] = arr[p1];
        arr[p1] = t;
    }

    private static boolean isPrime(int n) {
        return ((primes >> n) & 1) == 1;
    }

}