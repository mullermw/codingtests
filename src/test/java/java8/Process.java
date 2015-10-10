package java8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by mullermw on 8/18/15.
 */
public class Process {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://norvig.com/big.txt");
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        ) {

            Map<Object, Long> words = br.lines().map(line -> line.split("\\W"))
                    .flatMap(Arrays::stream).map(s -> s.toLowerCase())
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            System.out.println(words);
        }
    }
}
