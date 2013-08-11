package com.exadel.borsch.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad
 */
public class Entry implements Map.Entry<Long, Long> {

    private Long key;
    private Long value;

    public Entry() {
    }

    public Entry(Long key, Long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Long getKey() {
        return key;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public Long setValue(Long value) {
        Long old = this.value;
        this.value = value;
        return old;
    }

    public static Map<Long, Long> parseToMap(List<Entry> entries) {
        Map<Long, Long> map = new HashMap<>();
        for (Entry entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
