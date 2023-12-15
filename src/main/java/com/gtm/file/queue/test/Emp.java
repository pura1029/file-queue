/*
 * Copyright (c) 2023 VMware, Inc. All Rights Reserved.
 *
 */

package com.gtm.file.queue.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Insert your comment for {@link Emp}.
 *
 * @author kumargautam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable {

    private int id;
    private String name;
}
