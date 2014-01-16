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

### Ubuntu

    #!/bin/bash

    # Install tools and dependencies
    sudo apt-get install openjdk-7-jdk scala libjava-gnome-java junit4 ant fakeroot lintian
    
    # SCALA_HOME
    sudo ln -s /usr/share/java/ /usr/share/java/lib

    # JAVA_HOME
    javac_path=`readlink -f /usr/bin/javac`
    export JAVA_HOME=${javac_path%/bin/javac} 

## 3. Compile

    ant compile
    
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

