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
package org.starchartlabs.river.main.webapp.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.starchartlabs.river.main.webapp.app.api.IExperienceAppService;
import org.starchartlabs.river.main.webapp.app.api.IMarkdownAppService;
import org.starchartlabs.river.main.webapp.app.api.INoteAppService;
import org.starchartlabs.river.main.webapp.app.api.IProjectAppService;
import org.starchartlabs.river.main.webapp.app.api.IUserFlowAppService;
import org.starchartlabs.river.main.webapp.server.impl.ExperienceRestServer;
import org.starchartlabs.river.main.webapp.server.impl.NoteRestServer;
import org.starchartlabs.river.main.webapp.server.impl.ProjectRestServer;
import org.starchartlabs.river.main.webapp.server.impl.RootRestServer;
import org.starchartlabs.river.main.webapp.server.impl.UserFlowRestServer;

//TODO romeara doc
@Configuration
public class ServerConfiguration {

    @Bean
    public RootRestServer rootRestServer() {
        return new RootRestServer();
    }

    @Bean
    public ProjectRestServer projectRestServer(IProjectAppService projectAppService) {
        return new ProjectRestServer(projectAppService);
    }

    @Bean
    public UserFlowRestServer userFlowRestServer(IUserFlowAppService userFlowAppService,
            IMarkdownAppService markdownAppService) {
        return new UserFlowRestServer(userFlowAppService, markdownAppService);
    }

    @Bean
    public ExperienceRestServer experienceRestServer(IExperienceAppService experienceAppService,
            IMarkdownAppService markdownAppService) {
        return new ExperienceRestServer(experienceAppService, markdownAppService);
    }

    @Bean
    public NoteRestServer noteRestServer(INoteAppService noteAppService) {
        return new NoteRestServer(noteAppService);
    }

}
