package interviewcake.rotationpoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Collections.sort;

/**
 * Created by mullermw on 9/4/15.
 */
public class RotationPoint {
    
    public static void main(String[] args) throws IOException {
        List<String> words = readWords("/usr/share/dict/words", 10000);
        int partition = 1;

        List<String> partitioned = new ArrayList<>();
        partitioned.addAll(words.subList(partition, words.size()));
        partitioned.addAll(words.subList(0, partition));
        int expectedRotation = words.size() - partition;

        int rotation = findRotation(partitioned);

        System.out.println(rotation == expectedRotation);
        System.out.println(rotation);
        System.out.println(partitioned.get(rotation));

    }

    public static int findRotation(List<String> words) {
        if (words.size() == 1 || //singleton
            words.get(0).compareTo(words.get(words.size()-1)) < 0 //not rotated
        ) {
            return 0;
        }
        int left = 0;
        int right = words.size();
        int i = (right + left) / 2;
        String firstWord = words.get(0);
        String ithWord = words.get(i);
        while (ithWord.compareTo(words.get(i - 1)) > 0) {
            System.out.println(left + " " + right + " " + i + " " + words.get(i));
            int cmp = ithWord.compareTo(firstWord);
            if (cmp > 0) {
                left = i;
            } else {
                right = i;
            }
            i = (right + left) / 2;
            ithWord = words.get(i);
        } ;
        return i;
    }

    private static List<String> readWords (String source, int num) throws IOException {
        Collection<String> words = new TreeSet<>();
        try (
            BufferedReader br = new BufferedReader(new FileReader(source));
        ) {
            while (words.size() < num)
                words.add(br.readLine().toLowerCase());
        }
        ArrayList<String> list  = new ArrayList(words);
        sort(list);

        return list;
    }
}
