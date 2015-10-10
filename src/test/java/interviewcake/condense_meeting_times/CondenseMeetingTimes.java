package interviewcake.condense_meeting_times;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mullermw on 8/13/15.
 */
public class CondenseMeetingTimes {

    public static class Pair implements Comparable<Pair> {

        private final int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public static Pair of(int first, int second) {
            return new Pair(first, second);
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.first, o.first);
        }

        public String toString() {
            return String.format(("%s,%s"), first, second);
        }
    }

    public List<Pair> condenseMeetingTimes(List<Pair> meetingTimes) {

        if (meetingTimes.size() < 2) return meetingTimes;

        Collections.sort(meetingTimes);

        Iterator<Pair> it = meetingTimes.iterator();
        Pair prevMeeting = it.next();
        int prevMeetingStart = prevMeeting.first;
        int prevMeetingEnd = prevMeeting.second;
        List<Pair> list = new ArrayList<>();
        while (it.hasNext()) {
            Pair nextMeeting = it.next();
            if (nextMeeting.first <= prevMeeting.second) {
                prevMeetingEnd = Math.max(prevMeetingEnd, nextMeeting.second);
            } else {
                list.add(Pair.of(prevMeetingStart, prevMeetingEnd));
                prevMeetingStart = nextMeeting.first;
                prevMeetingEnd = nextMeeting.second;
            }
        }
        list.add(Pair.of(prevMeetingStart, prevMeetingEnd));
        return list;
    }

    @Test
    public void test() {
        List<Pair> meetingTimes = Arrays.asList(
            Pair.of(1,5),
            Pair.of(1,2),
            Pair.of(1,1),
            Pair.of(6,10)
        );

        System.out.println(condenseMeetingTimes(meetingTimes));
    }
}
