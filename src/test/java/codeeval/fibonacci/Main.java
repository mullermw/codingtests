package codeeval.fibonacci;

import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            System.out.println(fibiter(Integer.parseInt(line)));
        }
    }

//    private static long fibrecur(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        return fibrecur(n-1) + fibrecur(n-2);
//    }

    private static long fibiter(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) return n;
        int[] last2 = {0, 1};
        int j = 0;
        for (int i=2; i<=n; ++i) {
            j = i % 2 == 0 ? 0 : 1;
            last2[j] = last2[0] + last2[1];
        }
        return last2[j];
    }

}