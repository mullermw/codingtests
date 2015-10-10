package interviewcake.temptracker;

/**
 * Created by mullermw on 9/3/15.
 */
public class TempTracker {

    int maxTemp = 110;
    long[] tempCounts = new long[maxTemp+1];
    int min = 0, max = 0, mode = 0;
    long n = 0;
    double mean = 0;

    public void insert(int temp) {
        if (temp < 0 || temp > maxTemp) throw new IllegalArgumentException("Out of range");
        if (n == Long.MAX_VALUE) throw new IllegalStateException("Size Limit Reached");
        if (n == 0) {
            min = max = temp;
        } else {
            if (temp < min) min = temp;
            if (temp > max) max = temp;
        }
        ++n;
        ++tempCounts[temp];
        mean = (mean * (n-1) + temp) / n;
        //mean = (mean*n - mean + temp) / n;
        mean = mean - (mean/n) + (double)temp/n; //avoid overflow

        if (temp != mode && tempCounts[temp] > tempCounts[mode]) {
            mode = (byte)temp;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getMean() {
        return mean;
    }

    public int getMode() {
        return mode;
    }

    public String toString() {
        return new StringBuilder().append("TempTracker:").
        append("min=").append(min).append(",max=").append(max).append(",mean=").append(mean).append(",mode=").append(mode).toString();

    }

    public static void main(String[] args) {
        TempTracker t = new TempTracker();
        t.insert(10);
        t.insert(11);
        t.insert(11);
        t.insert(12);
        System.out.println(t);
    }
}
