## JDoctor, a Java library for good error messages

Designing good error messages is hard. 
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

### Modules

This project consists of the following modules:

- `jdoctor-core` is the main API mosly consisting of the model
- `jdoctor-utils` is a convenience module providing builders and renderers for problems and solutions
- `jdoctor-bom` is a platform used for aligning dependency versions

### Building

Run `./gradlew build`

