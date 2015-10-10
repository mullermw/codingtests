package interviewcake.stock_prices_yesterday;

import org.junit.Test;

/**
 * Created by mullermw on 8/12/15.
 */
public class StockPricesYesterday {


    public int maxProfit(int[] stock_prices_yesterday) {

        if (stock_prices_yesterday.length < 2) return 0;

        int bestBuy = stock_prices_yesterday[0];
        int bestSell = stock_prices_yesterday[1];
        int lowest = Math.min(bestBuy, bestSell);

        for (int i=2; i<stock_prices_yesterday.length; ++i) {
            int next = stock_prices_yesterday[i];
            if (next < lowest) {
                lowest = next;
            } else if (next - lowest > bestSell - bestBuy) {
                bestBuy = lowest;
                bestSell = next;

            }
        }

        return bestSell - bestBuy;

    }

    @Test
    public void test0() {

    }


}
