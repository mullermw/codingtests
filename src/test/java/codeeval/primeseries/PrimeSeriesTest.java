package codeeval.primeseries;

import org.junit.Assert;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by mullermw on 8/15/15.
 */
public class PrimeSeriesTest {

    @Test
    public void test() {
        int count = 0;
        SortedMap<Integer, Integer> expectations = new TreeMap<>();
        expectations.put(1, 2);
        expectations.put(2, 3);
        expectations.put(3, 5);
        expectations.put(1000000, 15485863);
        expectations.put(2000000, 32452843);
        for (int i : PrimeSeries.fromTwo()) {
            ++count;
            if (expectations.containsKey(count)) {
                int expected = expectations.get(count);
                Assert.assertEquals(expected, i);
                System.err.println(count + " " + expected + " Ok!");
            }
            if (count > expectations.lastKey()) {
                break;
            }
        }
    }

    @Test
    public void test1() {
        int count = 0;
        PrimeSeries primeSeries = new PrimeSeries(Integer.MAX_VALUE-100);
        for (int i : primeSeries) {
            ++count;
            //if (count % 10 == 0) {
                System.out.println(count + ":" + i);
            //}
        }
    }
}
