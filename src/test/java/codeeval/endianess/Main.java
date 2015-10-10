package codeeval.endianess;

import java.io.*;
import java.nio.ByteOrder;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
        System.out.println(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
    }
}