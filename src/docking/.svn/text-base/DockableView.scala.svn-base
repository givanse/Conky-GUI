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

/**
 * DockableView is a dockable JPanel which has as a first and only child component a JScrollPane.
 * Any component added to this JPanel will not be added directly to it, but
 * to the JViewport of its JScrollPane.
 *
 * By default this JPanel has a BoxLayout with its elements laid out top to bottom (javax.swing.BoxLayout.Y_AXIS).
 */
class DockableView(key: String)
  extends javax.swing.JPanel
  with com.vlsolutions.swing.docking.Dockable {

    private val dockKey = new DockKey(key)
    /**
     * Do not allow the dock to be closed, because
     * Conky GUI does not provide a way to open it again.
     */
    this.dockKey.setCloseEnabled(false)
    
    // create the view
    private val viewPortView = new custom.swing.GridBagPanel
    //viewPortView.setBackground(java.awt.Color.PINK)
    
    // Create and add the JScrollPane to this JPanel
    super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS))
    super.add( new javax.swing.JScrollPane( this.viewPortView ) )

    def getDockKey: DockKey = { this.dockKey }
    
    def getComponent: java.awt.Component = { this }

    /**
     * Distribute the extra vertical space to the bottom edge of the GridBagLayou.
     */
    def alignTop() {
        this.viewPortView.alignTop()
    }

    /**
     * Distribute the extra horizontal space to the right edge of the GridBagLayou.
     */
    def alignLeft() {
        this.viewPortView.alignLeft()
    }

    /**
     * Distribute the extra space to the right edge and the bottom edge of the GridBagLayou.
     */
    def alignLeftTopCorner {
        this.viewPortView.alignLeftTopCorner
    }

    /**
     * Specifies the external padding of the components that will be added, the minimum amount
     * of space between the component and the edges of its display area.
     * The default value is new Insets(0, 0, 0, 0) and they go counter clock-wise: top left bottom right
     */
    def setLayoutInsets(top: Int, left: Int, bottom: Int, right: Int) {
        this.viewPortView.setLayoutInsets(top, left, bottom, right)
    }

    def rowAppend(component: java.awt.Component) {
        this.viewPortView.rowAppend(component);
    }

    def rowEnd(component: java.awt.Component) {
        this.viewPortView.rowEnd(component)
    }

    def rowAppend(component: java.awt.Component, anchor: Int) {
        this.viewPortView.rowAppend( component, anchor )
    }

    def rowEnd(component: java.awt.Component, anchor: Int) {
        this.viewPortView.rowEnd( component, anchor )
    }

    def rowAdd(component: java.awt.Component, gridwidth: Int) {
        this.viewPortView.rowAdd( component, gridwidth )
    }

    // Can't override the setLayout() method. The VLDocking framework class might be calling it to, so
    // if its overrided the class enters an infinite loop.
    // To correct this problem the parent class should call the method from its ancestor i.e. super.setLayout()
    /*def setLayoutForViewPort(mgr: java.awt.LayoutManager) {
        this.viewPortView.setLayout(mgr)
    }*/
}