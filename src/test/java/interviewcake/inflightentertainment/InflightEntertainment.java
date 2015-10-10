package interviewcake.inflightentertainment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

/**
 * Created by mullermw on 9/12/15.
 */
public class InflightEntertainment {

    public static void main(String[] args) {

        System.out.println(hasMovieLength2(200, new int[]{50, 70, 90, 100, 120, 180}));
    }

    public static boolean hasMovieLength(int length, int[] times) {

        sort(times);
        int sf=0;
        int st=times.length;

        for (int i=0; i<st; ++i) {
            int time = times[i];
            if (time > length) return false;
            int s = binarySearch(times, i, st, length-time);
            if (s > -1) return true; //found it
            st = -(s+1);
        }
        return false;
    }

    public static boolean hasMovieLength2(int length, int[] times) {
        final HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<times.length; ++i) {
            int time = times[i];
            set.add(time);
            if (set.contains(length - time)) {
                return true;
            }
        }
        return false;
    }
}
