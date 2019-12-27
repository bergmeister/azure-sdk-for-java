// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.textanalytics.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DocumentKeyPhrases model.
 */
@Fluent
public final class DocumentKeyPhrases {
    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /*
     * A list of representative words or phrases. The number of key phrases
     * returned is proportional to the number of words in the input document.
     */
    @JsonProperty(value = "keyPhrases", required = true)
    private List<String> keyPhrases;

    /*
     * if showStats=true was specified in the request this field will contain
     * information about the document payload.
     */
    @JsonProperty(value = "statistics")
    private DocumentStatistics statistics;

    /**
     * Get the id property: Unique, non-empty document identifier.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Unique, non-empty document identifier.
     *
     * @param id the id value to set.
     * @return the DocumentKeyPhrases object itself.
     */
    public DocumentKeyPhrases setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the keyPhrases property: A list of representative words or phrases.
     * The number of key phrases returned is proportional to the number of
     * words in the input document.
     *
     * @return the keyPhrases value.
     */
    public List<String> getKeyPhrases() {
        return this.keyPhrases;
    }

    /**
     * Set the keyPhrases property: A list of representative words or phrases.
     * The number of key phrases returned is proportional to the number of
     * words in the input document.
     *
     * @param keyPhrases the keyPhrases value to set.
     * @return the DocumentKeyPhrases object itself.
     */
    public DocumentKeyPhrases setKeyPhrases(List<String> keyPhrases) {
        this.keyPhrases = keyPhrases;
        return this;
    }

    /**
     * Get the statistics property: if showStats=true was specified in the
     * request this field will contain information about the document payload.
     *
     * @return the statistics value.
     */
    public DocumentStatistics getStatistics() {
        return this.statistics;
    }

    /**
     * Set the statistics property: if showStats=true was specified in the
     * request this field will contain information about the document payload.
     *
     * @param statistics the statistics value to set.
     * @return the DocumentKeyPhrases object itself.
     */
    public DocumentKeyPhrases setStatistics(DocumentStatistics statistics) {
        this.statistics = statistics;
        return this;
    }
}
