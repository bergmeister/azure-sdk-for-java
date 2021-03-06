/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.backup.v2016_12_01;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Daily retention format.
 */
public class DailyRetentionFormat {
    /**
     * List of days of the month.
     */
    @JsonProperty(value = "daysOfTheMonth")
    private List<Day> daysOfTheMonth;

    /**
     * Get list of days of the month.
     *
     * @return the daysOfTheMonth value
     */
    public List<Day> daysOfTheMonth() {
        return this.daysOfTheMonth;
    }

    /**
     * Set list of days of the month.
     *
     * @param daysOfTheMonth the daysOfTheMonth value to set
     * @return the DailyRetentionFormat object itself.
     */
    public DailyRetentionFormat withDaysOfTheMonth(List<Day> daysOfTheMonth) {
        this.daysOfTheMonth = daysOfTheMonth;
        return this;
    }

}
