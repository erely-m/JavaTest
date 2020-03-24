package com.erely.kafka.demo;

public class ConsumerTest2 {

    public static void main(String[] args) {

        new Consumer(KafkaProperties.TOPIC).start();
    }
}
