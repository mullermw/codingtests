package codeeval.firstnonrepeatedchar;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            Character firstNonRepeatedChar = firstNonRepeatedChar(line);
            System.out.println(firstNonRepeatedChar == null ? "" : firstNonRepeatedChar);
        }
    }

    public static Character firstNonRepeatedChar(String line) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char c : line.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }
}