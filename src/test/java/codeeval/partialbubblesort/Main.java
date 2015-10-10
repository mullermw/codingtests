package codeeval.partialbubblesort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            List<String> fields = Arrays.asList(line.split("\\s+"));
            List<Integer> numbers = parse(fields.subList(0, fields.size() - 2));
            if (!"|".equals(fields.get(fields.size() - 2))) {
                throw new IllegalArgumentException();
            }
            long num = Long.parseLong(fields.get(fields.size() - 1));
            System.out.println(format(bubble(numbers, num)));
        }
    }

    private static List<Integer> bubble(List<Integer> numbers, long num) {
        List<Integer> list = new ArrayList<>(numbers);
        for (long i=0; i<num; ++i) {
            boolean swapped = false;
            for (int j=0; j<list.size()-1; ++j) {
                int i0 = list.get(j);
                int i1 = list.get(j+1);
                if (i0 > i1) {
                    list.set(j, i1);
                    list.set(j+1, i0);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return list;
    }

    private static List<Integer> parse(List<String> in) {
        List<Integer> list = new ArrayList<>();
        for (String s : in) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    private static String format(List<Integer> list) {
        StringBuilder b = new StringBuilder();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            b.append(it.next());
            if (it.hasNext()) b.append(" ");
        }
        return b.toString();
    }
}