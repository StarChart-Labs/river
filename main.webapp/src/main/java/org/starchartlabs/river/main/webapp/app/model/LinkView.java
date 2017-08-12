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

import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//TODO romeara doc, test
public class LinkView {

    @JsonProperty(value = "rel", required = true)
    private final String rel;

    @JsonProperty(value = "href", required = true)
    private final String href;

    public LinkView(String rel, String href) {
        this.rel = Objects.requireNonNull(rel);
        this.href = Objects.requireNonNull(href);
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRel(),
                getHref());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof LinkView) {
            LinkView compare = (LinkView) obj;

            result = Objects.equals(getRel(), compare.getRel())
                    && Objects.equals(getHref(), compare.getHref());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("rel", getRel())
                .add("href", getHref())
                .toString();
    }

}
