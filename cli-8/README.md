# cli-8

This project provides a command-line Java application as a single "shaded" JAR, using Maven for
construction, and a variety of other tools:

 - Junit 5 for unit tests
 - [Lombok](https://projectlombok.org/) for boilerplate code
 - [Jackson](https://github.com/FasterXML/jackson) for JSON serialisation/deserialisation
 - various [Apache Commons](https://commons.apache.org/) utility libraries
 
In particular, it forms a sandbox and collection of snippets of code and accompanying tests for Java 8.

This collection of snippets has been formed by pieces of useful code generated over the past few decade,
bought up to date for Java 8. You can expect that it will grow over time as I bring more old code into it.

Some of the things shown in here:

 - use of `assert` in Java code
 - use of Junit 5 assertions
 - use of Java 8 streams and lambda expressions
 - use of the Maven shade plugin
 - reading properties from the the classpath
 - reading files from the class path
 - configuring and using Log4J via SLF4J
 - thread safe construction of a Jackson ObjectMapper
 - serialisation/deserialisation of Java 8 Instant
 - command line parsing with [Commons CLI](https://commons.apache.org/proper/commons-cli/)

## Prerequisites
This project assumes that:

  - you have Maven 3.5.4 or later available
  - you are connected to the Internet and can pull dependencies via maven
  - you are executing with Java 1.8.0 or later.

## Building and Testing
To build the executable JAR:

```
$ git clone git@github.com:TheBellman/sandbox.git
$ cd sandbox
$ mvn package
```

after a few seconds you should see similar to

```
[INFO] Attaching shaded artifact.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.193 s
[INFO] Finished at: 2020-05-16T20:03:29+01:00
[INFO] ------------------------------------------------------------------------
```

## Usage
After building, you should be immediately able to execute the JAR:

```
$ java -jar target/cli-8-1.0-SNAPSHOT-shaded.jar 
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Main: started
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Application: Start executing 200 iterations
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Application: loaded 200 random names
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Application: the surname name 'Cruz' was found 1 times
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Application: Finish executing 200 iterations
2020-05-16 20:04:13 INFO  [main] net.parttimepolymath.sandbox.Main: ended
```

or alternately provide CLI utilities - you can find them via the inbuilt help:

```
$ java -jar target/cli-8-1.0-SNAPSHOT-shaded.jar -?
2020-05-16 20:04:57 INFO  [main] net.parttimepolymath.sandbox.Main: started
usage: cli-8
 -?,--help            print this help message
 -d,--debug           enable debug mode
 -n,--count <count>   number of iterations to run
2020-05-16 20:04:57 INFO  [main] net.parttimepolymath.sandbox.Main: ended
```

for example
```
$ java -jar target/cli-8-1.0-SNAPSHOT-shaded.jar -n 5000
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Main: started
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Application: Start executing 5000 iterations
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Application: loaded 200 random names
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Application: the surname name 'Cruz' was found 48 times
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Application: Finish executing 5000 iterations
2020-05-16 20:05:39 INFO  [main] net.parttimepolymath.sandbox.Main: ended
```

## License
Copyright 2020 Little Dog Digital

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.