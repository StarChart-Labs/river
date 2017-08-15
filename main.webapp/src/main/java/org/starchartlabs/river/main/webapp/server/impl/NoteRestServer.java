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
package org.starchartlabs.river.main.webapp.server.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starchartlabs.river.main.webapp.app.api.IMarkdownAppService;
import org.starchartlabs.river.main.webapp.app.api.INoteAppService;
import org.starchartlabs.river.main.webapp.app.model.ContentTypes;
import org.starchartlabs.river.main.webapp.app.model.NoteRequest;
import org.starchartlabs.river.main.webapp.app.model.NoteView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;

//TODO romeara doc test
@Controller
public class NoteRestServer {

    private final INoteAppService noteAppService;

    private final IMarkdownAppService markdownAppService;

    public NoteRestServer(INoteAppService noteAppService, IMarkdownAppService markdownAppService) {
        this.noteAppService = Objects.requireNonNull(noteAppService);
        this.markdownAppService = Objects.requireNonNull(markdownAppService);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = RequestPaths.NOTE_LIST,
    consumes = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<Void> createNote(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId,
            @PathVariable("experienceId") UUID experienceId,
            @RequestBody NoteRequest request) {
        NoteView result = noteAppService.create(projectId, userFlowId, experienceId, request);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, result.getMetaData().getHref());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.NOTE_LIST,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<PageView<NoteView>> getNotes(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId,
            @PathVariable("experienceId") UUID experienceId) {
        PageView<NoteView> result = noteAppService.get(projectId, userFlowId, experienceId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.NOTE,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<NoteView> getNote(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId,
            @PathVariable("experienceId") UUID experienceId,
            @PathVariable("noteId") UUID noteId) {
        Optional<NoteView> result = noteAppService.get(projectId, userFlowId, experienceId, noteId);

        return result.map(body -> new ResponseEntity<>(body, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, path = RequestPaths.NOTE)
    public ResponseEntity<Void> deleteNote(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId,
            @PathVariable("experienceId") UUID experienceId,
            @PathVariable("noteId") UUID noteId) {
        boolean found = noteAppService.delete(projectId, userFlowId, experienceId, noteId);

        HttpStatus status = (found ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(status);
    }

}
