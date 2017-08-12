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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//TODO romeara doc, test
public class ProjectView {

    @JsonProperty(value = "name", required = true)
    private final String name;

    @JsonProperty(value = "_meta", required = true)
    private final MetaDataView metaData;

    public ProjectView(String name, MetaDataView metaData) {
        this.name = Objects.requireNonNull(name);
        this.metaData = Objects.requireNonNull(metaData);
    }

    public String getName() {
        return name;
    }

    public MetaDataView getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof ProjectView) {
            ProjectView compare = (ProjectView) obj;

            result = Objects.equals(getName(), compare.getName())
                    && Objects.equals(getMetaData(), compare.getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("name", getName())
                .add("metaData", getMetaData())
                .toString();
    }

}
