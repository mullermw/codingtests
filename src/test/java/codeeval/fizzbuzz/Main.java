package codeeval.fizzbuzz;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] fields = line.split("\\s+");
            int x = Integer.parseInt(fields[0]);
            int y = Integer.parseInt(fields[1]);
            int n = Integer.parseInt(fields[2]);
            System.out.println(format(fizzBuzz(x, y, n)));
        }
    }

    public static List<String> fizzBuzz(int x, int y, int n) {
        List<String> list = new ArrayList<>();
        for (int i=1; i<=n; ++i) {
            String s;
            if (i % x == 0) {
                if (i % y == 0) {
                    s = "FB";
                } else {
                    s = "F";
                }
            } else if (i % y == 0) {
                s = "B";
            } else {
                s = Integer.toString(i);
            }
            list.add(s);
        }
        return list;
    }

    public static String format(List<String> list) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            builder.append(it.next());
            if (it.hasNext()) builder.append(" ");
        }
        return builder.toString();
    }
}