/*
 * Copyright (c) Aug 13, 2017 StarChart Labs Authors.
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

//TODO romeara doc test
public class ExperienceView {

    @JsonProperty(value = "user", required = true)
    private final String user;

    @JsonProperty(value = "_meta", required = true)
    private final MetaDataView metaData;

    public ExperienceView(String user, MetaDataView metaData) {
        this.user = Objects.requireNonNull(user);
        this.metaData = Objects.requireNonNull(metaData);
    }

    public String getUser() {
        return user;
    }

    public MetaDataView getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof ExperienceView) {
            ExperienceView compare = (ExperienceView) obj;

            result = Objects.equals(compare.getUser(), getUser())
                    && Objects.equals(compare.getMetaData(), getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("user", getUser())
                .add("metaData", getMetaData())
                .toString();
    }

}
