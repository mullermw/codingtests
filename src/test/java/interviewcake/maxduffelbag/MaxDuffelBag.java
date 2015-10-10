package interviewcake.maxduffelbag;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;

/**
 * Created by mullermw on 9/12/15.
 */
public class MaxDuffelBag {

    public static void main(String[] args) {
        int[][] cakes = {{7,160},{3,90},{2,15}};
        int capacity = 20;

        System.out.println(maxDuffelBagValue(cakes, capacity));
    }

    public static int maxDuffelBagValue(int[][] cakes, int capacity) {

        if (cakes.length == 0) return 0;
        if (capacity < 0) throw new IllegalArgumentException();

        for (int i=0; i<cakes.length; ++i) {
            if (cakes[i][0] < 0) throw new IllegalArgumentException();
            if (cakes[i][1] < 0) throw new IllegalArgumentException();
            if (cakes[i][0] == 0) return -1; //bag can hold an infinite number of weightless cakes
        }

        if (capacity == 0) return 0; //bag can't hold any non-weightless cakes.

        int[] dp = new int[capacity+1]; //dynamic programming
        for (int w=1; w<dp.length; ++w) { //consider every weight up to capacity
            for (int c=0; c<cakes.length; ++c) { //consider every cake
                int remain = w - cakes[c][0]; //how remaining capacity for a bag with capacity w holding this cake.
                if (remain >= 0) { //the cake fits in the bag
                    //value using this cake is the cake's value + the optimal value
                    // of the remaining capacity.  set dp[w] to the highest cake value found.
                    dp[w] = max(dp[w], cakes[c][1] + dp[remain]);
                }
            }
        }
        return dp[dp.length-1];
    }
}
