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

package listeners

import views.panels.PanelConkyEditor
import models.UserPreference
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import util.CLIExecuter
import views.windows.ConkyGUI

class ExecuteFileListener(conkyGUIView: ConkyGUI, userPreference: UserPreference) extends ActionListener {

    private var panelConkyEditor: PanelConkyEditor = null
    private val cli = new CLIExecuter()

    def setPanelConkyEditor(pce: PanelConkyEditor) {
        this.panelConkyEditor = pce
    }

    def stopConky {
        this.cli.stopConky
    }
   
    def actionPerformed(arg0: ActionEvent) {
        if(this.panelConkyEditor.isFileEdited) this.conkyGUIView.saveFile(false)
        val validatedPath = this.panelConkyEditor.getFilePath.replaceAll(" ", "\\\\ ")
        // replace the $FILE tag
        val cmd = this.userPreference.getCommand.replace(
                models.UserPreference.FILE_PATH_PLACE_HOLDER, validatedPath)
        this.cli.executeConkyFile(cmd)
    }

}
