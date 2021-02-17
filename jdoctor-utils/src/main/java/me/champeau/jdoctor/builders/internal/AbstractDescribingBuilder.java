/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.champeau.jdoctor.builders.internal;

import me.champeau.jdoctor.builders.DocumentedBuilder;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class AbstractDescribingBuilder<T extends DocumentedBuilder<T>> implements DocumentedBuilder<T> {
    protected Supplier<String> shortDescription;
    protected Supplier<String> longDescription = () -> null;
    protected Supplier<String> documentationLink = () -> null;

    public T withShortDescription(Supplier<String> shortDescription) {
        this.shortDescription = Objects.requireNonNull(shortDescription, "short description supplier must not be null");
        return (T) this;
    }

    public T withLongDescription(Supplier<String> longDescription) {
        this.longDescription = Objects.requireNonNull(longDescription, "long description supplier must not be null");
        return (T) this;
    }

    public T documentedAt(Supplier<String> documentationLink) {
        this.documentationLink = Objects.requireNonNull(documentationLink, "documentation link supplier must not be null");
        return (T) this;
    }
}
