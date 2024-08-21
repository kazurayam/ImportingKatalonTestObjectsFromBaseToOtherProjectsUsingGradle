# Importing TestObjects across Katalon Projects using Gradle

## Background

koushik.kannan posted a question to Katalon User forum, with title [Is there a way to override findTestObject method](https://forum.katalon.com/t/is-there-a-way-to-override-findtestobject-method/142370). His question inspired me. I played on Katalon Studio and I have created a [GitHub repository](https://github.com/kazurayam/ImportingTestObjectsAcrossKatalonProjectsUsingGradle) where 2 Katalon projects are linked together using Gradle. I would explain here what I made.

## Problem to solve

The following diagram describes what I want to achieve.

![sequence diagram](https://kazurayam.github.io/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/diagrams/out/sequence/sequence.png)

I would enumerate what's required, as follows:

1. In our team, we have 3 persons: Guru, Tester1 and Tester2.
2. We have 3 Katalon Studio projects: Base, ProjectA and ProjectB.
3. Guru develops the Base project, Tester1 develops the ProjectA, Tester2 develops the ProjectB.
4. In the Base project, Guru develops a set of reusable TestObjects, which is inteded to by used by Tester1 and Tester2
5. In the ProjectA, Tester1 develops a set of TestObjects, which is local to the ProjectA. And at the same time, Tester1 wants to use some TestObjects developed by Guru in the Base project.
6. In the ProjectB, Tester2 develops a set of TestObjects, which is local to the ProjectB. And at the same time, Tester2 wants to use some TestObjects developed by Guru in the Base project.
7. Test1 and Test2 do not need all the TestObjects developt by Guru in the Base project. Test1 wants to select some of the TestObjects out of the Base project.
8. Test1 and Test2 wants to keep their local project the ProjectA and the ProjectB in sync with the Base project as much as possible.
9. Test1 and Test2 wants to synchronize their local projects by an easy operation: by a single command operation. They do not like a long sequence of copy&past operations on Windows Explorer GUI.

## How the Katalon projects will look?

### The Base project
Guru has developed the Base project. In the Base project's "Object Repository" folder, I can find a subfolder named "Page_CURA Healthcare Service" with a lot of TestObjects.

![01](https://kazurayam.github.io/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/images/01BaseProject.png)

### The ProjectA before importing

Tester1 has developed the ProjectA. In the ProjectA's "Object Repository" folder, I can find only a subfolder named "Page_DuckDuckGo Privacy, simplified". I do not see the "Page_CURA..." TestObjects yet.

![02](https://kazurayam.github.io/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/images/02ProjectA_before_import.png)

### The ProjectA after importing

Now, let me assume that Tester1 performs a magic here!!!

After the magic, I can find 2 subfolers under the "Object Repository".

![03](https://kazurayam.github.io/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/images/03ProjectA_after_import.png)

The "Page_CURA Healthcare Service" and its componet TestObjects have been imported from the Base project into the ProjectA. Tester1 is now able to use the TestObjects that Guru had developed in the Base project. This is what I wanted achieve.

## Solution

I would use [Gradle](https://gradle.org/) build tool. Tester1 and Tester2 need to prepare environment on their machines.

1. They need to install Java
2. They need to install Gradle

See the doc [Installing Gradle](https://docs.gradle.org/current/userguide/installation.html#installation)

## Description

Tester1 wants to execute the following command on his machine in the commandline:

```
$ cd <ProjectA root folder>
$ gradle init
Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 1

Select build script DSL:
  1: Kotlin
  2: Groovy
Enter selection (default: Kotlin) [1..2] 2

Project name (default: ProjectA): ProjectA
Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no


> Task :init
To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.5/samples

BUILD SUCCESSFUL in 17s
2 actionable tasks: 2 executed
```

By this command, Tester1 will find the following files/folders are created

```
$ tree -L 1 .
.
├── build.gradle
├── gradle/
├── gradlew
├── gradlew.bat
└── settings.gradle
```

These files are required.

Tester1 wants to edit the `build.gradle` file. See the following code:

- [ProjectA/build.gradle](https://github.com/kazurayam/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/blob/master/ProjectA/build.gradle)

Tester1 can copy the above code into his `ProjectA/build.gradle`. He can get started with it.

Tester1 wants to run the following command on his machine in the terminal window.

```
$ cd <ProjectA>
$ .\gradlew importTestObjectsFromBase
```

This will let Gradle to execute the `importTestObjectFromBase` task. Please read the [source code](https://github.com/kazurayam/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/blob/master/ProjectA/build.gradle) and understand what it does.

Briefly, I would describe what it does.

1. The task identifies the path of the folder as source in the Base project.
2. The task identifies the path of the folder as target in the ProjectA.
3. The task removes the target folder, so that the target is cleared once before imporing.
4. The task copies the files as TestObject from the source into the target
5. The task selects files to copy by the specified pattern of file/directory names

## How to apply this method to your project

You need to edit the [`build.gradle`](https://github.com/kazurayam/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/blob/master/ProjectA/build.gradle) so that it matches to your projects file/folder paths names. Also you should specify appropriate patterns to select the TestObjects to copy from the base into the working projects.gi
