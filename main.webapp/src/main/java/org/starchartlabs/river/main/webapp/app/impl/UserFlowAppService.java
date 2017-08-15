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
package org.starchartlabs.river.main.webapp.app.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.starchartlabs.river.main.webapp.app.api.IUserFlowAppService;
import org.starchartlabs.river.main.webapp.app.model.LinkView;
import org.starchartlabs.river.main.webapp.app.model.MetaDataView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.app.model.UserFlowRequest;
import org.starchartlabs.river.main.webapp.app.model.UserFlowView;
import org.starchartlabs.river.main.webapp.domain.model.UserFlow;
import org.starchartlabs.river.main.webapp.domain.model.UserFlowFields;
import org.starchartlabs.river.main.webapp.repository.api.IUserFlowRepository;

//TODO romeara doc test
public class UserFlowAppService implements IUserFlowAppService {

    private final IUserFlowRepository userFlowRepository;

    public UserFlowAppService(IUserFlowRepository userFlowRepository) {
        this.userFlowRepository = Objects.requireNonNull(userFlowRepository);
    }

    @Override
    public UserFlowView create(UUID projectId, UserFlowRequest request) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(request);

        UserFlow userFlow = userFlowRepository.create(toFields(projectId, request));
        return toView(userFlow);
    }

    @Override
    public Optional<UserFlowView> get(UUID projectId, UUID id) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(id);

        return userFlowRepository.get(id)
                .map(this::toView);
    }

    @Override
    public PageView<UserFlowView> get(UUID projectId) {
        Objects.requireNonNull(projectId);

        MetaDataView metaData = new MetaDataView(RequestPaths.getUserFlowListUrl(projectId));

        List<UserFlowView> items = userFlowRepository.getByProject(projectId).stream()
                .map(this::toView)
                .collect(Collectors.toList());

        return new PageView<>(items, metaData);
    }

    @Override
    public boolean delete(UUID projectId, UUID id) {
        return userFlowRepository.get(id)
                .map(userFlowRepository::delete)
                .orElse(false);
    }

    // TODO romeara - move to dedicated converters
    private UserFlowFields toFields(UUID projectId, UserFlowRequest request) {
        Objects.requireNonNull(request);

        return new UserFlowFields(projectId,
                request.getName(),
                request.getSetupMarkdown(),
                request.getGoalMarkdown());
    }

    private UserFlowView toView(UserFlow userFlow) {
        Objects.requireNonNull(userFlow);

        LinkView experiencesLink = new LinkView("experiences",
                RequestPaths.getExperienceListUrl(userFlow.getProjectId(), userFlow.getId()));

        MetaDataView metaData = new MetaDataView(
                RequestPaths.getUserFlowUrl(userFlow.getProjectId(), userFlow.getId()),
                Arrays.asList(experiencesLink));

        return new UserFlowView(
                userFlow.getName(),
                userFlow.getSetupMarkdown(),
                userFlow.getGoalMarkdown(),
                metaData);
    }

}
