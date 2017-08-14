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
package org.starchartlabs.river.main.webapp.app.api;

import java.util.Optional;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.app.model.ExperienceRequest;
import org.starchartlabs.river.main.webapp.app.model.ExperienceView;
import org.starchartlabs.river.main.webapp.app.model.PageView;

//TODO romeara doc
public interface IExperienceAppService {

    ExperienceView create(UUID userFlowId, ExperienceRequest request);

    Optional<ExperienceView> get(UUID userFlowId, UUID id);

    PageView<ExperienceView> get(UUID userFlowId);

    boolean delete(UUID userFlowId, UUID id);

}
