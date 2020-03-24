package com.erely.google.rateLimter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimerTest {

    public static void main(String[] args) {

        RateLimiter rateLimiter = RateLimiter.create(10);

        for (int i = 1; i < 10; i++) {
            System.out.println(i + "  " + rateLimiter.acquire(i));
        }


    }
}
