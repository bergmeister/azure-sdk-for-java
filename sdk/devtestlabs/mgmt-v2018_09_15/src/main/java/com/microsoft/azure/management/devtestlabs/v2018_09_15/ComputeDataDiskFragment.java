/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.devtestlabs.v2018_09_15;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data disks attached to a virtual machine.
 */
public class ComputeDataDiskFragment {
    /**
     * Gets data disk name.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * When backed by a blob, the URI of underlying blob.
     */
    @JsonProperty(value = "diskUri")
    private String diskUri;

    /**
     * When backed by managed disk, this is the ID of the compute disk
     * resource.
     */
    @JsonProperty(value = "managedDiskId")
    private String managedDiskId;

    /**
     * Gets data disk size in GiB.
     */
    @JsonProperty(value = "diskSizeGiB")
    private Integer diskSizeGiB;

    /**
     * Get gets data disk name.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set gets data disk name.
     *
     * @param name the name value to set
     * @return the ComputeDataDiskFragment object itself.
     */
    public ComputeDataDiskFragment withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get when backed by a blob, the URI of underlying blob.
     *
     * @return the diskUri value
     */
    public String diskUri() {
        return this.diskUri;
    }

    /**
     * Set when backed by a blob, the URI of underlying blob.
     *
     * @param diskUri the diskUri value to set
     * @return the ComputeDataDiskFragment object itself.
     */
    public ComputeDataDiskFragment withDiskUri(String diskUri) {
        this.diskUri = diskUri;
        return this;
    }

    /**
     * Get when backed by managed disk, this is the ID of the compute disk resource.
     *
     * @return the managedDiskId value
     */
    public String managedDiskId() {
        return this.managedDiskId;
    }

    /**
     * Set when backed by managed disk, this is the ID of the compute disk resource.
     *
     * @param managedDiskId the managedDiskId value to set
     * @return the ComputeDataDiskFragment object itself.
     */
    public ComputeDataDiskFragment withManagedDiskId(String managedDiskId) {
        this.managedDiskId = managedDiskId;
        return this;
    }

    /**
     * Get gets data disk size in GiB.
     *
     * @return the diskSizeGiB value
     */
    public Integer diskSizeGiB() {
        return this.diskSizeGiB;
    }

    /**
     * Set gets data disk size in GiB.
     *
     * @param diskSizeGiB the diskSizeGiB value to set
     * @return the ComputeDataDiskFragment object itself.
     */
    public ComputeDataDiskFragment withDiskSizeGiB(Integer diskSizeGiB) {
        this.diskSizeGiB = diskSizeGiB;
        return this;
    }

}
