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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;

//TODO romeara doc, test
public class ProjectFields {

    private final String name;

    public ProjectFields(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public ProjectFields(ProjectFields fields) {
        this(fields.getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof ProjectFields) {
            ProjectFields compare = (ProjectFields) obj;

            result = Objects.equals(getName(), compare.getName());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("name", getName())
                .toString();
    }
}
