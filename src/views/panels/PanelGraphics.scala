/*
 * 
 *  Copyrigh 2008 Gastón Silva
 *  This program is distributed under the terms of the GNU General def License
 * 
 *  This file is part of ConkyGUI.
 * 
 *  ConkyGUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General def License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ConkyGUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General def License for more details.
 *
 *  You should have received a copy of the GNU General def License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package views.panels

import custom.swing.CheckBox

// It allows you to read the properties file
private class PanelGraphics {}

object PanelGraphics extends docking.DockableView("Graphics") {

    private val spinnerStippledBorders = new javax.swing.JSpinner
    private val spinnerMaxSpecials = new javax.swing.JSpinner
    private val checkBoxDoubleBuffer = new CheckBox
    private val checkBoxDrawBorders = new CheckBox
    private val checkBoxDrawGraphBorders = new CheckBox
    private val checkBoxDrawOutline = new CheckBox
    private val checkBoxDrawShades = new CheckBox

    this.initComponents
    this.reset
    
    private def initComponents {
        
        setName("Form") // NOI18N

        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelGraphics])
        
        checkBoxDoubleBuffer.setText(resourceMap.getString("checkBoxDoubleBuffer.text")) // NOI18N
        checkBoxDoubleBuffer.setName("checkBoxDoubleBuffer") // NOI18N

        checkBoxDrawBorders.setText(resourceMap.getString("checkBoxDrawBorders.text")) // NOI18N
        checkBoxDrawBorders.setName("checkBoxDrawBorders") // NOI18N

        checkBoxDrawGraphBorders.setText(resourceMap.getString("checkBoxDrawGraphBorders.text")) // NOI18N
        checkBoxDrawGraphBorders.setName("checkBoxDrawGraphBorders") // NOI18N

        checkBoxDrawOutline.setText(resourceMap.getString("checkBoxDrawOutline.text")) // NOI18N
        checkBoxDrawOutline.setName("checkBoxDrawOutline") // NOI18N

        checkBoxDrawShades.setText(resourceMap.getString("checkBoxDrawShades.text")) // NOI18N
        checkBoxDrawShades.setName("checkBoxDrawShades") // NOI18N

        val labelPixels = new javax.swing.JLabel(resourceMap.getString("labelPixels.text"))
        val labelStippledBorders = new javax.swing.JLabel(resourceMap.getString("labelStippledBorders.text"))
        val labelMaxSpecials = new javax.swing.JLabel(resourceMap.getString("labelMaxSpecials.text"))

        /**
         * SpinnerNumberModel Parameters:
         * value - the current value of the model
         * minimum - the first number in the sequence
         * maximum - the last number in the sequence
         * stepSize - the difference between elements of the sequence
         */
        spinnerStippledBorders.setModel(new javax.swing.SpinnerNumberModel(8, 0, 999, 1));
        spinnerStippledBorders.setName("spinnerStippledBorders") // NOI18N

        spinnerMaxSpecials.setModel(new javax.swing.SpinnerNumberModel(8, 0, 999, 1));
        spinnerMaxSpecials.setName("spinnerMaxSpecials") // NOI18N

        // add all the components
        // checkboxes
        this.rowEnd( checkBoxDoubleBuffer )
        this.rowEnd( checkBoxDrawBorders )
        this.rowEnd( checkBoxDrawGraphBorders )
        this.rowEnd( checkBoxDrawOutline )
        this.rowEnd( checkBoxDrawShades )

        // spinners
        this.rowAppend( labelStippledBorders, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerStippledBorders, java.awt.GridBagConstraints.WEST )
        this.rowEnd( labelPixels, java.awt.GridBagConstraints.WEST )
        
        this.rowAppend( labelMaxSpecials, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerMaxSpecials, java.awt.GridBagConstraints.WEST )

        this.alignLeft
        this.alignTop
    }

    def isDoubleBuffer: String = this.checkBoxDoubleBuffer.isSelectedCheckBox
    
    def setDoubleBuffer(value: String) {
        this.checkBoxDoubleBuffer.setSelected(value)
    }

    def isDrawShades: String = this.checkBoxDrawShades.isSelectedCheckBox
    
    def setDrawShades(value: String) {
        this.checkBoxDrawShades.setSelected(value)
    }

    def isDrawOutline: String = this.checkBoxDrawOutline.isSelectedCheckBox
    
    def setDrawOutline(value: String) {
        this.checkBoxDrawOutline.setSelected(value)
    }

    def isDrawBorders: String = this.checkBoxDrawBorders.isSelectedCheckBox
    
    def setDrawBorders(value: String) {
        this.checkBoxDrawBorders.setSelected(value)
    }

    def isDrawGraphBorders: String = this.checkBoxDrawGraphBorders.isSelectedCheckBox
    
    def setDrawGraphBorders(value: String) {
        this.checkBoxDrawGraphBorders.setSelected(value)
    }

    def getStippledBorders: String = this.spinnerStippledBorders.getValue.toString
    
    def setStippleBorders(value: Int) {
        this.spinnerStippledBorders.setValue(value)
    }

    def getMaxSpecials: String = this.spinnerMaxSpecials.getValue.toString
    
    def setMaxSpecials(value: Int) {
        this.spinnerMaxSpecials.setValue(value)
    }

    def reset {
        this.setDoubleBuffer(CheckBox.NO)
        this.setDrawBorders(CheckBox.NO)
        this.setDrawGraphBorders(CheckBox.NO)
        this.setDrawOutline(CheckBox.NO)
        this.setDrawShades(CheckBox.NO)
        this.setStippleBorders(8)
        this.setMaxSpecials(512)
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.checkBoxDoubleBuffer.setEnabled(value)
        this.checkBoxDrawBorders.setEnabled(value)
        this.checkBoxDrawGraphBorders.setEnabled(value)
        this.checkBoxDrawOutline.setEnabled(value)
        this.checkBoxDrawShades.setEnabled(value)
        this.spinnerMaxSpecials.setEnabled(value)
        this.spinnerStippledBorders.setEnabled(value)
    }
    
}
