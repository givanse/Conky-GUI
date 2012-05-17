/*
 *  Copyrigh 2012 Gastón I. Silva E.
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

/**
 * Defines file paths used by Conky GUI.
 */
object Path {

    /**
     * FOLDERS
     */
    // The home folder of the current user.
    val HOME_FOLDER = System.getProperty("user.home")
    // The folder where all the Conky GUI configuration files are stored.
    val CONFIG_FOLDER = Path.HOME_FOLDER + "/.conkygui"

    /**
     * FILES
     */

    // The default file where un-titled Conky files are saved.
    val DEFAULT_CONKY_FILE = Path.HOME_FOLDER + "/conkyrc untitled"
    // The file where the user preferences are saved.
    val USER_PREFERENCES_FILE = Path.CONFIG_FOLDER + "/preferences.xml"
    // The file where the application state is saved.
    val WORKSPACE_FILE = Path.CONFIG_FOLDER + "/workspace.xml"
    // The file from where Conky variables are parsed.
    var VARIABLES_FILE = Path.CONFIG_FOLDER + "/variables.xml"

}