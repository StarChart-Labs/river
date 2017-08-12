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

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//TODO romeara doc
//TODO romeara make this actually more than a placeholder
public class PageView<T> {

    @JsonProperty(value = "items", required = true)
    private final List<T> items;

    @JsonProperty(value = "_meta", required = true)
    private final MetaDataView metaData;

    public PageView(List<T> items, MetaDataView metaData) {
        this.items = Objects.requireNonNull(items);
        this.metaData = Objects.requireNonNull(metaData);
    }

    public List<T> getItems() {
        return items;
    }

    public MetaDataView getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof PageView) {
            PageView<?> compare = (PageView<?>) obj;

            result = Objects.equals(getItems(), compare.getItems())
                    && Objects.equals(getMetaData(), compare.getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("items", getItems())
                .add("metaData", getMetaData())
                .toString();
    }

}
