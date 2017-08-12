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
package org.starchartlabs.river.main.webapp.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.starchartlabs.river.main.webapp.repository.api.IProjectRepository;
import org.starchartlabs.river.main.webapp.repository.impl.MemoryProjectRepository;

//TODO romeara doc
@Configuration
public class RepositoryConfiguration {

    @Bean
    public IProjectRepository projectRepository() {
        return new MemoryProjectRepository();
    }

}
