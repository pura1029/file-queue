package com.gtm.file.queue.listeners;

import com.gtm.file.queue.ObjectQueue;

public interface Listener<T> {
        void onAdd(ObjectQueue<T> var1, T var2);

        void onRemove(ObjectQueue<T> var1, T var2);
    }