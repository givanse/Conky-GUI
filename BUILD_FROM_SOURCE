Steps to build Conky GUI from source:

    Create the folder where you want to store the source files:
    	mkdir conkygui
	cd conkygui
	git init

    Get the latest source code version:
	git remote add origin https://github.com/givanse/conky-gui.git
	git pull origin master

    Install the required libraries:
	sudo apt-get install openjdk-7-jdk scala libjava-gnome-java

    Set environment variables (SCALA_HOME, JUNIT_HOME)
	If Java and Scala were installed through apt-get in Ubuntu, do this:
            cd /usr/share/java
            sudo ln -s . lib

        In the file conkygui/nbproject/project.properties
        adjust, if necessary, the follwoing properties:
            scala.home
            junit.home

    Within the 'conkygui' folder:
        ant compile
            Just compile.
        ant test
	    Compile and run the unit tests.
	    Follow the "install JUnit" instructions.
        ant run
	    Compile and run.
        ant package
	    Build distributable files (.deb and .tar.gz files)
	    You'll need: sudo apt-get install fakeroot lintian
        ant dbg-classpath
            Print the project classpath.
	    It will print all the files/JARs available. 

 Install JUnit
    Site: junit.org

    Download
        wget http://search.maven.org/remotecontent?filepath=junit/junit/4.11/junit-4.11.jar
        wget http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

    Update with names of the files that you have downloaded:
    vim conkygui/nbproject/project.properties 
        file.reference.junit4.jar
        file.reference.hamcrest.jar
