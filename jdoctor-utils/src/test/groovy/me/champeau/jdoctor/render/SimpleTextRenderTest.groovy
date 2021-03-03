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

package me.champeau.jdoctor.render

import me.champeau.jdoctor.ProblemsVerifier
import me.champeau.jdoctor.TestProblemId
import me.champeau.jdoctor.TestSeverity
import me.champeau.jdoctor.builders.Builder
import me.champeau.jdoctor.builders.ProblemBuilder
import spock.lang.Specification

class SimpleTextRenderTest extends Specification implements ProblemsVerifier {
    def "can render a simple problem"() {
        given:
        def problem = Builder.build(
                ProblemBuilder.newBuilder(
                        TestProblemId.problem1,
                        TestSeverity.high,
                        context("Hawaiian pizza")
                )
                        .withShortDescription("pineapple on pizza isn't allowed")
                        .because("the Italian cuisine should be respected")
                        .withLongDescription("Pineapple on pizza would put your relationship with folks you respect at risk.")
                        .documentedAt("https://www.bbc.co.uk/bitesize/articles/z2vftrd")
                        .addSolution { it.withShortDescription("eat pineapple for desert") }
        )

        when:
        def rendered = SimpleTextRenderer.render(problem)

        then:
        rendered == """A problem happened: pineapple on pizza isn't allowed

Where? : Hawaiian pizza

Why? : the Italian cuisine should be respected

Details: Pineapple on pizza would put your relationship with folks you respect at risk.

Possible solution: eat pineapple for desert

You can learn more about this problem at https://www.bbc.co.uk/bitesize/articles/z2vftrd"""
    }

    def "can render a simple problem with multiple solutions"() {
        given:
        def problem = Builder.build(
                ProblemBuilder.newBuilder(
                        TestProblemId.problem1,
                        TestSeverity.high,
                        context("Hawaiian pizza")
                )
                        .withShortDescription("pineapple on pizza isn't allowed")
                        .because("the Italian cuisine should be respected")
                        .withLongDescription("Pineapple on pizza would put your relationship with folks you respect at risk.")
                        .documentedAt("https://www.bbc.co.uk/bitesize/articles/z2vftrd")
                        .addSolution { it.withShortDescription("eat pineapple for desert") }
                        .addSolution { it.withShortDescription("stop adding pineapple to pizza") }
        )

        when:
        def rendered = SimpleTextRenderer.render("The cooking police arrested you", problem)

        then:
        rendered == """The cooking police arrested you: pineapple on pizza isn't allowed

Where? : Hawaiian pizza

Why? : the Italian cuisine should be respected

Details: Pineapple on pizza would put your relationship with folks you respect at risk.

Possible solutions:
    - eat pineapple for desert
    - stop adding pineapple to pizza

You can learn more about this problem at https://www.bbc.co.uk/bitesize/articles/z2vftrd"""
    }
}
