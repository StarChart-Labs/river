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

import org.starchartlabs.alloy.core.MoreObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO romeara doc test
public class ExperienceRequest {

    private final String user;

    @JsonCreator
    public ExperienceRequest(@JsonProperty(value = "user", required = true) String user) {
        this.user = Objects.requireNonNull(user);
    }

    public String getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof ExperienceRequest) {
            ExperienceRequest compare = (ExperienceRequest) obj;

            result = Objects.equals(compare.getUser(), getUser());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("user", getUser())
                .toString();
    }

}
