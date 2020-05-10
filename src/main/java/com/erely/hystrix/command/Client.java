package com.erely.hystrix.command;

import com.erely.hystrix.command.impl.ConcreteCommand;

public class Client {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Command command = new ConcreteCommand(new Receiver());
        invoker.setCommand(command);
        invoker.action();
    }
}
