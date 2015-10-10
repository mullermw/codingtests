package codeeval.sumofprimes;

import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        int sum = 2;
        for (int i=3, remaining = 1000-1; remaining > 0; i+=2) { //candidate odd numbers 3...Inf

            //try disprove isPrime
            boolean isPrime = true;
            for (int j=3; j*j<=i; j+=2) { //look for factors
                if (i % j == 0) {
                    isPrime = false; //factor found, not prime
                    break;
                }
            }

            if (isPrime) { //no factors found, add and countdown
                sum += i;
                --remaining;
            }
        }
        System.out.println(sum);
    }
}