package com.gtm.file.queue;

import com.gtm.file.queue.converters.Converter;
import com.gtm.file.queue.exceptions.FileException;
import com.gtm.file.queue.listeners.Listener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileObjectQueue<T> implements ObjectQueue<T> {
    private final QueueFile queueFile;
    private final DirectByteArrayOutputStream bytes = new DirectByteArrayOutputStream();
    private final File file;
    private final Converter<T> converter;
    private Listener<T> listener;

    public FileObjectQueue(File file, Converter<T> converter) throws IOException {
        this.file = file;
        this.converter = converter;
        this.queueFile = new QueueFile(file);
    }

    public int size() {
        return this.queueFile.size();
    }

    public final void add(T entry) {
        try {
            this.bytes.reset();
            this.converter.toStream(entry, this.bytes);
            this.queueFile.add(this.bytes.getArray(), 0, this.bytes.size());
            if (this.listener != null) {
                this.listener.onAdd(this, entry);
            }

        } catch (IOException var3) {
            throw new FileException("Failed to add entry.", var3, this.file);
        }
    }

    public T peek() {
        try {
            byte[] bytes = this.queueFile.peek();
            return bytes == null ? null : this.converter.from(bytes);
        } catch (IOException var2) {
            throw new FileException("Failed to peek.", var2, this.file);
        }
    }

    public final void remove() {
        try {
            T data = this.peek();
            if(data != null) {
                this.queueFile.remove();
                if (this.listener != null) {
                    this.listener.onRemove(this, data);
                }
            }
        } catch (IOException var2) {
            throw new FileException("Failed to remove.", var2, this.file);
        }
    }

    public final void close() {
        try {
            this.queueFile.close();
        } catch (IOException var2) {
            throw new FileException("Failed to close.", var2, this.file);
        }
    }

    public void setListener(final Listener<T> listener) {
        if (listener != null) {
            try {
                this.queueFile.forEach(new QueueFile.ElementReader() {
                    public void read(InputStream in, int length) throws IOException {
                        byte[] data = new byte[length];
                        in.read(data, 0, length);
                        listener.onAdd(FileObjectQueue.this, FileObjectQueue.this.converter.from(data));
                    }
                });
            } catch (IOException var3) {
                throw new FileException("Unable to iterate over QueueFile contents.", var3, this.file);
            }
        }

        this.listener = listener;
    }

    private static class DirectByteArrayOutputStream extends ByteArrayOutputStream {
        public DirectByteArrayOutputStream() {
        }

        public byte[] getArray() {
            return this.buf;
        }
    }
}