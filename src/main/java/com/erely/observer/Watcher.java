package com.erely.observer;

/**
 * 观察者接口
 */
public interface Watcher {

    void process(Event event);
}
