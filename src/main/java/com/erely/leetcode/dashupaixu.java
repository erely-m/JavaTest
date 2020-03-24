package com.erely.leetcode;

import java.util.Random;

public class dashupaixu {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        byte[] b = new byte[100000001];
        for (int i = 0; i < 100000000; i++) {
            String ss = new String("3213" + i);
            b[ss.hashCode() & 100000000] = 1;
        }

        for (int i = 0; i < 100000000; i++) {
            if (b[i] == 1) {
                System.out.println(i);
            }
        }
        System.out.println("time is : " + (System.currentTimeMillis() - startTime));
    }

//    public static String getRandomString(int length) {
//        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int number = random.nextInt(62);
//            sb.append(str.charAt(number));
//        }
//        return sb.toString();
//    }
}
