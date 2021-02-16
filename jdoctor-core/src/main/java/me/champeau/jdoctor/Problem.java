/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.champeau.jdoctor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Problem<ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT, PAYLOAD> extends WithId<ID>, WithDescription, WithDocumentationLink {
    /**
     * @return How severe is the problem
     */
    SEVERITY getSeverity();

    /**
     * Where did the problem surface?
     * @return can refer to a source file, a plugin, or any place which lets the user
     * understand where a problem happened
     */
    CONTEXT getWhere();

    /**
     * An optional payload attached to this problem, which may be used
     * to carry information about the problem before it is actually
     * reported to the user. This can typically be an exception which
     * caused the problem.
     *
     * @return this problem payload
     */
    default Optional<PAYLOAD> getPayload() {
        return Optional.empty();
    }

    /**
     * Should return a short, human-understandable description of the problem.
     * By default, this returns the short description.
     * @return what problem happened
     */
    default String getWhat() {
        return getShortDescription();
    }

    /**
     * If possible, a problem should tell why it happened
     * @return a description of the why the problem happened
     */
    default Optional<String> getWhy() {
        return Optional.empty();
    }

    /**
     * A list of solutions, or potential solutions to the reported problem.
     * @return a list of solutions. Shouldn't be null.
     */
    default List<Solution> getPossibleSolutions() {
        return Collections.emptyList();
    }

    /**
     * If this problem is a consequence of another problem, return the
     * originating problem.
     * @return a problem which caused the current one to show up, if any.
     */
    default Optional<Problem<?, ?, ?, ?>> getCause() {
        return Optional.empty();
    }
}
