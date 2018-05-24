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
package org.starchartlabs.river.main.webapp.domain.model;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

//TODO romeara doc test
public class ExperienceFields {

    private final UUID userFlowId;

    private final String user;

    public ExperienceFields(UUID userFlowId, String user) {
        this.userFlowId = Objects.requireNonNull(userFlowId);
        this.user = Objects.requireNonNull(user);
    }

    public ExperienceFields(ExperienceFields fields) {
        this(fields.getUserFlowId(), fields.getUser());
    }

    public UUID getUserFlowId() {
        return userFlowId;
    }

    public String getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserFlowId(),
                getUser());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof ExperienceFields) {
            ExperienceFields compare = (ExperienceFields) obj;

            result = Objects.equals(compare.getUserFlowId(), getUserFlowId())
                    && Objects.equals(compare.getUser(), getUser());
        }

        return result;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("userFlowId", getUserFlowId())
                .add("user", getUser())
                .toString();
    }

}
