# sandbox

This is a collection of snippets and pieces of code that have accumulated for years, pulled together into a single project with tests.

You can expect that it will grow over time, as it's more convenient for me to put the Java bits in one place. In addition, I will in
the future translate the Java 8 materials to Java 11 or later.

You can find the following in here:

  - cli-8: A Java 8 Maven project that builds a stand-alone executable JAR that does not much, accompanied by tests.
  - springboot: a Java 12 project that builds a dummy HTTP API, along with tests, to hold various snippets of Spring Boot fragments


![Java CI with Maven](https://github.com/TheBellman/sandbox/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

## Test and Build

In real terms you probably want to use the `pom.xml` in each of the sub-projects locally, however it _is_ possible to build
and test from the top level directory:

```
$ git clone git@github.com:TheBellman/sandbox.git
$ cd sandbox
$ mvn package
```

all being well, you should see a Reactor summary inside of 20-30 seconds:

```
[INFO] ----------------< net.parttimepolymath.sandbox:sandbox >----------------
[INFO] Building sandbox 1.0-SNAPSHOT                                      [3/3]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for sandbox 1.0-SNAPSHOT:
[INFO]
[INFO] cli-8 .............................................. SUCCESS [  5.230 s]
[INFO] springboot ......................................... SUCCESS [ 11.068 s]
[INFO] sandbox ............................................ SUCCESS [  0.001 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  16.512 s
[INFO] Finished at: 2020-07-05T12:27:05+01:00
[INFO] ------------------------------------------------------------------------
```

**Note:** Because the `springboot` module uses Java 12, you will need to execute with Java 12.0.2 or later, even though `cli-8` only
needs Java 8.
