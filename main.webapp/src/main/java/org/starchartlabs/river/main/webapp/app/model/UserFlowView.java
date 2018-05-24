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

import org.starchartlabs.alloy.core.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO romeara doc test
public class UserFlowView {

    @JsonProperty(value = "name", required = true)
    private final String name;

    @JsonProperty(value = "setupMarkdown", required = true)
    private final String setupMarkdown;

    @JsonProperty(value = "goalMarkdown", required = true)
    private final String goalMarkdown;

    @JsonProperty(value = "_meta", required = true)
    private final MetaDataView metaData;

    public UserFlowView(String name, String setupMarkdown, String goalMarkdown, MetaDataView metaData) {
        this.name = Objects.requireNonNull(name);
        this.setupMarkdown = Objects.requireNonNull(setupMarkdown);
        this.goalMarkdown = Objects.requireNonNull(goalMarkdown);
        this.metaData = Objects.requireNonNull(metaData);
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

    public MetaDataView getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getSetupMarkdown(),
                getGoalMarkdown(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof UserFlowRequest) {
            UserFlowView compare = (UserFlowView) obj;

            result = Objects.equals(getName(), compare.getName())
                    && Objects.equals(getSetupMarkdown(), compare.getSetupMarkdown())
                    && Objects.equals(getGoalMarkdown(), compare.getGoalMarkdown())
                    && Objects.equals(getMetaData(), compare.getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("name", getName())
                .add("setupMarkdown", getSetupMarkdown())
                .add("goalMarkdown", getGoalMarkdown())
                .add("metaData", getMetaData())
                .toString();
    }

}
