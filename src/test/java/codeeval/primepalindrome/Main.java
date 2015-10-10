package codeeval.primepalindrome;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {

        Integer i = nextLowerPrime(1000);
        while (i != -1) {
            if (isPalindrome(i)) {
                break;
            }
            i = nextLowerPrime(i);
        }
        System.out.println(i);
    }

    private static int nextLowerPrime(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int i = n % 2 == 0 ? n - 1 : n - 2; //next lower odd number
        while (i > 1) {
            boolean isPrime = true;
            for (int j = 2; isPrime && j * j <= i; ++j) {
                if (i % j == 0) isPrime = false;
            }
            if (isPrime) return i;
            i -= 2;
        }
        return -1;
    }

    private static boolean isPalindrome(int n) {
        String s = Integer.toString(n);
        for (int i = 0; i < s.length() / 2; ++i) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(7919, nextLowerPrime(7920));
        Assert.assertEquals(7907, nextLowerPrime(7919));
        Assert.assertEquals(7907, nextLowerPrime(7918));
        Assert.assertEquals(3221, nextLowerPrime(3227));
        Assert.assertEquals(2, nextLowerPrime(3));
        Assert.assertEquals(3, nextLowerPrime(4));
        Assert.assertEquals(3, nextLowerPrime(5));
        Assert.assertEquals(5, nextLowerPrime(6));
    }

    @Test
    public void test2() throws Exception {
        SortedSet<Integer> primes = new TreeSet<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("primes")));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String s : line.split("\\s+")) {
                    if (!s.isEmpty())
                        primes.add(Integer.parseInt(s));
                }
            }
        }
        for (int i = 3; i < 7922; ++i) {
            int exp = (int) primes.headSet(i).last();
            int actual = nextLowerPrime(i);
            //System.out.println(i + " " + exp + " " + actual);
            Assert.assertEquals(exp, actual);
        }
    }
}
