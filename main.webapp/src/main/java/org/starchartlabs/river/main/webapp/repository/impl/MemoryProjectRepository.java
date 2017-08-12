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
package org.starchartlabs.river.main.webapp.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.domain.model.Project;
import org.starchartlabs.river.main.webapp.domain.model.ProjectFields;
import org.starchartlabs.river.main.webapp.repository.api.IProjectRepository;

//TODO romeara replace with persistence store
public class MemoryProjectRepository implements IProjectRepository {

    private Map<UUID, Project> projects = new HashMap<>();

    @Override
    public Project create(ProjectFields fields) {
        Objects.requireNonNull(fields);

        UUID id = UUID.randomUUID();
        Project project = new Project(id, fields);

        projects.put(project.getId(), project);

        return project;
    }

    @Override
    public Optional<Project> get(UUID id) {
        Objects.requireNonNull(id);

        return Optional.ofNullable(projects.get(id));
    }

    @Override
    public Collection<Project> get() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public boolean delete(Project project) {
        return (projects.remove(project.getId()) != null);
    }

}
