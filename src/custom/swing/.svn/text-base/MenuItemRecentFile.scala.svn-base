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
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JMenuItem
import texteditor.conky.ConkyFileReader

object MenuItemRecentFile {
    val EMPTY = "empty";
}

/**
 * MenuItemRecentFile class
 */
class MenuItemRecentFile(
  fileFullPath: String, panelConkyEditor: PanelConkyEditor) extends JMenuItem {

    private var filePath = fileFullPath;
    this.setFilePath(this.filePath)
    this.addActionListener(new ActionListener() {
        override def actionPerformed(evt: ActionEvent) {
            recentFileMenuItemActionPerformed(evt)
        }
    })

    /**
     * PUBLIC METHODS
     */

    def setFilePath(fileFullPath: String) {
      this.filePath = fileFullPath
      val fileName = this.filePath.substring(this.filePath.lastIndexOf("/") + 1)
      this.setText(fileName)
      this.setToolTipText(this.filePath)
    }

    def getFilePath: String = this.filePath

    /**
     * PRIVATE METHODS
     */

    private def recentFileMenuItemActionPerformed(evt: ActionEvent) {
        if (!this.filePath.equals(MenuItemRecentFile.EMPTY)) {
            val cfr = new ConkyFileReader();
            cfr.openFile(this.panelConkyEditor, this.filePath);
        }
    }
}
