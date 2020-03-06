package com.erely.mybatis.type;

import java.util.Map;

public class ClassA <K,V> {

    protected Map<K,V> map;

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }
}
