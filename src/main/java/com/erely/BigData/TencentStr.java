package com.erely.BigData;

public class TencentStr {
    private static final char a = ',';
    private static final char b = '.';
    private static final char c = ' ';
    private static final char d = '!';
    private static final String ss = "tencent";
    private static final int length = ss.length();
    private static boolean left;
    //一段字符串中单词出现的次数
    public static void main(String[] args) {
        String ss = "An sincerity so extremity he additions. Her yet <strong>there truth merit</strong>. Mrs all projecting favourable now tencent. Son law garden chatty temper. Oh children provided to mr elegance marriage strongly. Off can admiration prosperous now devonshire diminution law.</p >\n" +
                "<p>Received overcame oh sensible so at an. Formed do change tencent to county it. <strong>Am separate contempt</strong> atencent to to oh. On relation my so addition branched. Put hearing cottage she norland letters equally prepare too. Replied exposed savings he no tencents as up. Soon body add him hill. No father living really people estate if. Mistake do produce beloved demesne if am pursuit.</p >\n" +
                "<p>An sincerity so extremity he additions. Her yet <strong>there truth merit</strong>. Mrs all projecting favourable now unpleasing. Son law garden chatty temper. Oh children provided to mr elegance marriage strongly. tencent can admiration prosperous now \"tencent\" diminution law.</p >";
        System.out.println(f2(ss, 0, ss.length() - 1));
    }

    private static int f2(String s, int i, int len) {
        if (len - i < length - 1) {
            return 0;
        } else if (len - i == length - 1) {
            for (int index = 0; index < ss.length(); index++) {
                if (s.charAt(index + i) != ss.charAt(index)) {
                    return 0;
                }
            }
            return 1;
        } else {
            int middle = (i + len) / 2;
            left = true;
            do {
                if (middle == i) {
                    middle = (i + len) / 2;
                    left = false;
                } else if (middle == len) {
                    return 0;
                }
                if (s.charAt(middle) == a || s.charAt(middle) == b || s.charAt(middle) == c || s.charAt(middle) == d) {
                    break;
                }
                if (left) {
                    middle--;
                } else {
                    middle++;
                }
            } while (true);

            return f2(s, i, middle - 1) + f2(s, middle + 1, len);
        }
    }


}
