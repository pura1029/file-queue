/*
 * Copyright (c) 2023 VMware, Inc. All Rights Reserved.
 *
 */

package com.gtm.file.queue.test;

import com.gtm.file.queue.listeners.EmpListener;
import com.gtm.file.queue.FileObjectQueue;
import com.gtm.file.queue.converters.SerializedConverter;

import java.io.File;
import java.io.IOException;

/**
 * Insert your comment for {@link FileQueueTest}.
 *
 * @author kumargautam
 */
public class FileQueueTest {


    public static void main(String[] args) throws IOException {
        FileObjectQueue<Emp> queue = new FileObjectQueue<>(new File("/Users/kumargautam/Git_repo/GitHub-test/file-queue/test.txt"), new SerializedConverter<>());

        queue.setListener(new EmpListener());

        for (int i = 0; i < 100; i++) {
            Emp emp = new Emp(i, "test-" + i);
            queue.add(emp);
        }
        System.out.println("==============================");
        for (int i = 0; i < 100; i++) {
            queue.remove();
        }

        queue.close();
    }
}
