/*
 *  Copyrigh 2010 Gastón I. Silva E.
 *  This program is distributed under the terms of the GNU General Public License
 *
 *  This file is part of ConkyGUI.
 *
 *  ConkyGUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ConkyGUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package models

import custom.swing.MenuItemRecentFile
import custom.swing.MenuRecentFiles
import custom.swing.MenuItemRecentFile.EMPTY
import custom.swing.MenuRecentFiles.MAX_FILES
import custom.swing.ErrorPane
import java.io.File
import java.io.PrintWriter
import java.io.FileNotFoundException
import java.io.IOException
import scala.xml.Elem
import scala.xml.XML
import scala.io.Source

/**
 * UserPreference singleton.
 */
object UserPreference {

    private var instance: UserPreference = null;

    val FILE_PATH_PLACE_HOLDER = "$FILE"
    val DEFAULT_COMMAND = "killall conky; conky -c $FILE &"
    
    //TODO object can access private fields of a class with the smae name, but
    // that class can't access private fields of the object
    val TAG_COMMENT = "comment"
    val TAG_COMMAND = "command"
    val TAG_FILE = "file"

    /**
     * Returns a reference to the same UserPreference object always.
     */
    def getInstance: UserPreference = {
        if( UserPreference.instance == null ) {
            val file = models.Path.USER_PREFERENCES_FILE
            UserPreference.instance = UserPreference.readFile( file )
        }
        return UserPreference.instance
    }

    //TODO this method should be private, but it has been left public
    // for unit test purposes.
    // (See TODO note above, class can't access private fields in object)
    /**
     * Reads models.Path.USER_PREFERENCES_FILE (~/.conkygui/preferences.xml) and
     * initializates a UserPreference object with its values. If its the first
     * time that Conky GUI is run or the preferences.xml file has been deleted,
     * the default values are used. The default values come from the resource
     * files of each of the components of a PreferencesWindow.
     */
    def readFile(filePath: String): UserPreference = {
        /* Herding XML in Scala
         * http://programming-scala.labs.oreilly.com/ch10.html
         */
        val userPreferences = new UserPreferences()
        try {
            val source = Source.fromFile( filePath )
            val xmlString = source.getLines.mkString
            val xml:Elem = XML.loadString(xmlString)
            var files: List[String] = Nil
            for( nodeSeq <- (xml \ UserPreference.TAG_FILE) ) {
                    files = nodeSeq.text::files
            }
            val command = (xml \ UserPreference.TAG_COMMAND).text
            val comment = (xml \ UserPreference.TAG_COMMENT).text
            // the order of the array is important
            userPreferences.setFiles( files.reverse.toArray )
            userPreferences.setCommand( command )
            userPreferences.setComments( comment )
            return userPreferences
        } catch {
            case ex: FileNotFoundException =>
                /**
                 * Do nothing.
                 * It is the first time that the program is run or
                 * the file has been deleted by the user, so
                 * the default values will be used
                 */
            case ex: IOException =>
                //TODO replace with a logger
                ErrorPane.showErrorMessage(
                  "PreferencesRW: readPreferencesFile", ex)
        }
        return userPreferences
    }
    
}

/**
 * UserPreference interface.
 */
trait UserPreference {
    def setFiles(files: Array[String])
    def getFiles: Array[String]
    def setComments(comment: String)
    def getComments: String
    def setCommand(command: String)
    def getCommand: String
    def writeFile
}

/**
 * UserPreferences implementation.
 */
private class UserPreferences extends UserPreference {
  
    private var comments = "author: conky"
    private var command = models.UserPreference.DEFAULT_COMMAND
    private var files = new Array[String](MenuRecentFiles.MAX_FILES)

    for (i <- List.range(0, this.files.size) ) {
        this.files(i) = MenuItemRecentFile.EMPTY
    }

    /**
     * PRIVATE METHODS
     */

    private def escape(string: String): String = string.replace("&", "&amp;")

    private def reverseEscape(string: String): String = string.replace("&amp;", "&")

    /**
     * PUBLIC METHODS
     */

    def setFiles(files: Array[String]) {
        this.files = files
    }
    
    def getFiles: Array[String] = this.files

    def setComments(comment: String) {
        this.comments = reverseEscape(comment)
    }

    def getComments: String = this.comments

    def setCommand(command: String) {
        this.command = reverseEscape(command)
    }

    def getCommand: String = this.command

    /**
     * Writes the ~/.conkygui/preferences.xml file with the values of the
     * current UserPreference object.
     */
    def writeFile {
        val preferences = this.toString
        val errorLocation = "PreferencesRW: writePreferencesFile "
        try {
            val folder = new File( models.Path.CONFIG_FOLDER )
            folder.mkdir()
            val f = new File( models.Path.USER_PREFERENCES_FILE )
            f.createNewFile()
            val pw = new PrintWriter(f)
            pw.print("<?xml version='1.0' encoding='UTF-8'?>\n<root>"+
                preferences+"\n</root>")
            pw.close()
        } catch {
            case ex: FileNotFoundException =>
                ErrorPane.showErrorMessage(errorLocation, ex)
            case ex: IOException =>
                ErrorPane.showErrorMessage(errorLocation, ex)
        }
    }

    /**
     * @return the xml representation of this object
     */
    override def toString: String = {
        var xml = '<'+UserPreference.TAG_COMMENT+'>'+
            this.comments+"</"+UserPreference.TAG_COMMENT+'>';
        xml += "\n<"+UserPreference.TAG_COMMAND+'>'+
            this.command+"</"+UserPreference.TAG_COMMAND+'>';
        for(file <- this.files) {
            xml += "\n<"+UserPreference.TAG_FILE+'>'+
                file+"</"+UserPreference.TAG_FILE+'>'
        }
        return this.escape(xml)
    }

    override def equals(that: Any): Boolean =  {
      if( that == null || ! that.isInstanceOf[ UserPreference ] )
          return false
      return this.toString.equals( that.toString )
    }

    override def hashCode: Int = this.toString.hashCode

}