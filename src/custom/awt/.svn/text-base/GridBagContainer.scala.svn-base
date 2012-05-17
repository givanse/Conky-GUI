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
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package custom.awt

trait GridBagContainer extends java.awt.Container {

    // set the layout for the view
    private val gridbag = new java.awt.GridBagLayout
    //this.setLayoutForViewPort(gridbag)
    private val constraints = new java.awt.GridBagConstraints
    super.setLayout(gridbag);

    // set default values for the layout
    private val DEFAULT_ANCHOR = java.awt.GridBagConstraints.WEST
    this.constraints.anchor = this.DEFAULT_ANCHOR
    this.setLayoutInsets(7, 10, 3, 3)

    /**
     * Distribute the extra vertical space to the bottom edge of the GridBagLayou.
     */
    def alignTop() {
        val verticalEmptySpace = new javax.swing.JPanel
        this.constraints.weighty = 1.0
        this.constraints.fill = java.awt.GridBagConstraints.VERTICAL
        this.constraints.gridwidth = java.awt.GridBagConstraints.REMAINDER //end row
        this.gridbag.setConstraints(verticalEmptySpace, constraints)
        super.add(verticalEmptySpace)
        // For safety reset constraints to its defaults
        this.constraints.weighty = 0.0
        this.constraints.fill = 0
        this.constraints.gridwidth = 1
    }

    /**
     * Distribute the extra horizontal space to the right edge of the GridBagLayou.
     */
    def alignLeft() {
        val horizontalEmptySpace = new javax.swing.JPanel
        this.constraints.weightx = 1.0
        this.constraints.fill = java.awt.GridBagConstraints.HORIZONTAL
        this.constraints.gridwidth = java.awt.GridBagConstraints.REMAINDER //end row
        this.gridbag.setConstraints(horizontalEmptySpace, constraints)
        super.add(horizontalEmptySpace)
        // For safety reset constraints to its defaults
        this.constraints.weightx = 0.0
        this.constraints.fill = 0
        this.constraints.gridwidth = 1
    }

    /**
     * Distribute the extra space to the right edge and the bottom edge of the GridBagLayou.
     */
    def alignLeftTopCorner {
        this.alignLeft
        this.alignTop
    }

    /**
     * Specifies the external padding of the components
     * that will be added, the minimum amount
     * of space between the component and the edges of its display area.
     * The default value is new Insets(0, 0, 0, 0) and
     * they go counter clock-wise: top left bottom right
     */
    def setLayoutInsets(top: Int, left: Int, bottom: Int, right: Int) {
        this.constraints.insets = new java.awt.Insets(top, left, bottom, right)
    }

    def rowAppend(component: java.awt.Component) {
        this.constraints.gridwidth = 1
        this.gridbag.setConstraints(component, constraints)
        super.add(component);
    }

    def rowEnd(component: java.awt.Component) {
        this.constraints.gridwidth = java.awt.GridBagConstraints.REMAINDER //end row
        this.gridbag.setConstraints(component, constraints)
        super.add(component)
        // For safety reset constraints to its defaults
        this.constraints.gridwidth = 1
    }

    def rowAppend(component: java.awt.Component, anchor: Int) {
        this.constraints.gridwidth = 1
        this.constraints.anchor = anchor
        this.gridbag.setConstraints(component, constraints)
        super.add( component )
        // For safety reset constraints to its defaults
        this.constraints.anchor = this.DEFAULT_ANCHOR
    }

    def rowEnd(component: java.awt.Component, anchor: Int) {
        this.constraints.gridwidth = java.awt.GridBagConstraints.REMAINDER
        this.constraints.anchor = anchor
        this.gridbag.setConstraints(component, constraints)
        super.add( component )
        // For safety reset constraints to its defaults
        this.constraints.gridwidth = 1
        this.constraints.anchor = this.DEFAULT_ANCHOR
    }

    /**
     * Adds a component to the current row and it fills
     * as many cells as specified in the arguments.
     *
     * @param component: is the component to be added
     * @param cellWidth: is the number of columns filled by the component
     */
    def rowAdd(component: java.awt.Component, cellWidth: Int) {
        this.constraints.gridwidth = cellWidth
        this.gridbag.setConstraints(component, constraints)
        super.add( component )
        // For safety reset constraints to its defaults
        this.constraints.gridwidth = 1
    }

    /**
     * Adds a component to the current column and it fills
     * as many rows as specified in the arguments.
     *
     * @param component: is the component to be added
     * @param cellHeight: is the number of rows filled by the component
     */
    def columnAdd(component: java.awt.Component, cellHeight: Int) {
        this.constraints.gridheight = cellHeight
        this.gridbag.setConstraints(component, constraints)
        super.add( component )
        // For safety reset constraints to its defaults
        this.constraints.gridheight = 1
    }

    /**
     * Adds a component to the current column and it fills
     * all the rows available from the current row.
     *
     * @param component: is the component to be added
     */
    def columnEnd(component: java.awt.Component) {
        this.constraints.gridheight = java.awt.GridBagConstraints.REMAINDER
        this.gridbag.setConstraints(component, constraints)
        super.add( component )
        // For safety reset constraints to its defaults
        this.constraints.gridheight = 1
    }
}
