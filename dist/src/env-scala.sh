#!/bin/bash

# To edit this scripts, its easier to:
#   ant tar
#   extract the tar and work there
#   when done, copy the modified scripts here
# Its easier because you can execute and test the scripts inside the
# the compiled project.

# Find or guess where SCALA_HOME is
function export_scala_home {
    # check if $SCALA_HOME is set
    if [ -z $SCALA_HOME ]; then
        echo 'using a default SCALA_HOME'

        # try to find it at common locations
        paths=("/usr/share/scala" "/usr/share/java")
        for indx in 0 1
	    do
	        SCALA_HOME=${paths[$indx]}
	        if [ -d $SCALA_HOME ]; then
                export SCALA_HOME=${paths[$indx]}
	            break
	        fi
	        SCALA_HOME=''
	    done
    else
        echo 'SCALA_HOME already set'
    fi

    echo 'SCALA_HOME: '$SCALA_HOME
    echo ''
}

# Find or guess where SCALA_LIB is
function export_scala_lib {

    SCALA_LIB=$SCALA_HOME

    scala_library=$SCALA_LIB'/scala-library.jar'
    if [ -f $scala_library ] ; then
        export SCALA_LIB=$SCALA_HOME
    else
        SCALA_LIB=$SCALA_HOME'/lib'

        scala_library=$SCALA_LIB'/scala-library.jar'
        if [ -f $scala_library ] ; then
            export SCALA_LIB=$SCALA_HOME'/lib'
        else
            echo 'error: scala-library.jar could not be found';
        fi
    fi

    echo 'SCALA_LIB: '$SCALA_LIB
    echo ''
}

#EOF
