# Steps to build Conky GUI

## Get the source code

    git clone git@github.com:givanse/Conky-GUI.git

## Install all the dependencies

Development and libraries

    sudo apt-get install openjdk-7-jdk scala libjava-gnome-java
Testing

    sudo apt-get install junit4
Packaging (deb file)

    sudo apt-get install fakeroot lintian
    
#### Set JAVA_HOME
##### Ubuntu 12.04.3
After installing Java and Scala through apt-get

    sudo ln -s /usr/share/java/ /usr/share/java/lib
    export JAVA_HOME='/usr/'

## Compile

    ant compile
    
A succesful compilation will return:

    BUILD SUCCESSFUL
    Total time: 16 seconds

## Build package
 * ```ant tar``` get a .tar.bz2 package
 * ```ant deb``` get a .deb package

## Project tasks
 * ```ant dbg-classpath``` review that all the required libraries are included
 * ```ant test``` run all the unit tests
 * ```ant run``` execute the project
 * ```ant clean``` remove every file that is not part of the repo
 * ```ant package``` build distributable package files (tar and deb)

