/*
 * Copyright (c) Aug 13, 2017 StarChart Labs Authors.
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

import org.starchartlabs.river.main.webapp.app.api.IExperienceAppService;
import org.starchartlabs.river.main.webapp.app.model.ExperienceRequest;
import org.starchartlabs.river.main.webapp.app.model.ExperienceView;
import org.starchartlabs.river.main.webapp.app.model.MetaDataView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.domain.model.Experience;
import org.starchartlabs.river.main.webapp.domain.model.ExperienceFields;
import org.starchartlabs.river.main.webapp.domain.model.UserFlow;
import org.starchartlabs.river.main.webapp.repository.api.IExperienceRepository;
import org.starchartlabs.river.main.webapp.repository.api.IUserFlowRepository;

//TODO romeara doc test
public class ExperienceAppService implements IExperienceAppService {

    private final IUserFlowRepository userFlowRepository;

    private final IExperienceRepository experienceRepository;

    public ExperienceAppService(IUserFlowRepository userFlowRepository, IExperienceRepository experienceRepository) {
        this.userFlowRepository = Objects.requireNonNull(userFlowRepository);
        this.experienceRepository = Objects.requireNonNull(experienceRepository);
    }

    @Override
    public ExperienceView create(UUID userFlowId, ExperienceRequest request) {
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(request);

        UserFlow userFlow = userFlowRepository.get(userFlowId)
                .orElseThrow(() -> new IllegalArgumentException("User flow must exist to operate on experiences"));

        Experience experience = experienceRepository.create(toFields(userFlowId, request));
        return toView(userFlow, experience);
    }

    @Override
    public Optional<ExperienceView> get(UUID userFlowId, UUID id) {
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(id);

        UserFlow userFlow = userFlowRepository.get(userFlowId)
                .orElseThrow(() -> new IllegalArgumentException("User flow must exist to operate on experiences"));

        return experienceRepository.get(id)
                .map(input -> toView(userFlow, input));
    }

    @Override
    public PageView<ExperienceView> get(UUID userFlowId) {
        Objects.requireNonNull(userFlowId);

        UserFlow userFlow = userFlowRepository.get(userFlowId)
                .orElseThrow(() -> new IllegalArgumentException("User flow must exist to operate on experiences"));

        MetaDataView metaData = new MetaDataView(
                RequestPaths.getExperienceListUrl(userFlow.getProjectId(), userFlowId));

        List<ExperienceView> items = experienceRepository.getByUserFlowId(userFlowId).stream()
                .map(input -> toView(userFlow, input))
                .collect(Collectors.toList());

        return new PageView<>(items, metaData);
    }

    @Override
    public boolean delete(UUID userFlowId, UUID id) {
        Objects.requireNonNull(userFlowId);
        Objects.requireNonNull(id);

        return experienceRepository.get(id)
                .map(experienceRepository::delete)
                .orElse(false);
    }

    // TODO romeara - move to dedicated converters
    private ExperienceFields toFields(UUID userFlowId, ExperienceRequest request) {
        Objects.requireNonNull(request);

        return new ExperienceFields(userFlowId,
                request.getUser());
    }

    private ExperienceView toView(UserFlow userFlow, Experience experience) {
        Objects.requireNonNull(experience);

        MetaDataView metaData = new MetaDataView(
                RequestPaths.getExperienceUrl(userFlow.getProjectId(), experience.getUserFlowId(),
                        experience.getId()));

        return new ExperienceView(
                experience.getUser(),
                metaData);
    }

}
