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

import me.champeau.jdoctor.BaseProblem;
import me.champeau.jdoctor.Problem;
import me.champeau.jdoctor.Solution;
import me.champeau.jdoctor.builders.Builder;
import me.champeau.jdoctor.builders.ProblemBuilder;
import me.champeau.jdoctor.builders.SolutionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultProblemBuilder<ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT>
        extends AbstractDescribingBuilder<ProblemBuilder<ID, SEVERITY, CONTEXT>>
        implements ProblemBuilder<ID, SEVERITY, CONTEXT>, Supplier<Problem<ID, SEVERITY, CONTEXT>> {

    private final ID id;
    private final SEVERITY severity;
    private final CONTEXT context;
    private Supplier<String> reason;
    private final List<Supplier<Solution>> solutions = new ArrayList<>();

    public DefaultProblemBuilder(ID id, SEVERITY severity, CONTEXT context) {
        this.id = Objects.requireNonNull(id, "problem id must not be null");
        this.severity = Objects.requireNonNull(severity, "severity must not be null");
        this.context = Objects.requireNonNull(context, "context must not be null");
    }


    public Problem<ID, SEVERITY, CONTEXT> get() {
        return new BaseProblem<>(
                id,
                severity,
                context,
                shortDescription,
                longDescription,
                reason,
                documentationLink,
                solutions
        );
    }

    @Override
    public ProblemBuilder<ID, SEVERITY, CONTEXT> addSolution(Consumer<? super SolutionBuilder> solutionSpec) {
        solutions.add(() -> {
            SolutionBuilder builder = SolutionBuilder.newSolution();
            solutionSpec.accept(builder);
            return Builder.build(builder);
        });
        return this;
    }

    @Override
    public ProblemBuilder<ID, SEVERITY, CONTEXT> because(Supplier<String> reason) {
        this.reason = Objects.requireNonNull(reason, "reason supplier must not be null");
        return this;
    }
}
