package hackerrank.compress;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

/*
 * Complete the function below.
 */

    static String compress(String str) {
        if (str.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        char currentChar = str.charAt(0);
        int count = 1;
        for (int i=1; i<str.length(); ++i) {
            char c = str.charAt(i);
            if (c == currentChar) {
                ++count;
            } else {
                sb.append(currentChar);
                if (count > 1) sb.append(count);
                currentChar = c;
                count = 1;
            }
        }
        sb.append(currentChar);
        if (count > 1) sb.append(count);
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println(compress("aaaaabbbbbbbbbccccpqrstuv"));
    }
}

