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
package me.champeau.jdoctor.builders;

import java.util.function.Supplier;

/**
 * Base interface for builders of {@link me.champeau.jdoctor.WithDescription}.
 *
 * Implementations are recommended to avoid eager resolution of suppliers
 * as in general, computing strings which may not be displayed to users
 * is expensive.
 *
 * @param <T> the type of the builder implementation
 */
public interface DescriptionBuilder<T extends DescriptionBuilder<T>> {

    /**
     * Sets the short description supplier.
     * @param shortDescription a short description
     * @return this builder
     */
    T withShortDescription(Supplier<String> shortDescription);

    /**
     * Sets the short description. If the description is computed,
     * it is recommended to call {@link #withShortDescription(Supplier)} instead.
     * @param description a short description string
     * @return this builder
     */
    default T withShortDescription(String description) {
        return withShortDescription(() -> description);
    }

    /**
     * Sets the long description supplier.
     * @param longDescription a longer description
     * @return this builder
     */
    T withLongDescription(Supplier<String> longDescription);

    /**
     * Sets the long description. If the description is computed,
     * it is recommended to call {@link #withLongDescription(Supplier)} instead.
     * @param description a long description string
     * @return this builder
     */
    default T withLongDescription(String description) {
        return withLongDescription(() -> description);
    }

}
