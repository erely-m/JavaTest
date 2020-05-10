package com.erely.leetcode;

public class nashizi {

    public static void main(String[] args) {
        nashizi na = new nashizi();

        System.out.println(  na.f(7, 0, 1) + na.f(7, 0, 2) +
                na.f(7, 0, 3) + na.f(7, 0, 4) + na.f(7, 0, 5));
    }

    public int f(int num, int flag, int n) { //1.代表我拿  0代表他拿

        if (num - n <= 6 && num >= 5 && flag == 1) { //他不能一次拿完
            return 1;
        } else if (num <= 5 && flag == 1) { //他能一次拿完
            return 0;
        } else {
            if (flag == 1) {
                return f(num - 1, 0, 1) + f(num - 2, 0, 2) + f(num - 3, 0, 3) + f(num - 4, 0, 4) + f(num - 5, 0, 5);
            } else {
                return f(num - 1, 1, 1) + f(num - 2, 1, 2) + f(num - 3, 1, 3) + f(num - 4, 1, 4) + f(num - 5, 1, 5);
            }
        }

    }
}
