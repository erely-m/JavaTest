package com.erely.observer;

import com.erely.observer.impl.DefaultWatcherManager;
import com.erely.observer.impl.UpdateEvent;
import com.erely.observer.impl.UpdateWatcher;

public class ObserverTest {

    public static void main(String[] args) {

        DefaultWatcherManager dwm = new DefaultWatcherManager();
        UpdateEvent event = new UpdateEvent();
        event.setName("test");
        dwm.addWatcher(event,new UpdateWatcher());
        dwm.notify(event);

    }
}
