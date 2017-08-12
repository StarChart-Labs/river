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
package org.starchartlabs.river.main.webapp.app.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

//TODO romeara doc, test
public class UriBuilders {

    /**
     * Prevent instantiation of utility class
     */
    private UriBuilders() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate instance of utility class '" + getClass().getName() + "'");
    }

    public static UriComponentsBuilder getLocalServerUriBuilder() {
        UriComponentsBuilder result = null;

        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes) {
            result = ServletUriComponentsBuilder.fromCurrentRequestUri();
        } else {
            // romeara - This occurs when there is no current, usable request - in this case, we generate relative URLs
            result = UriComponentsBuilder.newInstance();
        }

        result = result.replacePath(null);

        return result;
    }
}
