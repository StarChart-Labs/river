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
import org.starchartlabs.river.main.webapp.app.api.IMarkdownAppService;
import org.starchartlabs.river.main.webapp.app.api.IUserFlowAppService;
import org.starchartlabs.river.main.webapp.app.model.ContentTypes;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.app.model.UserFlowRequest;
import org.starchartlabs.river.main.webapp.app.model.UserFlowView;

//TODO romeara doc test, 404 when project doesnt exist
@Controller
public class UserFlowRestServer {

    private final IUserFlowAppService userFlowAppService;

    private final IMarkdownAppService markdownAppService;

    public UserFlowRestServer(IUserFlowAppService userFlowAppService, IMarkdownAppService markdownAppService) {
        this.userFlowAppService = Objects.requireNonNull(userFlowAppService);
        this.markdownAppService = Objects.requireNonNull(markdownAppService);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = RequestPaths.USER_FLOW_LIST,
    consumes = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<Void> createUserFlow(@PathVariable("projectId") UUID projectId,
            @RequestBody UserFlowRequest request) {
        UserFlowView result = userFlowAppService.create(projectId, request);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, result.getMetaData().getHref());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.USER_FLOW_LIST,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<PageView<UserFlowView>> getUserFlows(@PathVariable("projectId") UUID projectId) {
        PageView<UserFlowView> result = userFlowAppService.get(projectId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.USER_FLOW,
    produces = { ContentTypes.JSON, ContentTypes.STARCHART_V1 })
    public ResponseEntity<UserFlowView> getUserFlow(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId) {
        Optional<UserFlowView> result = userFlowAppService.get(projectId, userFlowId);

        return result.map(body -> new ResponseEntity<>(body, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, path = RequestPaths.USER_FLOW)
    public ResponseEntity<Void> deleteUserFlow(@PathVariable("projectId") UUID projectId,
            @PathVariable("userFlowId") UUID userFlowId) {
        boolean found = userFlowAppService.delete(projectId, userFlowId);

        HttpStatus status = (found ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(status);
    }

}
