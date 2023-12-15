/*
 * Copyright (c) 2023 VMware, Inc. All Rights Reserved.
 *
 */

package com.gtm.file.queue.listeners;

import com.gtm.file.queue.test.Emp;
import com.gtm.file.queue.ObjectQueue;

/**
 * Insert your comment for {@link EmpListener}.
 *
 * @author kumargautam
 */
public class EmpListener implements Listener<Emp> {
    @Override
    public void onAdd(ObjectQueue<Emp> objectQueue, Emp emp) {
        System.out.println(emp);
    }

    @Override
    public void onRemove(ObjectQueue<Emp> objectQueue, Emp emp) {
        System.out.println(emp);
    }
}
