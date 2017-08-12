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
package org.starchartlabs.river.main.webapp.domain.model;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;

//TODO romeara doc test
public class UserFlowFields {

    private final UUID projectId;

    private final String name;

    private final String setupMarkdown;

    private final String goalMarkdown;

    public UserFlowFields(UUID projectId, String name, String setupMarkdown, String goalMarkdown) {
        this.projectId = Objects.requireNonNull(projectId);
        this.name = Objects.requireNonNull(name);
        this.setupMarkdown = Objects.requireNonNull(setupMarkdown);
        this.goalMarkdown = Objects.requireNonNull(goalMarkdown);
    }

    public UserFlowFields(UserFlowFields fields) {
        this(fields.getProjectId(),
                fields.getName(),
                fields.getSetupMarkdown(),
                fields.getGoalMarkdown());
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getSetupMarkdown() {
        return setupMarkdown;
    }

    public String getGoalMarkdown() {
        return goalMarkdown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(),
                getName(),
                getSetupMarkdown(),
                getGoalMarkdown());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof UserFlowFields) {
            UserFlowFields compare = (UserFlowFields) obj;

            result = Objects.equals(getProjectId(), compare.getProjectId())
                    && Objects.equals(getName(), compare.getName())
                    && Objects.equals(getSetupMarkdown(), compare.getSetupMarkdown())
                    && Objects.equals(getGoalMarkdown(), compare.getGoalMarkdown());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("projectId", getProjectId())
                .add("name", getName())
                .add("setupMarkdown", getSetupMarkdown())
                .add("goalMarkdown", getGoalMarkdown())
                .toString();
    }

}
