package com.servi.cloud.consumer.util.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟threadlocal
 * @param <T>
 */
public class IThreadLocal<T> {
    private Map<Thread, T> local = new ConcurrentHashMap<>();

    public void setValue(T value) {
        local.put(Thread.currentThread(), value);
    }

    public T getValue() {
        Thread thread = Thread.currentThread();
        T value = null;
        if (local.containsKey(thread)) {
            value = local.get(thread);
        } else {
            value = this.initValue();
            local.put(thread, value);
        }
        return value;
    }

    protected T initValue() {
        return null;
    }
}
