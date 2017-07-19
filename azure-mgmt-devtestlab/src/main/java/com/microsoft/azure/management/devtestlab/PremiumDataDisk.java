/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.devtestlab;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for PremiumDataDisk.
 */
public final class PremiumDataDisk {
    /** Static value Disabled for PremiumDataDisk. */
    public static final PremiumDataDisk DISABLED = new PremiumDataDisk("Disabled");

    /** Static value Enabled for PremiumDataDisk. */
    public static final PremiumDataDisk ENABLED = new PremiumDataDisk("Enabled");

    private String value;

    /**
     * Creates a custom value for PremiumDataDisk.
     * @param value the custom value
     */
    public PremiumDataDisk(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PremiumDataDisk)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PremiumDataDisk rhs = (PremiumDataDisk) obj;
        if (value == null) {
            return rhs.value == null;
        } else {
            return value.equals(rhs.value);
        }
    }
}