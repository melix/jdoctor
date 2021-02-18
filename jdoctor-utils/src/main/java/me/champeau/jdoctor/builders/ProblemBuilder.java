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

/**
 * The generic interface of problem builders. It's actually rare that you'd want to directly implement
 * this interface. Instead, it is recommended to create dedicated builders, for each kind of problem,
 * so that the construction of the error message makes more sense.
 *
 * For example, if you have a category of problems related to user input validation, you might want
 * to have a ValidationProblem type and a builder of problems which knows about the specifics of
 * validation errors.
 *
 * @param <ID> the type of the problem ID
 * @param <SEVERITY> the type of the severity
 * @param <CONTEXT> the type of the problem context
 * @param <PAYLOAD> the type of the problem payload
 */
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
