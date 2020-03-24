package com.erely.leetcode;

public class reverseBits {

    public static void main(String[] args) {
        System.out.println(f1(43261596));
    }

    public static int f1(int n) {
        int res = 0;
        int count = 0;
        while (count < 32) {
            res <<= 1;
            res |= (n & 1);
            n >>= 1;
            count++;
        }
        return res;
    }
}
