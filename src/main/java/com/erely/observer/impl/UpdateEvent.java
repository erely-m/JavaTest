package com.erely.observer.impl;

import com.erely.observer.Event;

/**
 * 更新事件
 */
public class UpdateEvent implements Event {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
