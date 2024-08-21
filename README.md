# Importing TestObjects across Katalon Projects using Gradle

## Background

koushik.kannan posted a question to Katalon User forum, with title [Is there a way to override findTestObject method](https://forum.katalon.com/t/is-there-a-way-to-override-findtestobject-method/142370). His question inspired me. I played on Katalon Studio and I have created a [GitHub repository](https://github.com/kazurayam/ImportingTestObjectsAcrossKatalonProjectsUsingGradle) where 2 Katalon projects are . I would explain here what I made.

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

## Solution

I would use [Gradle](https://gradle.org/) build tool. In the ProjectA and ProjectB, I will create `build.gradle` file which implements a custom task `importTestObjectsFromBase`. And Tester1 runs a command in the Terminal:

```
$ cd <ProjectA>
$ .\gradlew importTestObjectsFromBase
```

Tester2 also runs a command in the Terminal:

```
$ cd <ProjectB>
$ .\gradlew importTestObjectsFromBase
```

The `importTestObjectsFromBase` will copy the selected TestObjects into the local project's `Object Repository` folder.

Before executing the command, Test1 will see in the ProjectA:



- [docs](https://kazurayam.github.io/ImportingTestObjectsAcrossKatalonProjectsUsingGradle/)
