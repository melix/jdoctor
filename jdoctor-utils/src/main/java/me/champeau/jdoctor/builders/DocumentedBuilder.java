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
 * Interface for builders of {@link me.champeau.jdoctor.WithDocumentationLink}.
 *
 * @param <T> the type of the builder
 */
public interface DocumentedBuilder<T extends DocumentedBuilder<T>> {
    /**
     * Defines the supplier of documentation link.
     * @param link the supplier of documentation link
     * @return this builder
     */
    T documentedAt(Supplier<String> link);

    /**
     * Defines the supplier of documentation link. If the link is
     * expensive to compute, it's recommended to use the {@link #documentedAt(Supplier)}
     * method instead.
     *
     * @param link the supplier of documentation link
     * @return this builder
     */
    default T documentedAt(String link) {
        return documentedAt(() -> link);
    }
}
