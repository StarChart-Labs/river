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
package org.starchartlabs.river.main.webapp.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.starchartlabs.river.main.webapp.domain.model.Experience;
import org.starchartlabs.river.main.webapp.domain.model.ExperienceFields;
import org.starchartlabs.river.main.webapp.repository.api.IExperienceRepository;

//TODO romeara replace with actual persistence store
public class MemoryExperienceRepository implements IExperienceRepository {

    private final Map<UUID, Experience> experiences = new HashMap<>();

    @Override
    public Experience create(ExperienceFields fields) {
        Objects.requireNonNull(fields);

        UUID id = UUID.randomUUID();

        Experience result = new Experience(id, fields);

        experiences.put(result.getId(), result);

        return result;
    }

    @Override
    public Optional<Experience> get(UUID id) {
        Objects.requireNonNull(id);

        return Optional.ofNullable(experiences.get(id));
    }

    @Override
    public Collection<Experience> getByUserFlowId(UUID userFlowId) {
        Objects.requireNonNull(userFlowId);

        return experiences.values().stream()
                .filter(input -> Objects.equals(userFlowId, input.getUserFlowId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Experience experience) {
        return (experiences.remove(experience) != null);
    }

}
