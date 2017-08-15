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
import org.starchartlabs.river.main.webapp.app.api.IProjectAppService;
import org.starchartlabs.river.main.webapp.app.model.ContentTypes;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.ProjectRequest;
import org.starchartlabs.river.main.webapp.app.model.ProjectView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;

//TODO romeara doc, test
@Controller
public class ProjectRestServer {

    private final IProjectAppService projectAppService;

    public ProjectRestServer(IProjectAppService projectAppService) {
        this.projectAppService = Objects.requireNonNull(projectAppService);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = RequestPaths.PROJECT_LIST,
    consumes = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<Void> createProject(@RequestBody ProjectRequest request) {
        ProjectView result = projectAppService.create(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, result.getMetaData().getHref());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.PROJECT_LIST,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<PageView<ProjectView>> getProjects() {
        PageView<ProjectView> result = projectAppService.get();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.PROJECT,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<ProjectView> getProject(@PathVariable("projectId") UUID projectId) {
        Optional<ProjectView> result = projectAppService.get(projectId);

        return result.map(body -> new ResponseEntity<>(body, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, path = RequestPaths.PROJECT)
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") UUID projectId) {
        boolean found = projectAppService.delete(projectId);

        HttpStatus status = (found ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(status);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.PROJECT,
    produces = { ContentTypes.HTML })
    public String getProjectHtml(@PathVariable("projectId") UUID projectId) {
        // TODO ui - project page

        // The project page should contain:
        // A list of existing user flows (GET on project._meta.links[userflows])
        // A control to create a new user flow (POST on project._meta.links[userflows], Request as specified in
        // UserFlowRequest class)
        // A control to delete a user flow (DELETE on URL on each userflow._meta.href)
        // A control to go to the detail page of each user flow (GET on URL on each userflow._meta.href)

        return "todo-ui";
    }
}
