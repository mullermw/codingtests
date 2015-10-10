package codeeval.stringmask;

import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] fields = line.split("\\s+");
            String word = fields[0];
            String bits = fields[1];
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<word.length(); ++i) {
                char c = word.charAt(i);
                if (bits.charAt(i) != '0') {
                    c = Character.toUpperCase(c);
                }
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}