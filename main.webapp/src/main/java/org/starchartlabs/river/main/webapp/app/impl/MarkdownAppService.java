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
package org.starchartlabs.river.main.webapp.app.impl;

import java.util.Objects;

import org.starchartlabs.river.main.webapp.app.api.IMarkdownAppService;

import com.github.rjeschke.txtmark.Processor;

//TODO romeara doc test
public class MarkdownAppService implements IMarkdownAppService {

    @Override
    public String toHtml(String markdown) {
        Objects.requireNonNull(markdown);

        return Processor.process(markdown)
                .replaceAll("<script>", "&lt;script$gt;")
                .replaceAll("</script>", "&lt;/script$gt;");
    }

}
