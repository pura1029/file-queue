package com.gtm.file.queue.converters;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializedConverter<T extends Serializable> implements Converter<T> {
    public SerializedConverter() {
    }

    private T deserialize(InputStream in) throws IOException {
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in, 1024));

        try {
            T entry = (T) oin.readUnshared();
            return entry;
        } catch (ClassNotFoundException var5) {
            throw new AssertionError(var5);
        }
    }

    public T from(byte[] bytes) throws IOException {
        return this.deserialize(new ByteArrayInputStream(bytes));
    }

    public void toStream(T o, OutputStream bytes) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(bytes);
        out.writeUnshared(o);
        out.close();
    }
}