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

import me.champeau.jdoctor.Solution;
import me.champeau.jdoctor.StandardSeverity;
import me.champeau.jdoctor.builders.internal.BuilderProblem;
import me.champeau.jdoctor.exceptions.ExceptionUtils;

import java.util.function.Supplier;

/**
 * Builders which want to hide their build() method should implement
 * both this interface and the {@link Supplier} interface.
 *
 * Why hiding the build() method? If you follow the configuration
 * pattern, which is to provide a builder via a {@link java.util.function.Consumer},
 * then it's not a good idea to expose the build method directly as
 * a user may call it when it should be the responsibility of the
 * code which creates the builder to finalize (and build).
 */
public interface Builder {
    /**
     * Builders provided by the `jdoctor-builders` module automatically implement
     * the {@link Supplier} interface, but do not expose them so that the responsibility
     * of consuming the generated error message is delegated to the consumer itself.
     *
     * In other words, the builders allow describing errors, but don't care _when_ they
     * are going to be consumed. Therefore the "build" method shouldn't leak.
     *
     * This method is just an utility class to help from the consumer side.
     *
     * @param builder a builder
     * @param <T> the expected return type of the builder
     * @return the solution created by the builder, if supported
     */
    static <T> T build(Builder builder) {
        if (builder instanceof Supplier) {
            //noinspection unchecked
            return (T) ((Supplier<Solution>) builder).get();
        }
        throw ExceptionUtils.asRuntimeException(build(
                ProblemBuilder.newBuilder(BuilderProblem.UNEXPECTED_TYPE_OF_BUILDER, StandardSeverity.ERROR, SolutionBuilder.class)
                        .withShortDescription(() -> "Unexpected builder type " + builder.getClass())
                        .withLongDescription("The SolutionBuilder#build method only supports builders created via the solution builder itself, or builders implementing the Supplier interface")
                        .addSolution(solution -> solution.withShortDescription("Call this method with a builder created via the SolutionBuilder only")))
        );
    }
}
