// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.http.policy;

import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.Configuration;
import com.azure.core.util.Context;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.ServiceVersion;
import reactor.core.publisher.Mono;

/**
 * Pipeline policy that adds "User-Agent" header to a request.
 *
 * The format for the "User-Agent" string is outlined in
 * <a href="https://azure.github.io/azure-sdk/general_azurecore.html#telemetry-policy">Azure Core: Telemetry policy</a>.
 */
public class UserAgentPolicy implements HttpPipelinePolicy {
    private static final String USER_AGENT = "User-Agent";
    private static final String DEFAULT_USER_AGENT_HEADER = "azsdk-java";

    /**
     * Key for {@link Context} to add a value which will override the User-Agent supplied in this policy in an ad-hoc
     * manner.
     */
    public static final String OVERRIDE_USER_AGENT_CONTEXT_KEY = "Override-User-Agent";

    /**
     * Key for {@link Context} to add a value which will be appended to the User-Agent supplied in this policy in an
     * ad-hoc manner.
     */
    public static final String APPEND_USER_AGENT_CONTEXT_KEY = "Append-User-Agent";

    /*
     * The base User-Agent header format is azsdk-java-<client_lib>/<sdk_version>. Additional information such as the
     * application ID will be prepended and platform telemetry will be appended, a fully configured User-Agent header
     * format is <application_id> azsdk-java-<client_lib>/<sdk_version> <platform_info>.
     */
    private static final String DEFAULT_USER_AGENT_FORMAT = DEFAULT_USER_AGENT_HEADER + "-%s/%s";

    // From the design guidelines, the platform info format is:
    // <language runtime>; <os name> <os version>
    private static final String PLATFORM_INFO_FORMAT = "%s; %s %s";

    private final String userAgent;

    /**
     * Creates a {@link UserAgentPolicy} with a default user agent string.
     */
    public UserAgentPolicy() {
        this(null);
    }

    /**
     * Creates a UserAgentPolicy with {@code userAgent} as the header value. If {@code userAgent} is {@code null}, then
     * the default user agent value is used.
     *
     * @param userAgent The user agent string to add to request headers.
     */
    public UserAgentPolicy(String userAgent) {
        if (userAgent != null) {
            this.userAgent = userAgent;
        } else {
            this.userAgent = DEFAULT_USER_AGENT_HEADER;
        }
    }

    /**
     * Creates a UserAgentPolicy with the {@code sdkName} and {@code sdkVersion} in the User-Agent header value.
     *
     * <p>If the passed configuration contains true for AZURE_TELEMETRY_DISABLED the platform information won't be
     * included in the user agent.</p>
     *
     * @param applicationId User specified application Id.
     * @param sdkName Name of the client library.
     * @param sdkVersion Version of the client library.
     * @param configuration Configuration store that will be checked for {@link
     * Configuration#PROPERTY_AZURE_TELEMETRY_DISABLED}. If {@code null} is passed the {@link
     * Configuration#getGlobalConfiguration() global configuration} will be checked.
     */
    public UserAgentPolicy(String applicationId, String sdkName, String sdkVersion, Configuration configuration) {
        this.userAgent = buildUserAgent(applicationId, sdkName, sdkVersion, configuration);
    }

    /**
     * Creates a UserAgentPolicy with the {@code sdkName} and {@code sdkVersion} in the User-Agent header value.
     *
     * <p>If the passed configuration contains true for AZURE_TELEMETRY_DISABLED the platform information won't be
     * included in the user agent.</p>
     *
     * @param sdkName Name of the client library.
     * @param sdkVersion Version of the client library.
     * @param version {@link ServiceVersion} of the service to be used when making requests.
     * @param configuration Configuration store that will be checked for {@link
     * Configuration#PROPERTY_AZURE_TELEMETRY_DISABLED}. If {@code null} is passed the {@link
     * Configuration#getGlobalConfiguration() global configuration} will be checked.
     */
    public UserAgentPolicy(String sdkName, String sdkVersion, Configuration configuration, ServiceVersion version) {
        this.userAgent = buildUserAgent(null, sdkName, sdkVersion, configuration);
    }

    /**
     * Updates the "User-Agent" header with the value supplied in the policy.
     *
     * <p>The {@code context} will be checked for {@code Override-User-Agent} and {@code Append-User-Agent}.
     * {@code Override-User-Agent} will take precedence over the value supplied in the policy,
     * {@code Append-User-Agent} will be appended to the value supplied in the policy.</p>
     *
     * @param context request context
     * @param next The next policy to invoke.
     * @return A publisher that initiates the request upon subscription and emits a response on completion.
     */
    @Override
    public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy next) {
        String overrideUserAgent = (String) context.getData(OVERRIDE_USER_AGENT_CONTEXT_KEY).orElse(null);
        String appendUserAgent = (String) context.getData(APPEND_USER_AGENT_CONTEXT_KEY).orElse(null);

        String userAgentValue;
        if (!CoreUtils.isNullOrEmpty(overrideUserAgent)) {
            userAgentValue = overrideUserAgent;
        } else if (!CoreUtils.isNullOrEmpty(appendUserAgent)) {
            userAgentValue = userAgent + " " + appendUserAgent;
        } else {
            userAgentValue = userAgent;
        }

        context.getHttpRequest().getHeaders().put(USER_AGENT, userAgentValue);
        return next.process();
    }

    /*
     * Builds the User-Agent header, at minimum this will create a User-Agent header with the DEFAULT_USER_AGENT_FORMAT.
     */
    protected static String buildUserAgent(String applicationId, String sdkName, String sdkVersion,
        Configuration configuration) {
        StringBuilder userAgentBuilder = new StringBuilder();

        // Only add the application ID if it is present as it is optional.
        if (applicationId != null) {
            userAgentBuilder.append(applicationId).append(" ");
        }

        // Add the required default User-Agent string.
        userAgentBuilder.append(String.format(DEFAULT_USER_AGENT_FORMAT, sdkName, sdkVersion));

        // Only add the platform telemetry if it is allowed as it is optional.
        if (!telemetryDisabled(configuration)) {
            userAgentBuilder.append(" ")
                .append("(")
                .append(getPlatformInfo())
                .append(")");
        }

        return userAgentBuilder.toString();
    }

    /*
     * Retrieves the platform information telemetry that is appended to the User-Agent header.
     */
    private static String getPlatformInfo() {
        String javaVersion = Configuration.getGlobalConfiguration().get("java.version");
        String osName = Configuration.getGlobalConfiguration().get("os.name");
        String osVersion = Configuration.getGlobalConfiguration().get("os.version");

        return String.format(PLATFORM_INFO_FORMAT, javaVersion, osName, osVersion);
    }

    /*
     * Retrieves the telemetry disabled flag from the passed configuration if it isn't {@code null} otherwise it will
     * check in the global configuration.
     */
    private static boolean telemetryDisabled(Configuration configuration) {
        return (configuration == null)
            ? Configuration.getGlobalConfiguration().get(Configuration.PROPERTY_AZURE_TELEMETRY_DISABLED, false)
            : configuration.get(Configuration.PROPERTY_AZURE_TELEMETRY_DISABLED, false);
    }
}
