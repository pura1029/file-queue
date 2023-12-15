package com.gtm.file.queue;

import com.gtm.file.queue.listeners.Listener;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class InMemoryObjectQueue<T> implements ObjectQueue<T> {
    private final Queue<T> tasks = new LinkedList();
    private Listener<T> listener;

    public InMemoryObjectQueue() {
    }

    public void add(T entry) {
        this.tasks.add(entry);
        if (this.listener != null) {
            this.listener.onAdd(this, entry);
        }

    }

    public T peek() {
        return this.tasks.peek();
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove() {
        T data = this.tasks.remove();
        if (this.listener != null) {
            this.listener.onRemove(this, data);
        }

    }

    public void setListener(Listener<T> listener) {
        if (listener != null) {
            Iterator<T> var2 = this.tasks.iterator();

            while (var2.hasNext()) {
                T task = var2.next();
                listener.onAdd(this, task);
            }
        }

        this.listener = listener;
    }
}