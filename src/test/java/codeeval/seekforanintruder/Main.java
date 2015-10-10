package codeeval.seekforanintruder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
//Valid IP addresses:
//        1000000111011100010000001100101
//        01000000.11101110.00100000.01100101
//        034062405073
//        023244514100
//        033642264316
//        222.137.104.206
//        10110010011001001101100010111010
//        0131.0345.0202.0341
//        0x59.0xe5.0x82.0xe1
//        89.229.130.225
//        1011001111001011000001011100001
//        89.229.130.225
public class Main {

    static class Extractor {
        Pattern pattern;
        boolean dotted;
        int radix;

        private static long MINIP = 0x01000000L; //1.0.0.0
        private static long MAXIP = 0xFFFFFFFEL; //255.255.255.254
        private static Pattern binaryIpPattern = Pattern.compile("[01]{25,}");
        private static int MIN_BINARY_LENGTH = Long.toString(MINIP, 2).length();
        private static int MIN_DECIMAL_LENGTH = Long.toString(MINIP, 10).length();

        Extractor(String pattern, boolean dotted, int radix) {
            this.pattern = Pattern.compile(pattern);
            this.dotted = dotted;
            this.radix = radix;
        }

        public List<Long> extractIps(String string) {
            List<Long> ips = new ArrayList<>();
            Matcher m = pattern.matcher(string);
            int f=0;
            while (m.find(f)) {
                String s = m.group();
                long i = parse(s);
                System.err.println(s +"->"+i+" ");
                if (inRange(i)) {
                    ips.add(i);
                }
                f = m.end();
            }
            return ips;
        }

        public long parse(String string) {
            long value;
            if (dotted) {
                value = 0;
                String[] fields = string.split("\\.");
                for (int i=0; i<fields.length; ++i) {
                    String f = radix == 16 && fields[i].startsWith("0x") ? fields[i].substring(2) : fields[i];
                    long p = Long.parseLong(f, radix);
                    value |= p << (8*i);
                }
            } else {
                value = Long.parseLong(string, radix);
            }
            return value;
        }

        public boolean inRange(long ip) {
            return ip >= MINIP && ip <= MAXIP;
        };
    }

    static List<Extractor> extractors = Arrays.asList(
        new Extractor("(?:[01]{8}\\.){3}[01]{8}", true, 2),//binary
        new Extractor("(?:0(?:37[0-7]|3[0-6][0-9]|[012][0-9][0-9])\\.){3}0(?:37[0-7]|3[0-6][0-9]|[012][0-9][0-9])", true, 8), //octal
        new Extractor("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", true, 10), //decimal
        new Extractor("(?:0x[0-9a-f]{2}\\.){3}0x[0-9a-f]{2}", true, 16), //hex
        new Extractor("(?<![0-9]\\.?)[01]{25,32}(?!\\.?[0-9])", false, 2), //binary
        new Extractor("(?<![0-9]\\.?)0[0-7]{9,11}(?!\\.?[0-9])", false, 8), //octal
        new Extractor("(?<![0-9]\\.?)[1-9][0-9]{7,9}(?!\\.?[0-9])", false, 10) //decimal
    );

    public static void main (String[] args) throws IOException {
        System.out.println(Long.toString(Extractor.MINIP, 8));
        System.out.println(Long.toString(Extractor.MAXIP, 8));
        System.out.println(Long.toString(Extractor.MINIP, 10));
        System.out.println(Long.toString(Extractor.MAXIP, 10));
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        Map<Long, Integer> ipAddressCounts  = new HashMap<>();
        List<Long> maxIps = new ArrayList<>();
        int maxCount = 0;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            for (Extractor e : extractors) {
                for (long ipAddress : e.extractIps(line)) {
                    int count;
                    if (ipAddressCounts.containsKey(ipAddress)) {
                        count = ipAddressCounts.get(ipAddress) + 1;
                    } else {
                        count = 1;
                    }
                    ipAddressCounts.put(ipAddress, count);
                    if (count == maxCount) {
                        maxIps.add(ipAddress);
                    } else if (count > maxCount) {
                        maxCount = count;
                        maxIps.clear();
                        maxIps.add(ipAddress);
                    }
                }
            }
        }
        for (int i=0; i<maxIps.size(); ++i) {
            System.out.print(formatIp(maxIps.get(i)));
            if (i < maxIps.size()-1) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

//    Dotted decimal	192.0.2.235 with no leading zero.
//    Dotted hexadecimal 0xc0.0x0.0x02.0xeb Each octet is individually converted to hexadecimal form.
//    Dotted octal 0300.0000.0002.0353 Each octet is individually converted into octal.
//    Dotted binary 11000000.00000000.00000010.11101011 Each octet is individually converted into binary.
//    Binary 11000000000000000000001011101011
//    Octal 030000001353
//    Hexadecimal	0xC00002EB	Concatenation of the octets from the dotted hexadecimal.
//    Decimal	3221226219	The 32-bit number expressed in decimal.
//    1.0.0.0 00000001000000000000000000000000

    private static String formatIp(long ip) {
        return String.format("%s", ip);
    }
}