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
package org.starchartlabs.river.main.webapp.app.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.starchartlabs.river.main.webapp.app.api.INoteAppService;
import org.starchartlabs.river.main.webapp.app.model.MetaDataView;
import org.starchartlabs.river.main.webapp.app.model.NoteRequest;
import org.starchartlabs.river.main.webapp.app.model.NoteView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.domain.model.Note;
import org.starchartlabs.river.main.webapp.domain.model.NoteFields;
import org.starchartlabs.river.main.webapp.repository.api.INoteRepository;

//TODO romeara doc test
public class NoteAppService implements INoteAppService {

    private final INoteRepository noteRepository;

    public NoteAppService(INoteRepository noteRepository) {
        this.noteRepository = Objects.requireNonNull(noteRepository);
    }

    @Override
    public NoteView create(UUID projectId, UUID userFlowId, UUID experienceId, NoteRequest request) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);
        Objects.requireNonNull(request);

        int currentNoteCount = noteRepository.getByExperienceId(experienceId).size();
        NoteFields fields = toFields(experienceId, currentNoteCount, request);

        return toView(projectId, userFlowId, noteRepository.create(fields));
    }

    @Override
    public Optional<NoteView> get(UUID projectId, UUID userFlowId, UUID experienceId, UUID id) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);
        Objects.requireNonNull(id);

        return noteRepository.get(id)
                .map(note -> toView(projectId, userFlowId, note));
    }

    @Override
    public PageView<NoteView> get(UUID projectId, UUID userFlowId, UUID experienceId) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);

        List<NoteView> items = noteRepository.getByExperienceId(experienceId).stream()
                .sorted()
                .map(note -> toView(projectId, userFlowId, note))
                .collect(Collectors.toList());

        MetaDataView metaData = new MetaDataView(
                RequestPaths.getNoteListUrl(projectId, userFlowId, experienceId));

        return new PageView<>(items, metaData);
    }

    @Override
    public boolean delete(UUID projectId, UUID userFlowId, UUID experienceId, UUID id) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(experienceId);
        Objects.requireNonNull(id);

        return noteRepository.get(id)
                .map(noteRepository::delete)
                .orElse(false);
    }

    private NoteFields toFields(UUID experienceId, int currentNoteCount, NoteRequest request) {
        return new NoteFields(experienceId,
                currentNoteCount,
                request.getType(),
                request.getNoteMarkdown());
    }

    private NoteView toView(UUID projectId, UUID userFlowId, Note note) {
        MetaDataView metaData = new MetaDataView(
                RequestPaths.getNoteUrl(projectId, userFlowId, note.getExperienceId(), note.getId()));

        return new NoteView(note.getType(),
                note.getNoteMarkdown(),
                metaData);
    }

}
