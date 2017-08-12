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
package org.starchartlabs.river.main.webapp.app.api;

import java.util.Optional;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.ProjectRequest;
import org.starchartlabs.river.main.webapp.app.model.ProjectView;

//TODO romeara doc
public interface IProjectAppService {

    ProjectView create(ProjectRequest request);

    Optional<ProjectView> get(UUID id);

    PageView<ProjectView> get();

    boolean delete(UUID id);

}
