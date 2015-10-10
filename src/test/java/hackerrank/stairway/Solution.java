package hackerrank.stairway;

import java.io.InputStream;
import java.util.Scanner;

public class Solution {
    public static void main(String args[] ) throws Exception {
        run(System.in);
    }

    public static void run(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int n = scanner.nextInt();
        char[] arr = new char[n];
        for (int i=n-1; i>-1; --i) {
            arr[i] = '#';
            System.out.println(arr);
        }
    }
}

