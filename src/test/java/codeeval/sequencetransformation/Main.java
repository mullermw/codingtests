package codeeval.sequencetransformation;

import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            if (line.startsWith("#")) continue;
            line = line.trim();
            String[] fields = line.split("\\s+");
            boolean[] bits = parseBooleans(fields[0], '1');
            boolean[] letters = parseBooleans(fields[1], 'B');
            System.out.println(dp(bits, letters) ? "Yes" : "No");
        }
    }

    private static boolean dp(boolean[] pattern, boolean[] text) {

        //State[i,j] =
        //1. (pattern[i] == '0' and text[j] == 'A') or pattern[i] == '1'   // if State[i-1,j-1] = true
        //2. State[i,j-1] && text[j-1] == text[j],                         // otherwise

        //For Dynamic programming, we need only use two slices of the dp matrix.
        //each slice is associated with a single position j in text
        //slice[i] is true if the pattern up to i matches the text up to position j
        boolean[] prevDp = {compatible(pattern[0], text[0])};//slice for j-1 in text

        if (!prevDp[0]) return false;

        boolean[] dp;//slice for current position j in text

        //Main LOOP
        //Consider each position j in text,
        //for each position in the pattern, determine if it can match the text
        //at position j

        //Optimization:  update prevFirstMatch and prevLastMatch to bound the iterations
        //over pattern
        int prevFirstMatch=0; //index of the first pattern match for the previous j in text
        int prevLastMatch=0; //index of the last pattern match for the previous j in text

        //Optimization: Variables moved out of loops.
        int to;
        boolean match, foundMatch;

        for (int j=1; j<text.length; ++j) {

            dp = new boolean[pattern.length]; //further optimization:  use a smaller array.
            foundMatch = false;
            to = Math.min(prevLastMatch+2, pattern.length);

            for (int i=prevFirstMatch; i<to; ++i) {
                if (i != 0 && prevDp[i-1]) {
                    match = compatible(pattern[i],text[j]);
                } else {
                    match = prevDp[i] && text[j-1] == text[j];
                }
                if (match) {//found a match, adjust i ranges for next j
                    dp[i] = match;

                    //Optimization: update prevFirstMatch and prevLastMatch to
                    //limit the range of i
                    if(!foundMatch) {//it's the first match for j
                        foundMatch = true;
                        prevFirstMatch = i;
                    }
                    prevLastMatch= i;
                }
            }
            prevDp = dp;
        }
        return prevDp[pattern.length-1];

    }

    private static boolean compatible(boolean bit, boolean letter) {
        return bit || !letter;
    }

    private static boolean[] parseBooleans(String string, char trueChar) {
        boolean[] booleans = new boolean[string.length()];
        for (int i=0; i<booleans.length; ++i) {
            booleans[i] = string.charAt(i) == trueChar;
        }
        return booleans;

    }
}