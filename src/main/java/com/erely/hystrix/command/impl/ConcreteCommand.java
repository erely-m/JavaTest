package com.erely.hystrix.command.impl;

import com.erely.hystrix.command.Command;
import com.erely.hystrix.command.Receiver;

/**
 * 具体命令
 */
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
