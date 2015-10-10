package interviewcake.making_change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mullermw on 9/3/15.
 */
public class MakingChange {


    public static void main(String[] args) {

        int amt = 5;
        List<Integer> denominations = Arrays.asList(1,3,5);

        System.out.println(numWays(amt, denominations));

    }

    private static int numWays(int amt, List<Integer> denominations) {
        return numwaysDP(amt, denominations);
    }

    private static int numwaysRecursive(int amt, List<Integer> denominations) {
        System.out.println("numwaysRecursive("+amt+","+denominations+")");
        //baseCase
        if (amt == 0 || denominations.size() == 0) return 0;

        int highestDenomination = denominations.get(denominations.size() - 1);
        int numHighestDenomination = amt / highestDenomination;

        int numWays = amt % highestDenomination == 0 ? 1 : 0;

        List<Integer> allButHighestDenomination =
                denominations.subList(0, denominations.size()-1);
        for (int i=0; i<=numHighestDenomination; ++i) {
            numWays += numwaysRecursive(
                    amt - i * highestDenomination,
                    allButHighestDenomination
            );
        }

        return numWays;
    }

    private static int numwaysDP(int amt, List<Integer> denominations) {
        List<Integer> waysOfDoingNCents = newList(amt + 1, 0);
        waysOfDoingNCents.set(0, 1);

        for (int coin : denominations) {
            for (int higherAmt = coin; higherAmt < amt + 1; ++higherAmt) {
                waysOfDoingNCents.set(
                        higherAmt,
                        waysOfDoingNCents.get(higherAmt) + waysOfDoingNCents.get(higherAmt - coin)
                );
            }
            System.out.println(coin + " " + waysOfDoingNCents);
        }
        return waysOfDoingNCents.get(amt);
    }

    private static <T> List<T> newList(int size, T defaultValue) {
        List l = new ArrayList<>(size);
        for (int i=0; i<size; ++i) {
            l.add(defaultValue);
        }
        return l;
    }

}
