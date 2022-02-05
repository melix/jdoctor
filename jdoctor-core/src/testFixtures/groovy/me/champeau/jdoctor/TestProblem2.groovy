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

package me.champeau.jdoctor

import groovy.transform.CompileStatic

import java.util.function.Supplier

@CompileStatic
class TestProblem2 extends BaseProblem<Integer, TestSeverity, TestContext> {
    protected TestProblem2(Integer testProblemId, TestSeverity testSeverity, TestContext context, Supplier<String> shortDescription, Supplier<String> longDescription, Supplier<String> reason, Supplier<String> docUrl, List<Supplier<Solution>> solutions) {
        super(testProblemId, testSeverity, context, shortDescription, longDescription, reason, docUrl, solutions)
    }
}
