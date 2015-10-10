package hackerrank.matrix;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i=0; i<n; ++i) {
            for (int j=0; j<n; ++j) {
                if (j == i)
                    sum += scanner.nextInt();
                else if (j == n-i-1)
                    sum -= scanner.nextInt();
                else
                    scanner.nextInt();
            }
        }
        System.out.println(sum);

    }
}