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
package org.starchartlabs.river.main.webapp.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.starchartlabs.river.main.webapp.domain.model.Note;
import org.starchartlabs.river.main.webapp.domain.model.NoteFields;
import org.starchartlabs.river.main.webapp.repository.api.INoteRepository;

//TODO romeara replace with real persistence
public class MemoryNoteRepository implements INoteRepository {

    private final Map<UUID, Note> notes = new HashMap<>();

    @Override
    public Note create(NoteFields fields) {
        Objects.requireNonNull(fields);

        UUID id = UUID.randomUUID();
        Note result = new Note(id, fields);

        notes.put(result.getId(), result);

        return result;
    }

    @Override
    public Optional<Note> get(UUID id) {
        Objects.requireNonNull(id);

        return Optional.ofNullable(notes.get(id));
    }

    @Override
    public Collection<Note> getByExperienceId(UUID experienceId) {
        Objects.requireNonNull(experienceId);

        return notes.values().stream()
                .filter(input -> Objects.equals(input.getExperienceId(), experienceId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Note note) {
        Objects.requireNonNull(note);

        return (notes.remove(note) != null);
    }

}
