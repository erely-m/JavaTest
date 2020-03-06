package com.erely.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(LogTest.class);
        log.debug("hello world!");

    }
}
