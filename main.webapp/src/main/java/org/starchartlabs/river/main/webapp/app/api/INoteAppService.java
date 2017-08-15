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
package org.starchartlabs.river.main.webapp.app.api;

import java.util.Optional;
import java.util.UUID;

import org.starchartlabs.river.main.webapp.app.model.NoteRequest;
import org.starchartlabs.river.main.webapp.app.model.NoteView;
import org.starchartlabs.river.main.webapp.app.model.PageView;

//TODO romeara doc
public interface INoteAppService {

    NoteView create(UUID projectId, UUID userFlowId, UUID experienceId, NoteRequest request);

    Optional<NoteView> get(UUID projectId, UUID userFlowId, UUID experienceId, UUID id);

    PageView<NoteView> get(UUID projectId, UUID userFlowId, UUID experienceId);

    boolean delete(UUID projectId, UUID userFlowId, UUID experienceId, UUID id);

}
