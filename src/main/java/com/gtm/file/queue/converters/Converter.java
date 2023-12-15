package com.gtm.file.queue.converters;

import java.io.IOException;
import java.io.OutputStream;

public interface Converter<T> {
        T from(byte[] var1) throws IOException;

        void toStream(T var1, OutputStream var2) throws IOException;
    }