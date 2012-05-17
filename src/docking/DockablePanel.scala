/*
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
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package docking

import com.vlsolutions.swing.docking.DockKey
import java.awt.Component

/**
 * DockablePanel is a dockable JPanel.
 * By default this JPanel has a BoxLayout with its elements laid out top to bottom (javax.swing.BoxLayout.Y_AXIS).
 */
class DockablePanel(key: String)
extends javax.swing.JPanel
with com.vlsolutions.swing.docking.Dockable {

    private val dockKey = new DockKey(key)
    /**
     * Do not allow the dock to be closed, because
     * Conky GUI does not provide a way to open it again.
     */
    this.dockKey.setCloseEnabled(false)
    this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS))
    
    def getDockKey: DockKey = { this.dockKey }
    
    def getComponent: Component = { this }
    
}