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

import org.starchartlabs.river.main.webapp.domain.model.UserFlow;
import org.starchartlabs.river.main.webapp.domain.model.UserFlowFields;

//TODO romeara doc
public interface IUserFlowRepository {

    UserFlow create(UserFlowFields fields);

    Optional<UserFlow> get(UUID id);

    Collection<UserFlow> getByProject(UUID projectId);

    boolean delete(UserFlow userFlow);

}
