# springboot

This project provides a headless Spring Boot application as a single "shaded" JAR, using Maven for
construction, and a variety of other tools:

 - Junit 5 for unit tests
 - [Spring Boot 2.3.1](https://spring.io/projects/spring-boot) =
 - [Lombok](https://projectlombok.org/) for boilerplate code
 - [Jackson](https://github.com/FasterXML/jackson) for JSON serialisation/deserialisation
 - various [Apache Commons](https://commons.apache.org/) utility libraries

In particular, it forms a sandbox and collection of snippets of code and accompanying tests for Spring Boot.

This collection of snippets has been formed by pieces of useful code generated over the past few years,
bought up to date for Java 12. You can expect that it will grow over time as I bring more old code into it.

## Prerequisites
This project assumes that:

  - you have Maven 3.6.3 or later available
  - you have git available
  - you are connected to the Internet and can pull dependencies via maven and the project via git
  - you are executing with Java 12.0.2 or later.

## Building and Testing
To build and test the executable JAR:

```
$ git clone git@github.com:TheBellman/sandbox.git
$ cd sandbox/springboot
$ mvn package
```

after a few seconds you should see similar to

```
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ springboot ---
[INFO] Building jar: /Users/robert/Projects/Java/sandbox/springboot/target/springboot-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.3.1.RELEASE:repackage (repackage) @ springboot ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.919 s
[INFO] Finished at: 2020-06-27T11:48:54+01:00
[INFO] ------------------------------------------------------------------------
```

If the application is running (see below), then you can use `curl` for example to test various end points:

 - `curl localhost:8080/api`
 - `curl localhost:8080/api/version`
 - `curl --data '{"message":"foo"}' --header "Content-Type: application/json" localhost:8080/api/echo`

Because of the (somewhat artificial) way in which I've set up the Spring Profiles in order to show how they wire
to the Maven build, if you want to execute tests that include writing and retrieving messages to the database, you need
to either build or run the application with the "release" profile:

 - `mvn -Prelease clean spring-boot:run` or
 - `mvn -Prelease clean package ; java -jar target/springboot-1.0-SNAPSHOT.jar`
## Usage
After building, you should be immediately able to execute the JAR:

```
java -jar target/springboot-1.0-SNAPSHOT.jar

======================================================================
| springboot - springboot:1.0-SNAPSHOT
| built   : 2020-06-27T17:46:30Z
| profile : development
======================================================================

2020-06-27 18:46:56 INFO  [main] net.parttimepolymath.sandbox.springboot.App: Starting App v1.0-SNAPSHOT on Rozencrantz.local with PID 7323 (/Users/robert/Projects/Java/sandbox/springboot/target/springboot-1.0-SNAPSHOT.jar started by robert in /Users/robert/Projects/Java/sandbox/springboot)
2020-06-27 18:46:56 INFO  [main] net.parttimepolymath.sandbox.springboot.App: The following profiles are active: development
2020-06-27 18:46:57 INFO  [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer: Tomcat initialized with port(s): 8081 (http)
2020-06-27 18:46:57 INFO  [main] org.apache.coyote.http11.Http11NioProtocol: Initializing ProtocolHandler ["http-nio-8081"]
2020-06-27 18:46:57 INFO  [main] org.apache.catalina.core.StandardService: Starting service [Tomcat]
2020-06-27 18:46:57 INFO  [main] org.apache.catalina.core.StandardEngine: Starting Servlet engine: [Apache Tomcat/9.0.36]
2020-06-27 18:46:57 INFO  [main] org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/]: Initializing Spring embedded WebApplicationContext
2020-06-27 18:46:57 INFO  [main] org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext: Root WebApplicationContext: initialization completed in 1030 ms
2020-06-27 18:46:57 INFO  [main] net.parttimepolymath.sandbox.springboot.VersionConsumer: Version details : Version(name=springboot, version=1.0-SNAPSHOT, build=2020-06-27T17:46:30Z, profile=development)
2020-06-27 18:46:57 INFO  [main] org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor: Initializing ExecutorService 'applicationTaskExecutor'
2020-06-27 18:46:58 INFO  [main] org.apache.coyote.http11.Http11NioProtocol: Starting ProtocolHandler ["http-nio-8081"]
2020-06-27 18:46:58 INFO  [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer: Tomcat started on port(s): 8081 (http) with context path ''
2020-06-27 18:46:58 INFO  [main] net.parttimepolymath.sandbox.springboot.App: Started App in 1.848 seconds (JVM running for 2.343)
```

There are several different ways to start the application - note injection of `server.port` to override the default 8080

 - `java -jar target/springboot-1.0-SNAPSHOT.jar`
 - `java -jar -Dserver.port=8181 target/springboot-1.0-SNAPSHOT.jar`
 - `mvn spring-boot:run`

## What you can see
I'm not a big fan of Spring, although Spring Boot makes me a little happier. I will concede though that now 
the thousands of lines of XML configuration are gone in favour of annotations, it's become more testable and readable.
I will also concede that Spring Boot, in particular, allows removal of a lot of boiler plate code when creating
applications. The downside is that a lot of dependencies are needed - it still disturbs me that a program that does
little more than "hello world" can wind up as a 12Mb or more JAR.

My big frustration with the whole world of Spring is that there is a huge amount of samizdat documentation and information,
and it's very difficult to determine what information is relevant to current versions, and what information persists long
obsoleted conventions and habits.

I'm hoping that this little sandbox will display clean and current good practices.

### Relationship between Maven and Springboot
The `pom.xml` is largely as defined by [Spring Initializer](https://start.spring.io) with some additional dependencies:

 - `spring-boot-starter-validation` - allows validation of properties
 - `spring-boot-configuration-processor` - exposes `Configuration` definitions to the IDE
 - `spring-boot-starter-web` - allows for REST API
 - `spring-boot-starter-data-jpa` - adds wiring to a database via JPA/Hibernate
 - `lombok`
 - `jackson-databind` - this gives us `jackson-core` and `jackson-annotations` at the same time.
 - `h2`

It's also got two different profiles defined, which will come into play during bundling for release. By default the
"development" profile is active for all general testing, and the "release" profile should be used for the release artifact.

Finally, note that the use of resource filtering during the build phase, to ensure that build-time information is 
injected to resources bundled into the resulting JAR.

If you look in `application.properties` you can see that the Spring
application and profile properties are aligned with the values in the POM, and that we have also injected some build-time
properties into a configuration bean. Similarly, we inject build time values into `banner.txt`.

Doing this ensures that artifact version and names are defined only during and by the Maven build. 

### Spring Boot Application
The `App` class is the runnable entry point. Nothing interesting to see here, although note that this is the place where
the Spring Application and Spring behaviour can be finessed before it's actually started.

### Configuration
Configuration items and beans are defined in the configuration package (unsurprisingly) and are broadly just simple beans.

Note that `Version` by being defined as a `Configuration` and annotated with `ConfigurationProperties` shows how where
we inject values from `application.properties` (and the operating environment, if desired) to a Java object that's managed
by Spring. The `VersionConsumer` just illustrates that this mapping of the environmental properties to the Java runtime
context is working.

### JPA, Spring and Databases oh my!
There are a number of pieces to note here. First off, the `database` package contains the wiring between the database
and Java code, and the `StoringEchoService` shows use of the repository.

`MessagesRepositoryTest` illustrates use of Spring's inbuilt bits for testing JPA, and note the use of specifying
the `release` profile during a test in `EchoControllerTest`

Finally, the SQL files in the `resources` folders are used to set up the physical database schema, allied closely to the
`spring.datasource.*`, `spring.h2.*` and `spring.jpa.*` properties.

### API
The `web` classes provide the API via the `@RestController` markup. This is all quite straight forward, although note
that for the more complex endpoint, the business logic is broken out of this layer and into a separate (testable) 
business layer in the `service` package.

**YOU ARE HERE**

ToDo:
   - fetch-by-id
   - fetch-all
   - need to cap incoming message string to prevent overflows

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
