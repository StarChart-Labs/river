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

import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//TODO romeara doc test
public class UserFlowRequest {

    private final String name;

    private final String setupMarkdown;

    private final String goalMarkdown;

    @JsonCreator
    public UserFlowRequest(@JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "setupMarkdown", required = true) String setupMarkdown,
            @JsonProperty(value = "goalMarkdown", required = true) String goalMarkdown) {
        this.name = Objects.requireNonNull(name);
        this.setupMarkdown = Objects.requireNonNull(setupMarkdown);
        this.goalMarkdown = Objects.requireNonNull(goalMarkdown);
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
        return Objects.hash(getName(),
                getSetupMarkdown(),
                getGoalMarkdown());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof UserFlowRequest) {
            UserFlowRequest compare = (UserFlowRequest) obj;

            result = Objects.equals(getName(), compare.getName())
                    && Objects.equals(getSetupMarkdown(), compare.getSetupMarkdown())
                    && Objects.equals(getGoalMarkdown(), compare.getGoalMarkdown());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("name", getName())
                .add("setupMarkdown", getSetupMarkdown())
                .add("goalMarkdown", getGoalMarkdown())
                .toString();
    }

}
