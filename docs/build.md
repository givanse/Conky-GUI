# Steps to build Conky GUI

## Get the source code

    git clone git@github.com:givanse/Conky-GUI.git

## Set up the environment

### Ubuntu


#### Install tools and dependencies

    sudo apt-get install openjdk-7-jdk scala libjava-gnome-java junit4 ant fakeroot lintian
    
##### Variables
###### SCALA_HOME

    sudo ln -s /usr/share/java/ /usr/share/java/lib

###### JAVA_HOME

    javac_path=`readlink -f /usr/bin/javac`
    export JAVA_HOME=${javac_path%/bin/javac} 

## Compile

    ant compile
    
A succesful compilation will return:

    BUILD SUCCESSFUL
    Total time: 16 seconds

## Build distributable packages
 * ```ant tar``` get a .tar.bz2 package
 * ```ant deb``` get a .deb package

## Other project tasks
 * ```ant dbg-classpath``` review that all the required libraries are included
 * ```ant test``` run all the unit tests
 * ```ant run``` execute the project
 * ```ant clean``` remove every file that is not part of the repo
 * ```ant package``` build distributable package files (tar and deb)

