/*
 *  Copyrigh 2010 Gastón Silva
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

import javax.swing.SpinnerNumberModel
import javax.swing.JSpinner
import javax.swing.JComboBox
import javax.swing.DefaultComboBoxModel
import javax.swing.JLabel

// It allows you to read the properties file
private class PanelLayout {}

object PanelLayout extends docking.DockableView("Layout") {

    private val comboBoxUseSpacer = new JComboBox[String]
    private val spinnerMinimumSizeW = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerGapY = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerMaximumWidth = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerBorderMargin = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerBorderWidth = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerGapX = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val spinnerMinimumSizeH = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1))
    private val comboBoxAlignment = new JComboBox[String]

    this.initComponents
    this.reset
    
    private def initComponents {

        this.setName("Form") 

        val resourceMap = org.jdesktop.application.Application.
            getInstance(classOf[controllers.ConkyGUI]).getContext.
            getResourceMap(classOf[PanelLayout])

        //TODO: hardcoded, use properties files
        val modelUseSpacer: Array[String] = Array("none", "left", "right")
        comboBoxUseSpacer.setModel(
            new DefaultComboBoxModel[String](modelUseSpacer))
        comboBoxUseSpacer.setName("comboBoxUseSpacer") 

        //TODO: hardcoded, use properties files
        val modelAlign: Array[String] = Array("top_left", "top_right", 
            "top_middle", "bottom_left", "bottom_right", "bottom_middle", 
            "middle_left", "middle_right", "none")
        comboBoxAlignment.setModel(new DefaultComboBoxModel[String](modelAlign))
        comboBoxAlignment.setName("comboBoxAlignment") 

        spinnerMinimumSizeW.setName("spinnerMinimumSizeW") 

        spinnerGapY.setName("spinnerGapY") 

        spinnerMaximumWidth.setName("spinnerMaximumWidth") 

        spinnerBorderMargin.setName("spinnerBorderMargin") 

        spinnerBorderWidth.setName("spinnerBorderWidth") 

        spinnerGapX.setName("spinnerGapX") 

        spinnerMinimumSizeH.setName("spinnerMinimumSizeH") 

        val labelGapX = new JLabel(resourceMap.getString("labelGapX.text"))
        val labelGapY = new JLabel(resourceMap.getString("labelGapY.text"))
        val labelAlignment = new JLabel(resourceMap.getString("labelAlignment.text"))
        val labelMaximumWidth = new JLabel(resourceMap.getString("labelMaximumWidth.text"))
        val labelMinimumSize = new JLabel(resourceMap.getString("labelMinimumSize.text"))
        val labelHeight = new JLabel(resourceMap.getString("labelHeight.text"))
        val labelWidth = new JLabel(resourceMap.getString("labelWidth.text"))
        val labelUseSpacer = new JLabel(resourceMap.getString("labelUseSpacer.text"))
        val labelBorderMargin = new JLabel(resourceMap.getString("labelBorderMargin.text"))
        val labelBorderWidth = new JLabel(resourceMap.getString("labelBorderWidth.text"))

        // add all the components
        this.rowAppend( labelGapX, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerGapX )
        this.rowAppend( labelAlignment, java.awt.GridBagConstraints.EAST )
        this.rowEnd( comboBoxAlignment )

        this.rowAppend( labelGapY, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerGapY )
        this.rowAppend( labelUseSpacer, java.awt.GridBagConstraints.EAST )
        this.rowEnd( comboBoxUseSpacer )

        this.rowAppend( labelMaximumWidth, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerMaximumWidth )
        this.rowEnd( labelMinimumSize )

        this.rowAppend( labelBorderMargin, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerBorderMargin )
        this.rowAppend( labelWidth, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerMinimumSizeW )

        this.rowAppend( labelBorderWidth, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerBorderWidth )
        this.rowAppend( labelHeight, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerMinimumSizeH )

        this.alignLeftTopCorner
    }
    
    def getBorderMargin: String = this.spinnerBorderMargin.getValue.toString
    
    def setBorderMargin(value: Int) {
        this.spinnerBorderMargin.setValue(value)
    }

    def getBorderWidth: String = this.spinnerBorderWidth.getValue.toString
    
    def setBorderWidth(value: Int) {
        this.spinnerBorderWidth.setValue(value)
    }

    def getAlignment: String = this.comboBoxAlignment.getSelectedItem.toString
    
    def setAlignment(alignment: String) {
        this.comboBoxAlignment.setSelectedItem(alignment)
    }

    def getGapX: String = this.spinnerGapX.getValue.toString
    
    def setGapX(gap: Int) {
        this.spinnerGapX.setValue(gap)
    }

    def getGapY: String = this.spinnerGapY.getValue.toString
    
    def setGapY(gap: Int) {
        this.spinnerGapY.setValue(gap)
    }

    def getUseSpacer: String = this.comboBoxUseSpacer.getSelectedItem.toString
    
    def setUseSpacer(spacer: String) {
        this.comboBoxUseSpacer.setSelectedItem(spacer)
    }

    def getMaximumWidth: String = this.spinnerMaximumWidth.getValue.toString
    
    def setMaximumWidth(maxWidth: Int) {
        this.spinnerMaximumWidth.setValue(maxWidth)
    }

    /**
     * minimum_size  width (height)
        Minimum size of window
     * @width height
     */
    def getMinimumSizeConky: String = this.spinnerMinimumSizeW.getValue.toString + " " + this.spinnerMinimumSizeH.getValue.toString
    
    def setMinimumSizeConky(minW: Int, minH: Int) {
        this.spinnerMinimumSizeW.setValue(minW)
        this.spinnerMinimumSizeH.setValue(minH)
    }

    def reset {
        this.setAlignment("top_right")
        this.setUseSpacer("none")
        this.setGapX(0)
        this.setGapY(0)
        this.setMinimumSizeConky(400, 5)
        this.setMaximumWidth(0)
        this.setBorderMargin(4)
        this.setBorderWidth(1)
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.comboBoxAlignment.setEnabled(value)
        this.comboBoxUseSpacer.setEnabled(value)
        this.spinnerBorderMargin.setEnabled(value)
        this.spinnerBorderWidth.setEnabled(value)
        this.spinnerGapX.setEnabled(value)
        this.spinnerGapY.setEnabled(value)
        this.spinnerMaximumWidth.setEnabled(value)
        this.spinnerMinimumSizeH.setEnabled(value)
        this.spinnerMinimumSizeW.setEnabled(value)
    }

}
