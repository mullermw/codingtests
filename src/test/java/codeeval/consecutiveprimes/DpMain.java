package codeeval.consecutiveprimes;

import java.io.*;
public class DpMain {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            int number = Integer.parseInt(line);
            System.out.println(solve(number));
        }
    }

    public static final int solve(int n) {
        boolean[][] isPrime = new boolean[n+1][n+1];
        for (int i=0; i<=n; ++i) {
            for (int j=0; j<=n; ++j) {
                isPrime[i][j] = isPrime(i+j);
            }
        }
        int[][] dp = new int[(int)Math.pow(2,n)][n];//one bit for each of the values 1..n
        dp[1][0] = 1;
        //the bit at position n in mask indicates whether number n is part of the considered
        //sequence
        for (int mask=0; mask<dp.length; ++mask) {
            //System.out.println("MASK:"+mask + " " + Integer.toBinaryString(mask));
            //consider each subsequence that::
            //000001 : Use only '1'
            //000010 : Use only '2'
            //000011 : Use only '1' & '2'
            //000100 : Use only '3'
            //....
            for (int last=1; last <=n; ++last) {
                for (int cur=1; cur <=n; ++cur) {//consider all transitions from last to cur

                    //for the sequences containing mask, ending in last but not
                    //containing cur
                    //if last + cur is prime, add this count to the count for
                    //the sequences containing mask+cur, ending in cur

                    //if last+cur is prime, (a valid transition)
                    //and last is part of the current subsequence (mask)
                    //and cur is not part of the current subsequence (mask)
                    //we add the count or this subsequence (mask)
                    //ending with last to the count for the subsequence that includes
                    //cur

                    if (isPrime[last][cur] && contains(mask, last) && !contains(mask, cur)) {
                        int count = dp[mask][last-1];
                        dp[add(mask,cur)][cur-1] += count;
//                        if (count != 0) {
//                            System.out.println("last=" + last + ",cur="
//                                    + cur + ",dp[mask][" + last + "]=" + dp[mask][last]
//                                    + ",dp[mask]["+cur+"]=" + dp[add(mask, cur)][cur]);
//                        }
                    }
                }
            }
        }
        int sum = 0;
        int[] dpLastMask = dp[dp.length-1];
        for (int i=0; i<dpLastMask.length; ++i) {
            if (isPrime[1][i+1]) {
//                System.out.println("FOUND: " + i + " " + dpLastMask[i]);
                sum += dpLastMask[i];
            }
        }
        return sum;
    }

    private static boolean isPrime(int n) {
        if (n == 0) return false;
        if (n < 3) return true;
        for (int i=2; i<=Math.sqrt(n); ++i) {
            if (n % i == 0) return false;
        }
        return true;
    }

    //for using an int as a set of values between 1 and 32.
    //int i contains n if the (n-1)th bit == 1
    private static boolean contains(int collection, int value) {
        return ((collection >> (value-1)) & 1) == 1;
    }

    private static int add(int collection, int value) {
        return collection | (1 << (value - 1));
    }
}