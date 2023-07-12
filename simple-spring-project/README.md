# Simple Spring Project

This is a simple Spring Project. I will write notes here

To future me or someone who might be confused with the layout of this "simple" project. I will explain some of these things, just so you don't go crazy. I feel like often, it is assumed that we will know something, or even worse (in my opinion), to just ignore it. This is nothing more than a convention, but it is one you should probably follow.

src/main/java -> where all the java code lives. If you see com/.../.../someRandomFolder this is a Java package. This is a java way to logically split code into "packages" which are folder where common Java Classes live. For example, in this project, there is com/elisegovia/projects/game (in java this looks like `com.elisegovia.projects.game`)

src/main/resources -> where config files live. In complex java applications, we want to keep constants or values in one place so that we may reference them in our code, instead of writing them as variables all over the place. This means we just need to change the values in one place and be done 🥇

test/java -> where Java testing lives. These are also java files, but they test what is in src/main/java

pom.xml -> this is basically a configuration and instruction for Maven to work. Maven is a build tool as well as a dependency manager which compiles, and packages this project, as well as keeps track of all the dependencies we need. It gets those dependencies from its remote repository, but then it stores them in the local repository called .m2. This .m2 should be somewhere in your home directory.

mvnw and mvnw.cmd are Unix-type and Windows commands respectively. They can run maven functionality even if you don't have maven installed. You don't have to worry too much about it.
