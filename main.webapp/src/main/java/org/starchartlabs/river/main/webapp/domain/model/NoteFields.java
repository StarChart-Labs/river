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
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;

//TODO romeara doc, test
public class NoteFields {

    private final UUID experienceId;

    private final int order;

    private final NoteType type;

    private final String noteMarkdown;

    public NoteFields(UUID experienceId, int order, NoteType type, String noteMarkdown) {
        this.experienceId = Objects.requireNonNull(experienceId);
        this.order = Objects.requireNonNull(order);
        this.type = Objects.requireNonNull(type);
        this.noteMarkdown = Objects.requireNonNull(noteMarkdown);
    }

    public NoteFields(NoteFields fields) {
        this(fields.getExperienceId(), fields.getOrder(), fields.getType(), fields.getNoteMarkdown());
    }

    public UUID getExperienceId() {
        return experienceId;
    }

    public int getOrder() {
        return order;
    }

    public NoteType getType() {
        return type;
    }

    public String getNoteMarkdown() {
        return noteMarkdown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExperienceId(),
                getOrder(),
                getType(),
                getNoteMarkdown());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof NoteFields) {
            NoteFields compare = (NoteFields) obj;

            result = Objects.equals(compare.getExperienceId(), getExperienceId())
                    && Objects.equals(compare.getOrder(), getOrder())
                    && Objects.equals(compare.getType(), getType())
                    && Objects.equals(compare.getNoteMarkdown(), getNoteMarkdown());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("experienceId", getExperienceId())
                .add("order", getOrder())
                .add("type", getType())
                .add("noteMarkdown", getNoteMarkdown())
                .toString();
    }

}
