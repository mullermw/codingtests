package codeeval.sequencetransformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainCopy {
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
            System.out.println(canTransform(bits, letters) ? "Yes" : "No");
        }
    }

    private static boolean canTransform(boolean[] bits, boolean[] letters) {
        //return recurseOrig(bits, letters, 0, 0);
        return dp(bits, letters);
    }

    private static boolean dp(boolean[] pattern, boolean[] text) {

        //State[i,j] =
        //1. (pattern[i] == '0' and text[j] == 'A') or pattern[i] == '1'   // if State[i-1,j-1] = true
        //2. State[i,j-1] && text[j-1] == text[j],                         // otherwise

        boolean[] prevDp = new boolean[pattern.length];
        boolean[] dp;

        prevDp[0] = compatible(pattern[0], text[0]);
        if (!prevDp[0]) return false;

        //used to trim the inner loop
        int firstMatch=0;
        int lastMatch=0;
        int to;
        boolean match, foundMatch;
        for (int j=1; j<text.length; ++j) {

            dp = new boolean[pattern.length];
            foundMatch = false;
            to = Math.min(lastMatch+2, pattern.length);

            for (int i=firstMatch; i<to; ++i) {
                if (i != 0 && prevDp[i-1]) {
                    match = compatible(pattern[i],text[j]);
                } else {
                    match = prevDp[i] && text[j-1] == text[j];
                }
                if (match) {//found a match
                    dp[i] = match;
                    if(!foundMatch) {//it's the first match for j
                        foundMatch = true;
                        firstMatch = i;
                    }
                    lastMatch= i;
                }
            }
            prevDp = dp;
        }
        return prevDp[pattern.length-1];

    }

    private static boolean dp2(boolean[] pattern, boolean[] text) {

        //State[i,j] =
        //1. (pattern[i] == '0' and text[j] == 'A') or pattern[i] == '1'   // if State[i-1,j-1] = true
        //2. State[i-1,j] && text[j-1] == text[j],                         // otherwise
        boolean[][] dp = new boolean[pattern.length][text.length];
        dp[0][0] = compatible(pattern[0], text[0]);
        if (!dp[0][0]) return false;
        int firstMatch=0;
        int prevLastMatch=0;
        //print(pattern, text, dp);
        for (int j=1; j<text.length; ++j) {
            boolean foundMatchInText = false;
            int to = Math.min(prevLastMatch + 2, pattern.length);
            //System.out.println(j + " " + firstMatch + " "+ to);
            for (int i=firstMatch; i<to; ++i) {
                if (i != 0 && dp[i-1][j-1]) {
                    dp[i][j] = compatible(pattern[i],text[j]);
                } else {
                    dp[i][j] = dp[i][j-1] && text[j-1] == text[j];
                }
                if (dp[i][j]) {//found a match
                    if(!foundMatchInText) {//it's the first match for this text
                        foundMatchInText = true;
                        firstMatch = i;
                    }
                    prevLastMatch= i;
                }
            }
        }
        //print(pattern, text, dp);
        return dp[pattern.length-1][text.length-1];

    }

    private static boolean dp1(boolean[] pattern, boolean[] text) {

        //State[i,j] =
        //1. (pattern[i] == '0' and text[j] == 'A') or pattern[i] == '1'   // if State[i-1,j-1] = true
        //2. State[i-1,j] && text[j-1] == text[j],                         // otherwise
        boolean[][] dp = new boolean[pattern.length][text.length];
        dp[0][0] = compatible(pattern[0], text[0]);
        for (int j=1; j<text.length; ++j) {
            dp[0][j] = dp[0][j-1] && text[j-1] == text[j];
            for (int i=1; i<pattern.length; ++i) {
                if (dp[i-1][j-1]) {
                    dp[i][j] = compatible(pattern[i],text[j]);
                } else {
                    dp[i][j] = dp[i][j-1] && text[j-1] == text[j];
                }
            }
        }
        print(pattern, text, dp);
        return dp[pattern.length-1][text.length-1];

    }

    private static void print(boolean[] pattern, boolean[] text, boolean[][] dp) {

        System.out.print(" ");
        for (boolean t : text) {
            System.out.print(" " + (t ? "B" : "A"));
        }
        System.out.println();

        for (int p=0; p<pattern.length; ++p) {
            System.out.print((pattern[p] ? "1" : "0"));
            for (int t=0; t<text.length; ++t) {
                System.out.print(" " + (dp[p][t] ? "1" : "0"));
            }
            System.out.println();
        }
    }

    private static boolean recurse(boolean[] bits, boolean[] letters, int bitPos, int letterPos) {
        //System.out.println(bitPos + " " + letterPos);
        if (!compatible(bits[bitPos], letters[letterPos])) return false;

        if (letterPos == letters.length - 1)
            return bitPos == bits.length - 1; //Last letter reached.

        if (bitPos < bits.length - 1) {
            //switch to the next bitPos
            boolean b = recurse(bits, letters, bitPos + 1, letterPos + 1);
            if (b) return true;
        }

        if (letters[letterPos] == letters[letterPos+1]) {//A->A OK, A->B NO!
            //Try to consume one more letter in bitPos
            boolean b = recurse(bits, letters, bitPos, letterPos + 1);
            if (b) return true;
        }

        return false;

    }

    private static boolean recurseOrig(boolean[] bits, boolean[] letters, int bitPos, int letterPos) {

        if (!compatible(bits[bitPos], letters[letterPos])) return false;

        if (letterPos == letters.length - 1)
            return bitPos == bits.length - 1; //Last letter reached.

        if (letters[letterPos] == letters[letterPos+1]) {//A->A OK, A->B NO!
            //Try to consume one more letter in bitPos
            boolean b = recurseOrig(bits, letters, bitPos, letterPos + 1);
            if (b) return true;
        }

        if (bitPos < bits.length - 1) {
            //switch to the next bitPos
            return recurseOrig(bits, letters, bitPos + 1, letterPos + 1);
        } else {
            return false;
        }

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