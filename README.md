# JDoctor, a Java library for good help messages

[![Build](https://github.com/melix/jdoctor/actions/workflows/on-pr.yml/badge.svg)](https://github.com/melix/jdoctor/actions/workflows/on-pr.yml)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/melix/jdoctor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/melix/jdoctor/context:java)

Designing good help messages is hard. 
In Java, most often, developers just rely on throwing exceptions with a small text.
In best cases, the error message is sufficiently understandable, but there are lots of cases where it's inefficient.

A good error message should describe:

- _how severe_ the problem is (can this be safely ignored?)
- _what_ problem was encountered. This is usually where developers stop. For example, "foo is null"
- _where_ the problem was encountered (in which context, e.g what file being compiled, what line number, ...)
- _why_ the problem happened

and eventually, a set of possible solutions to the problem.

Exceptions are not good at solving this, because an exception describes a failure in the program execution flow.
Stack traces are good for debugging, but it's rare that a user cares _where in your program_ a problem occured: most likely what they are interested in is _how they can solve the problem_.

As a consequence, JDoctor provides a library to make it easier to design good error messages, by modeling them properly.

This library is currently in experimental stages, feedback is welcome.

### Example

This is an example of how you would construct a new problem descriptor using the convenience API:

```java
ProblemBuilder.newBuilder(TasteProblem.INVALID_INGREDIENT, Severity.HIGH, "Hawaiian pizza")
    .withShortDescription("pineapple on pizza isn't allowed")
    .because("the Italian cuisine should be respected")
    .withLongDescription("Pineapple on pizza would put your relationship with folks you respect at risk.")
    .documentedAt("https://www.bbc.co.uk/bitesize/articles/z2vftrd")
    .addSolution(s -> s.withShortDescription("eat pineapple for desert"))
    .addSolution(s -> s.withShortDescription("stop adding pineapple to pizza"))
```

In practice, we encourage users to implement their own implementation of the `Problem` interface (for example extending the `BaseProblem` class) and come with topical builders which are specific to a category of problems.

For example:

```java
problemRecorder.recordNewProblem(problem ->
    problem.forType(classWithAnnotationAttached)
        .reportAs(ERROR)
        .withId(ValidationProblemId.INVALID_USE_OF_CACHEABLE_TRANSFORM_ANNOTATION)
        .withDescription(() -> String.format("Using %s here is incorrect", getAnnotationType().getSimpleName()))
        .happensBecause(() -> String.format("This annotation only makes sense on %s types", TransformAction.class.getSimpleName()))
        .documentedAt("validation_problems", "invalid_use_of_cacheable_transform_annotation")
        .addPossibleSolution("Remove the annotation"))
```

Then you might wonder how this should be rendered.
As always, the answer is "it depends".
`jdoctor` is agnostic with regards to rendering.
You will not render a message the same way if you have a CLI, a rich interface or an HTML report.
In short: rendering is the responsibility of the _consumer_ of the problems.

For example, you might want to collect multiple problems, sort them by category, then render a short message and tell that the full explanation is available in a report.

However, for convenience, `jdoctor-utils` provides a simple, simplistic renderer.
Here's an example of output for the first example:

```
The cooking police arrested you: pineapple on pizza isn't allowed

Where? : Hawaiian pizza

Why? : the Italian cuisine should be respected

Details: Pineapple on pizza would put your relationship with folks you respect at risk.

Possible solutions:
    - eat pineapple for desert
    - stop adding pineapple to pizza

You can learn more about this problem at https://www.bbc.co.uk/bitesize/articles/z2vftrd
```

### Modules

This project consists of the following modules published under the `me.champeau.jdoctor` group id:

- `jdoctor-core` is the main API mosly consisting of the model
- `jdoctor-utils` is a convenience module providing builders and renderers for problems and solutions
- `jdoctor-bom` is a platform used for aligning dependency versions

### Using the library in your favorite project

To use `jdoctor` with Gradle:

```groovy
dependencies {
    implementation("me.champeau.jdoctor:jdoctor-core:0.1.2")
}
```

From Maven:

```xml
<dependencies>
    <dependency>
        <groupId>me.champeau.jdoctor</groupId>
        <artifactId>jdoctor-core</artifactId>
        <version>0.1</version>
    </dependency>
</dependencies>
```

### Building

Run `./gradlew build`
