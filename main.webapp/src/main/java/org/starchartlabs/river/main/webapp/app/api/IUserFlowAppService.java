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
import org.starchartlabs.river.main.webapp.app.model.UserFlowRequest;
import org.starchartlabs.river.main.webapp.app.model.UserFlowView;

//TODO romeara doc
public interface IUserFlowAppService {

    UserFlowView create(UUID projectId, UserFlowRequest request);

    Optional<UserFlowView> get(UUID projectId, UUID id);

    PageView<UserFlowView> get(UUID projectId);

    boolean delete(UUID projectId, UUID id);

}
