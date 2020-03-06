package com.erely.observer;

import java.util.List;

/**
 * 事件和观察者绑定接口
 */
public interface WatcherManager {

    void addWatcher(Event event, Watcher watcher); //绑定事件和观察者

    void deleteWatcher(Event event, Watcher watcher); //删除观察者

    void notify(Event event); //根据事件类型通知观察者

    List<Watcher> getWatcher(Event event); //根据时间获取观察者
}
