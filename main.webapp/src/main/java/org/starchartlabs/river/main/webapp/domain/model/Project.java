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

//TODO romeara doc, test
public class Project extends ProjectFields {

    private final UUID id;

    public Project(UUID id, String name) {
        super(name);

        this.id = Objects.requireNonNull(id);
    }

    public Project(UUID id, ProjectFields fields) {
        super(fields);

        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(),
                super.hashCode());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Project) {
            Project compare = (Project) obj;

            result = super.equals(compare)
                    && Objects.equals(getId(), compare.getId());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("id", getId())
                .add("fields", super.toString())
                .toString();
    }
}
