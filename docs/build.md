# Steps to build Conky GUI

Follow this four steps:
   1. [Get the source code](build.md#1-get-the-source-code)
   2. [Set up the environment](build.md#2-set-up-the-environment)
      * [Ubuntu](build.md#ubuntu)
   3. [Compile](build.md#3-compile)
   4. [Build distributable packages](4-build-distributable-packages)

## 1. Get the source code

    git clone git@github.com:givanse/Conky-GUI.git

## 2. Set up the environment

Dependencies:
 * Java JDK
 * Scala compiler 
 * Junit4

If building a DEB:
 * fakeroot
 * lintian

Set JAVA_HOME and put it in the path, then set SCALA_HOME:
```bash
# SCALA_HOME
export SCALA_HOME=path-to-scala-folder
ln -s $SCALA_HOME/lib/scala-xml* $SCALA_HOME/lib/scala-xml.jar
ln -s $SCALA_HOME/lib/scala-parser-combinators* $SCALA_HOME/lib/scala-parser-combinators.jar
```

### Ubuntu
```bash
# Install tools and dependencies
sudo apt-get install openjdk-7-jdk scala libjava-gnome-java junit4 ant fakeroot lintian

# Add a fake lib/ folder.
# It is required because Scala and JUnit were installed through APT.
sudo ln -s /usr/share/java/ /usr/share/java/lib
```

## 3. Compile

    ant compile

If errors are returned, [the wiki](https://github.com/givanse/Conky-GUI/wiki) might be of help.    
A succesful compilation will return:

    BUILD SUCCESSFUL
    Total time: 16 seconds

## 4. Build distributable packages
 * ```ant tar``` builds a .tar.bz2 package
 * ```ant deb``` builds a .deb package

## Other project tasks
 * ```ant dbg-classpath``` review that all the required libraries are included
 * ```ant test``` run all the unit tests
 * ```ant run``` execute the project
 * ```ant clean``` remove every file that is not part of the repo
 * ```ant package``` build distributable package files (tar and deb)

