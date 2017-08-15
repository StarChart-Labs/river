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

import org.starchartlabs.river.main.webapp.domain.model.NoteType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//TODO romeara doc test
public class NoteView {

    @JsonProperty(value = "type", required = true)
    private final NoteType type;

    @JsonProperty(value = "noteMarkdown", required = true)
    private final String noteMarkdown;

    @JsonProperty(value = "_meta", required = true)
    private final MetaDataView metaData;

    @JsonCreator
    public NoteView(NoteType type, String noteMarkdown, MetaDataView metaData) {
        this.type = Objects.requireNonNull(type);
        this.noteMarkdown = Objects.requireNonNull(noteMarkdown);
        this.metaData = Objects.requireNonNull(metaData);
    }

    public NoteType getType() {
        return type;
    }

    public String getNoteMarkdown() {
        return noteMarkdown;
    }

    public MetaDataView getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(),
                getNoteMarkdown(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof NoteView) {
            NoteView compare = (NoteView) obj;

            result = Objects.equals(compare.getType(), getType())
                    && Objects.equals(compare.getNoteMarkdown(), getNoteMarkdown())
                    && Objects.equals(compare.getMetaData(), getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("type", getType())
                .add("noteMarkdown", getNoteMarkdown())
                .add("metaData", getMetaData())
                .toString();
    }
}