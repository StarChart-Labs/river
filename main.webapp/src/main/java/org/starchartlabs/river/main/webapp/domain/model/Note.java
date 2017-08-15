/*
 * Copyright (c) Aug 14, 2017 StarChart Labs Authors.
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
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Comparators;

//TODO romeara doc test
public class Note extends NoteFields implements Comparable<Note> {

    private final UUID id;

    public Note(UUID id, NoteFields fields) {
        super(fields);

        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int compareTo(Note o) {
        Optional<Integer> compareOrder = Optional.ofNullable(o)
                .map(Note::getOrder);

        return Comparators.emptiesLast(Integer::compare)
                .compare(Optional.of(getOrder()), compareOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                super.hashCode());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Note) {
            Note compare = (Note) obj;

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
