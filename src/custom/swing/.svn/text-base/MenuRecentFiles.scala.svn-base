/*
 * 
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

package custom.swing

import views.panels.PanelConkyEditor
import javax.swing.JMenu

object MenuRecentFiles {
    val MAX_FILES = 7
}

/**
 * MenuRecentFiles class
 */
class MenuRecentFiles(panelConkyEditor: PanelConkyEditor)
extends JMenu("Open Recent File") {

    this.setMnemonic('r')
    private val menuItemsArray = this.initMenuItemsArray

    /**
     * PUBLIC METHODS
     */

    /**
     * Populate this MenuRecentFiles with MenuItemRecentFile objects
     * build from the list of strings received as a argument.
     *
     * recentFiles: An array containing the full paths of the files to be
     * added to this menu.
     */
    def setRecentFiles(recentFiles: Array[String]) {
        for(index <- List.range(0, recentFiles.length)) {
            val filePath = recentFiles(index)
            if( filePath != null && !filePath.isEmpty ) {
                this.menuItemsArray(index).setFilePath(filePath)
            }
        }
    }

    /**
     * Adds a MenuItemRecentFile, to this MenuRecentFiles, of a recently opened file.
     *
     * filePath: The full path of the file to be added to this menu.
     */
    def addNewRecentFile(fileFullPath: String) {
        val menuItemIndex = this.getMenuItemIndex( fileFullPath )
        val addingNewFile = menuItemIndex == -1
        // Place the new file on top of the list
        if ( addingNewFile ) {
            // Backwards loop
            // start (inclusive)
            val startIndx = this.menuItemsArray.length - 1
            // end (exclusive)
            val endIndx = 0
            val stepSize = -1
            val backwardsList = List.range(startIndx, endIndx, stepSize) 
            for( index <- backwardsList ) {
                val currentItem = this.menuItemsArray( index )
                var nextItem = this.menuItemsArray( index - 1 )
                currentItem.setFilePath( nextItem.getFilePath )
            }
            // Add new file
            this.menuItemsArray(0).setFilePath( fileFullPath )
        }
    }

    def getRecentFiles: Array[String] = {
        val numberOfFiles = this.menuItemsArray.length
        val filesArray = new Array[String](numberOfFiles)
        for (index <- List.range(0, numberOfFiles) ) {
            filesArray(index) = this.menuItemsArray(index).getFilePath
        }
        return filesArray
    }

    /**
     * PRIVATE METHODS
     */

    private def initMenuItemsArray(): Array[MenuItemRecentFile] = {
        val menuItemsArray = new Array[MenuItemRecentFile]( MenuRecentFiles.MAX_FILES )
        // Fill the menu with "empty" items
        for( index <- List.range(0, menuItemsArray.length) ) {
            menuItemsArray( index ) = new MenuItemRecentFile(
                MenuItemRecentFile.EMPTY, this.panelConkyEditor)
            this.add( menuItemsArray(index) )
        }
        return menuItemsArray
    }

    /**
     * Returns the index of the MenuItemRecentFile contained in this MenuRecentFiles.
     * 
     * fileFullPath: The full path value of the menu item.
     * returns: The index of the menu item.
     */
    private def getMenuItemIndex(fileFullPath: String): Int = {
        for( index <- List.range(0, this.menuItemsArray.length) ) {
            val currentFilePath = this.menuItemsArray(index).getFilePath
            if ( currentFilePath.equals( fileFullPath ) ) {
                return index;
            }
        }
        return -1;// MenuItemRecentFile Not found
    }
}
