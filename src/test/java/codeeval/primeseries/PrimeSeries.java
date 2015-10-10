package codeeval.primeseries;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mullermw on 8/15/15.
 */
public class PrimeSeries implements Iterable<Integer> {

    private static PrimeSeries fromTwo = new PrimeSeries();

    private int firstPrime;

    public PrimeSeries(int first) {
        firstPrime = nextPrime(first);
    }

    public PrimeSeries() {
        this(2);
    }

    public static PrimeSeries fromTwo() {
        return fromTwo;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            int next = firstPrime;

            @Override
            public boolean hasNext() {
                return next != -1;
            }

            @Override
            public Integer next() {
                if (next == -1) {
                    throw new NoSuchElementException();
                }
                int n = next;
                if (next == Integer.MAX_VALUE) {
                    next = -1;
                } else if (next == 2) {
                    next = 3;
                } else {
                    next = nextPrime(next+2);
                }
                return n;
            }
        };
    }

    //the next prime number greater than or equal to n
    private static int nextPrime (int n) {
        if (n <= 2) return 2;

        int i = n; //i is a candidate prime to be evaluated
        //i must always be odd
        if (i % 2 == 0) {
            ++i;
        }

        for (; i <= Integer.MAX_VALUE; i+= 2) {
            boolean isPrime = true;
            for (int j=3;
                 isPrime && (long)j*j <= i; //cast to long to avoid overflow
                 j+=2
            ) { //search for factors
                if (i % j == 0) {
                    isPrime = false; //factor found, i is not prime
                }
            }
            if (isPrime) {
                return i; //no factors found, i is prime
            }
        }
        throw new IllegalStateException();//should never happen.
    }

}
