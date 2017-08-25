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
package org.starchartlabs.river.main.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.starchartlabs.river.main.webapp.app.config.AppServiceConfiguration;
import org.starchartlabs.river.main.webapp.app.config.ThymeleafConfiguration;
import org.starchartlabs.river.main.webapp.repository.config.RepositoryConfiguration;
import org.starchartlabs.river.main.webapp.server.config.ServerConfiguration;

//TODO romeara doc
@SpringBootApplication(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@Import({ RepositoryConfiguration.class,
    AppServiceConfiguration.class,
    ServerConfiguration.class,
    ThymeleafConfiguration.class})
public class River {

    public static void main(String[] args) {
        SpringApplication.run(River.class, args);
    }

}
