/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.appservice.v2016_03_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Information about the formal API definition for the app.
 */
public class ApiDefinitionInfo {
    /**
     * The URL of the API definition.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * Get the URL of the API definition.
     *
     * @return the url value
     */
    public String url() {
        return this.url;
    }

    /**
     * Set the URL of the API definition.
     *
     * @param url the url value to set
     * @return the ApiDefinitionInfo object itself.
     */
    public ApiDefinitionInfo withUrl(String url) {
        this.url = url;
        return this;
    }

}
