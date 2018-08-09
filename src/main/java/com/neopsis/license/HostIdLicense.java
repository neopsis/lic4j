/*
 * @(#)HostIdLicense.java   08.08.2018
 *
 * Copyright (c) 2007 Neopsis GmbH
 *
 *
 */



package com.neopsis.license;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * A license based on a unique HostID
 *
 * @author Robert
 * @version 8.8.2018.
 */
public class HostIdLicense extends AbstractLicense {

    @JSONField(name = "hostId")
    private String hostId;

    public HostIdLicense(String hostId) {
        this.hostId = hostId;
    }

    public HostIdLicense() {

        // we need parameterless constructor for reflection
    }

    public String getHostId() {
        return hostId;
    }

    public HostIdLicense setHostId(String hostId) {

        this.hostId = hostId;

        return this;
    }
}
