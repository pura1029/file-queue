package com.gtm.file.queue;

import com.gtm.file.queue.listeners.Listener;

public interface ObjectQueue<T> {
    int size();

    void add(T var1);

    T peek();

    void remove();

    void setListener(Listener<T> var1);
}