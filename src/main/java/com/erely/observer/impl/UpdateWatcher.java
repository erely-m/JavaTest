package com.erely.observer.impl;

import com.erely.observer.Event;
import com.erely.observer.Watcher;

/**
 * 更新事件观察者
 */
public class UpdateWatcher implements Watcher {
    @Override
    public void process(Event event) {
        System.out.println(((UpdateEvent)event).getName() + "update");
    }
}
