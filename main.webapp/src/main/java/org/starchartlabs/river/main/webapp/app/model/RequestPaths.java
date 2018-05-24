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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.app.util.UriBuilders;

//TODO romeara doc
public class RequestPaths {

    public static final String ROOT = "/";

    public static final String PROJECT_LIST = "/projects";

    public static final String PROJECT = "/projects/{projectId}";

    public static final String USER_FLOW_LIST = "/projects/{projectId}/userflows";

    public static final String USER_FLOW = "/projects/{projectId}/userflows/{userFlowId}";

    public static final String EXPERIENCE_LIST = "/projects/{projectId}/userflows/{userFlowId}/experiences";

    public static final String EXPERIENCE_CREATE = "/projects/{projectId}/userflows/{userFlowId}/experiences/new";

    public static final String EXPERIENCE = "/projects/{projectId}/userflows/{userFlowId}/experiences/{experienceId}";

    public static final String NOTE_LIST = "/projects/{projectId}/userflows/{userFlowId}/experiences/{experienceId}/notes";

    public static final String NOTE = "/projects/{projectId}/userflows/{userFlowId}/experiences/{experienceId}/notes/{noteId}";

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
        Objects.requireNonNull(projectId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(PROJECT)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getUserFlowListUrl(UUID projectId) {
        Objects.requireNonNull(projectId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(USER_FLOW_LIST)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getUserFlowUrl(UUID projectId, UUID userFlowId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(USER_FLOW)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getExperienceListUrl(UUID projectId, UUID userFlowId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(EXPERIENCE_LIST)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getExperienceCreateUrl(UUID projectId, UUID userFlowId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(EXPERIENCE_CREATE)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getExperienceUrl(UUID projectId, UUID userFlowId, UUID experienceId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());
        values.put("experienceId", experienceId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(EXPERIENCE)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getNoteListUrl(UUID projectId, UUID userFlowId, UUID experienceId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());
        values.put("experienceId", experienceId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(NOTE_LIST)
                .buildAndExpand(values)
                .toUriString();
    }

    public static String getNoteUrl(UUID projectId, UUID userFlowId, UUID experienceId, UUID noteId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);
        Objects.requireNonNull(noteId);

        Map<String, String> values = new HashMap<>();
        values.put("projectId", projectId.toString());
        values.put("userFlowId", userFlowId.toString());
        values.put("experienceId", experienceId.toString());
        values.put("noteId", noteId.toString());

        return UriBuilders.getLocalServerUriBuilder()
                .path(NOTE)
                .buildAndExpand(values)
                .toUriString();
    }
}
