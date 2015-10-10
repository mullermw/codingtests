package codeeval.reversewords;

import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] split = line.split("\\s+");
            for (int i=split.length-1; i>=0; --i) {
                System.out.print(split[i]);
                if (i > 0) System.out.print(" ");
            }
            System.out.println();
        }
    }
}