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
package org.starchartlabs.river.main.webapp.server.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.starchartlabs.river.main.webapp.app.model.ContentTypes;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;

@Controller
public class RootRestServer {

    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.ROOT, produces = { ContentTypes.HTML })
    public String getHomePage() {
        // TODO ui - home page

        // The home page should contain:
        // A list of existing projects (GET on /projects)
        // A control to create a new project (POST on /projects, Request as specified in ProjectRequest class)
        // A control to delete a project (DELETE on URL on each project._meta.href)
        // A control to go to the detail page of each project (GET on URL on each project._meta.href)

        return "todo-ui";
    }

}
