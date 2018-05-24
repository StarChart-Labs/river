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
package org.starchartlabs.river.main.webapp.app.model;

import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;
import org.starchartlabs.river.main.webapp.domain.model.NoteType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO romeara doc test
public class NoteRequest {

    private final NoteType type;

    private final String noteMarkdown;

    @JsonCreator
    public NoteRequest(@JsonProperty(value = "type", required = true) NoteType type,
            @JsonProperty(value = "noteMarkdown", required = true) String noteMarkdown) {
        this.type = Objects.requireNonNull(type);
        this.noteMarkdown = Objects.requireNonNull(noteMarkdown);
    }

    public NoteType getType() {
        return type;
    }

    public String getNoteMarkdown() {
        return noteMarkdown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(),
                getNoteMarkdown());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof NoteRequest) {
            NoteRequest compare = (NoteRequest) obj;

            result = Objects.equals(compare.getType(), getType())
                    && Objects.equals(compare.getNoteMarkdown(), getNoteMarkdown());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("type", getType())
                .add("noteMarkdown", getNoteMarkdown())
                .toString();
    }

}
