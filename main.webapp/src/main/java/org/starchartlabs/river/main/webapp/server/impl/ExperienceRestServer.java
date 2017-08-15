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
import org.starchartlabs.river.main.webapp.app.api.IExperienceAppService;
import org.starchartlabs.river.main.webapp.app.api.IMarkdownAppService;
import org.starchartlabs.river.main.webapp.app.model.ContentTypes;
import org.starchartlabs.river.main.webapp.app.model.ExperienceRequest;
import org.starchartlabs.river.main.webapp.app.model.ExperienceView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;

//TODO romeara doc test
@Controller
public class ExperienceRestServer {

    private final IExperienceAppService experienceAppService;

    private final IMarkdownAppService markdownAppService;

    public ExperienceRestServer(IExperienceAppService experienceAppService, IMarkdownAppService markdownAppService) {
        this.experienceAppService = Objects.requireNonNull(experienceAppService);
        this.markdownAppService = Objects.requireNonNull(markdownAppService);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = RequestPaths.EXPERIENCE_LIST,
    consumes = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<Void> createExperience(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId,
            @RequestBody ExperienceRequest request) {
        ExperienceView result = experienceAppService.create(userFlowId, request);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, result.getMetaData().getHref());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.EXPERIENCE_LIST,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<PageView<ExperienceView>> getExperiences(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId) {
        PageView<ExperienceView> result = experienceAppService.get(userFlowId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.EXPERIENCE,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<ExperienceView> getExperience(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId, @PathVariable("experienceId") UUID experienceId) {
        Optional<ExperienceView> result = experienceAppService.get(userFlowId, experienceId);

        return result.map(body -> new ResponseEntity<>(body, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, path = RequestPaths.EXPERIENCE)
    public ResponseEntity<Void> deleteExperience(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId, @PathVariable("experienceId") UUID experienceId) {
        boolean found = experienceAppService.delete(userFlowId, experienceId);

        HttpStatus status = (found ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(status);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.EXPERIENCE_CREATE,
    produces = { ContentTypes.HTML })
    public String getExperienceCreateHtml(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId) {
        // TODO ui - create experience page

        // The create experience page should contain:
        // A box for a user to enter a name. When completed, POST on userflow._meta.links[experiences], Request as
        // specified in ExperienceRequest class)

        return "todo-ui";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.EXPERIENCE,
    produces = { ContentTypes.HTML })
    public String getExperienceHtml(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId, @PathVariable("experienceId") UUID experienceId) {
        // TODO ui - experience page

        // Note - markdown in standard JSON-based response can be transform via markdownAppService for HTML display

        // The experience page should contain:
        // A list of existing notes (GET on experience._meta.links[notes])
        // A control to create a new note with type "CLEAR" or "CONFUSING" (POST on experience._meta.links[userflows],
        // Request as specified in
        // NoteRequest class)
        // A control to delete a note (DELETE on URL on each note._meta.href)
        // A control to complete the experience (note with type "BLOCKED" or "COMPLETED" (POST on
        // experience._meta.links[userflows], Request as specified in
        // NoteRequest class)

        return "todo-ui";
    }

}
