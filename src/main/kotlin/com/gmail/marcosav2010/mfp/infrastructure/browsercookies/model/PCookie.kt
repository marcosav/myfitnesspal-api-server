package com.gmail.marcosav2010.mfp.infrastructure.browsercookies.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PCookie(
    val version: Int?,
    val name: String,
    val value: String?,
    val port: String?,
    @JsonProperty("port_specified")
    val portSpecified: Boolean,
    val domain: String,
    @JsonProperty("domain_specified")
    val domainSpecified: Boolean,
    @JsonProperty("domain_initial_dot")
    val domainInitialDot: Boolean,
    val path: String,
    @JsonProperty("path_specified")
    val pathSpecified: Boolean,
    val secure: Boolean,
    val expires: Int?,
    val discard: Boolean,
    val comment: String?,
    @JsonProperty("comment_url")
    val commentUrl: String?,
    val rest: Map<String, String>,
    val rfc2109: Boolean
)