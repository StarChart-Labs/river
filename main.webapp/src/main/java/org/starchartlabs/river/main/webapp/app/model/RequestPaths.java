/*
 * Copyright (c) Aug 12, 2017 StarChart Labs Authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    romeara - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.river.main.webapp.app.model;

import java.util.UUID;

import org.starchartlabs.river.main.webapp.app.util.UriBuilders;

import com.google.common.collect.ImmutableMap;

//TODO romeara doc
public class RequestPaths {

    public static final String ROOT = "/";

    public static final String PROJECT_LIST = "/projects";

    public static final String PROJECT = "/projects/{projectId}";

    public static final String USER_FLOW_LIST = "/projects/{projectId}/userflows";

    public static final String USER_FLOW = "/projects/{projectId}/userflows/{userFlowId}";

    /**
     * Prevent instantiation of utility class
     */
    private RequestPaths() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate instance of utility class '" + getClass().getName() + "'");
    }

    public static String getProjectListUrl() {
        return UriBuilders.getLocalServerUriBuilder()
                .path(PROJECT_LIST)
                .toUriString();
    }

    public static String getProjectUrl(UUID projectId) {
        return UriBuilders.getLocalServerUriBuilder()
                .path(PROJECT)
                .buildAndExpand(ImmutableMap.of(
                        "projectId", projectId.toString()))
                .toUriString();
    }

    public static String getUserFlowListUrl(UUID projectId) {
        return UriBuilders.getLocalServerUriBuilder()
                .path(USER_FLOW_LIST)
                .buildAndExpand(ImmutableMap.of(
                        "projectId", projectId.toString()))
                .toUriString();
    }

    public static String getUserFlowUrl(UUID projectId, UUID userFlowId) {
        return UriBuilders.getLocalServerUriBuilder()
                .path(USER_FLOW)
                .buildAndExpand(ImmutableMap.of(
                        "projectId", projectId.toString(),
                        "userFlowId", userFlowId.toString()))
                .toUriString();
    }
}
