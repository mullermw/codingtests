import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mullermw on 8/14/15.
 */
public class amount {

    public int numWays(int amount, List<Integer> denominations) {
        List<Integer> list = newList(amount+1, 0);
        list.set(0, 1);
        for (int d : denominations) {
            for (int a=d; a<=amount; ++a) {
                list.set(a, list.get(a) + list.get(a - d));
            }
            System.out.println(d + " " + list);
        }
        return list.get(amount);
    }

    @Test
    public void test() {
        System.out.println(numWays(4, Arrays.asList(2,3,1)));
    }

    private static <T> List<T> newList(int size, T value) {
        List<T> list = new ArrayList<>();
        for (int i=0; i<size; ++i) {
            list.add(value);
        }
        return list;
    }
}
