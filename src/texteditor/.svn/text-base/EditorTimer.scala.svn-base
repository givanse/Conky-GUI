/*
 * 
 *  Copyrigh 2008 Gastón Silva
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
package texteditor

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Timer

class EditorTimer(delay: Int, al: ActionListener) extends Timer(delay, al) {

    super.setRepeats(false)

    def this(delay: Int) {
        this(delay, new ActionListener {
                @Override
                def actionPerformed(arg0: ActionEvent) {
                    val msg = "This action was added just to be removed in the first run. It helps the restartTimer() method"
                    throw new UnsupportedOperationException(msg)
                }
            })
    }
    
    def this() {
        this(1000)// default delay, 1000 milliseconds
    }
    
    override def restart {
        val alArr = super.getActionListeners
        super.removeActionListener(alArr(0))
        super.restart()
    }

}
