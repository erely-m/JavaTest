package com.erely.observer.impl;

import com.erely.observer.Event;
import com.erely.observer.Watcher;
import com.erely.observer.WatcherManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultWatcherManager implements WatcherManager {

    private HashMap<Event, List<Watcher>> eventListHashMap = new HashMap<>();

    @Override
    public synchronized void addWatcher(Event event, Watcher watcher) {
        assert (event == null || watcher == null);
        List<Watcher> watchers = eventListHashMap.get(event);
        if (watchers == null) {
            watchers = new ArrayList<>();
            watchers.add(watcher);
        }
        eventListHashMap.put(event, watchers);
    }

    @Override
    public synchronized void deleteWatcher(Event event, Watcher watcher) {
        assert (event == null || watcher == null);
        List<Watcher> watchers = eventListHashMap.get(event);
        if (watchers != null) {
            watchers.remove(watcher);
        }
    }

    @Override
    public synchronized void notify(Event event) { //通知所有观察者处理
        assert (event == null);
        List<Watcher> watchers = eventListHashMap.get(event);
        if (watchers != null) {
            for (Watcher watcher : watchers) {
                watcher.process(event);
            }
        }
    }

    @Override
    public synchronized List<Watcher> getWatcher(Event event) {
        assert (event == null);
        return eventListHashMap.get(event);
    }
}
