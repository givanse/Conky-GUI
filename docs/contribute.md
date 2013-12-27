# Contribute guide

 1. Fork the project
 2. Make a local clone of your fork
 3. [Build and test the project] (build.md) - skip step 1
 4. Create a branch for your changes and implement them.

        git checkout -b fix-or-add-this-and-that
      
 5. Test your changes: ```ant test```
 6. Verify your changes: ```ant run```
 7. Push your changes to your forked repo
      * update your fork

            git remote add upstream git@github.com:givanse/Conky-GUI.git
            git checkout master
            git pull upstream master
      * update your branch
    
            git checkout fix-or-add-this-and-that 
            git rebase master
      * push your changes

            git push --set-upstream origin fix-or-add-this-and-that
 8. Make a [pull request] (https://help.github.com/articles/creating-a-pull-request) 
