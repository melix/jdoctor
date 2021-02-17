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

import me.champeau.jdoctor.builders.internal.DefaultProblemBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ProblemBuilder<ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT, PAYLOAD>
        extends DescriptionBuilder<ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD>>, DocumentedBuilder<ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD>>, Builder {
    static <ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT, PAYLOAD> ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD> newBuilder(
            ID problemId,
            SEVERITY severity,
            CONTEXT context,
            PAYLOAD payload
    ) {
        return new DefaultProblemBuilder<>(problemId, severity, context, payload);
    }

    static <ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT> ProblemBuilder<ID, SEVERITY, CONTEXT, Void> newBuilder(
            ID problemId,
            SEVERITY severity,
            CONTEXT context
    ) {
        return newBuilder(problemId, severity, context, null);
    }

    ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD> addSolution(Consumer<? super SolutionBuilder> solutionSpec);

    ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD> because(Supplier<String> reason);

    default ProblemBuilder<ID, SEVERITY, CONTEXT, PAYLOAD> because(String reason) {
        return because(() -> reason);
    }
}
