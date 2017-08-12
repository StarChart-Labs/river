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
package org.starchartlabs.river.main.webapp.repository.api;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.domain.model.Project;
import org.starchartlabs.river.main.webapp.domain.model.ProjectFields;

//TODO romeara doc
public interface IProjectRepository {

    Project create(ProjectFields fields);

    Optional<Project> update(UUID id, ProjectFields fields);

    Optional<Project> get(UUID id);

    Collection<Project> get();

    boolean delete(Project project);

}
