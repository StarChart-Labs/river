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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.starchartlabs.river.main.webapp.domain.model.UserFlow;
import org.starchartlabs.river.main.webapp.domain.model.UserFlowFields;
import org.starchartlabs.river.main.webapp.repository.api.IUserFlowRepository;

//TODO romeara replace with persistence store
public class MemoryUserFlowRepository implements IUserFlowRepository {

    private final Map<UUID, UserFlow> userFlows = new HashMap<>();

    @Override
    public UserFlow create(UserFlowFields fields) {
        Objects.requireNonNull(fields);

        UUID id = UUID.randomUUID();

        UserFlow result = new UserFlow(id, fields);

        userFlows.put(result.getId(), result);

        return result;
    }

    @Override
    public Optional<UserFlow> get(UUID id) {
        Objects.requireNonNull(id);

        return Optional.ofNullable(userFlows.get(id));
    }

    @Override
    public Collection<UserFlow> getByProject(UUID projectId) {
        Objects.requireNonNull(projectId);

        return userFlows.values().stream()
                .filter(input -> Objects.equals(projectId, input.getProjectId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(UserFlow userFlow) {
        return (userFlows.remove(userFlow) != null);
    }

}
