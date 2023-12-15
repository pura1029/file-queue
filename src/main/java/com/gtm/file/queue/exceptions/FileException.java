package com.gtm.file.queue.exceptions;

import java.io.File;
import java.io.IOException;

public class FileException extends RuntimeException {
    private final File file;

    public FileException(String message, IOException e, File file) {
        super(message, e);
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }
}