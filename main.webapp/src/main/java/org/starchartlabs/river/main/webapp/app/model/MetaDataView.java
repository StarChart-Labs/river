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
package org.starchartlabs.river.main.webapp.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO romeara doc, test
public class MetaDataView {

    @JsonProperty(value = "href", required = true)
    private final String href;

    @JsonProperty(value = "links", required = true)
    private final List<LinkView> links;

    public MetaDataView(String href) {
        this(href, Collections.emptyList());
    }

    public MetaDataView(String href, List<LinkView> links) {
        this.href = Objects.requireNonNull(href);
        this.links = Objects.requireNonNull(links);
    }

    public String getHref() {
        return href;
    }

    public List<LinkView> getLinks() {
        return links;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHref(),
                getLinks());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof MetaDataView) {
            MetaDataView compare = (MetaDataView) obj;

            result = Objects.equals(getHref(), compare.getHref())
                    && Objects.equals(getLinks(), compare.getLinks());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("href", getHref())
                .add("links", getLinks())
                .toString();
    }

}
