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

import org.starchartlabs.river.main.webapp.app.api.IProjectAppService;
import org.starchartlabs.river.main.webapp.app.model.LinkView;
import org.starchartlabs.river.main.webapp.app.model.MetaDataView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.ProjectRequest;
import org.starchartlabs.river.main.webapp.app.model.ProjectView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.domain.model.Project;
import org.starchartlabs.river.main.webapp.domain.model.ProjectFields;
import org.starchartlabs.river.main.webapp.repository.api.IProjectRepository;

//TODO romeara doc, test
public class ProjectAppService implements IProjectAppService {

    private final IProjectRepository projectRepository;

    public ProjectAppService(IProjectRepository projectRepository) {
        this.projectRepository = Objects.requireNonNull(projectRepository);
    }

    @Override
    public ProjectView create(ProjectRequest request) {
        Objects.requireNonNull(request);

        Project project = projectRepository.create(toFields(request));

        return toView(project);
    }

    @Override
    public Optional<ProjectView> get(UUID id) {
        Objects.requireNonNull(id);

        return projectRepository.get(id)
                .map(this::toView);
    }

    @Override
    public PageView<ProjectView> get() {
        MetaDataView metaData = new MetaDataView(RequestPaths.getProjectListUrl());

        List<ProjectView> items = projectRepository.get().stream()
                .map(this::toView)
                .collect(Collectors.toList());

        return new PageView<>(items, metaData);
    }

    @Override
    public boolean delete(UUID id) {
        Objects.requireNonNull(id);

        return projectRepository.get(id)
                .map(projectRepository::delete)
                .orElse(false);
    }

    // TODO romeara - move to dedicated converters
    private ProjectFields toFields(ProjectRequest request) {
        Objects.requireNonNull(request);

        return new ProjectFields(request.getName());
    }

    private ProjectView toView(Project project) {
        Objects.requireNonNull(project);

        LinkView userFlowLink = new LinkView("userflows", RequestPaths.getUserFlowListUrl(project.getId()));

        MetaDataView metaData = new MetaDataView(RequestPaths.getProjectUrl(project.getId()),
                Arrays.asList(userFlowLink));

        return new ProjectView(
                project.getName(),
                metaData);
    }

}
