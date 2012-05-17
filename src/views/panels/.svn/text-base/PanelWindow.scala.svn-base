/*
 * 
 *  Copyrigh 2010 Gastón I. Silva E.
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
import java.awt.Color

// It allows you to read the properties file
private class PanelWindow {}

object PanelWindow extends docking.DockableView("Window") {

    private val comboBoxOwnWindowType = new javax.swing.JComboBox
    private val checkBoxOwnWindow = new custom.swing.CheckBox
    private val checkBoxOwnWindowTransparent = new custom.swing.CheckBox
    private val bcOwnWindowColour = new custom.swing.ColorButton
    private val jCheckBoxUndecorated = new javax.swing.JCheckBox
    private val jCheckBoxBelow = new javax.swing.JCheckBox
    private val jCheckBoxAbove = new javax.swing.JCheckBox
    private val jCheckBoxSticky = new javax.swing.JCheckBox
    private val jCheckBoxSkipTaskbar = new javax.swing.JCheckBox
    private val jCheckBoxSkipPager = new javax.swing.JCheckBox
    private val textFieldOwnWindowClass = new javax.swing.JTextField
    private val textFieldOwnWindowTitle = new javax.swing.JTextField

    this.initComponents
    this.reset
    
    private def initComponents {

        this.setName("Form") // NOI18N

        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelWindow])

        textFieldOwnWindowClass.setName("textFieldOwnWindowClass") // NOI18N
        textFieldOwnWindowClass.setPreferredSize(new java.awt.Dimension(200, 25))

        textFieldOwnWindowTitle.setText(resourceMap.getString("textFieldOwnWindowTitle.text")) // NOI18N
        textFieldOwnWindowTitle.setName("textFieldOwnWindowTitle") // NOI18N
        textFieldOwnWindowTitle.setPreferredSize(new java.awt.Dimension(200, 25))

        val model: Array[Object] = List("normal", "desktop", "override").toArray
        comboBoxOwnWindowType.setModel(new javax.swing.DefaultComboBoxModel( model ))
        comboBoxOwnWindowType.setName("comboBoxOwnWindowType") // NOI18N

        checkBoxOwnWindow.setText(resourceMap.getString("checkBoxOwnWindow.text")) // NOI18N
        checkBoxOwnWindow.setName("checkBoxOwnWindow") // NOI18N

        checkBoxOwnWindowTransparent.setText(resourceMap.getString("checkBoxOwnWindowTransparent.text")) // NOI18N
        checkBoxOwnWindowTransparent.setName("checkBoxOwnWindowTransparent") // NOI18N

        bcOwnWindowColour.setText(resourceMap.getString("labelOwnWindowColour.text")) // NOI18N
        bcOwnWindowColour.setName("labelOwnWindowColour") // NOI18N

        jCheckBoxUndecorated.setText(resourceMap.getString("jCheckBoxUndecorated.text")) // NOI18N
        jCheckBoxUndecorated.setName("jCheckBoxUndecorated") // NOI18N

        jCheckBoxBelow.setText(resourceMap.getString("jCheckBoxBelow.text")) // NOI18N
        jCheckBoxBelow.setName("jCheckBoxBelow") // NOI18N

        jCheckBoxAbove.setText(resourceMap.getString("jCheckBoxAbove.text")) // NOI18N
        jCheckBoxAbove.setName("jCheckBoxAbove") // NOI18N

        jCheckBoxSticky.setText(resourceMap.getString("jCheckBoxSticky.text")) // NOI18N
        jCheckBoxSticky.setName("jCheckBoxSticky") // NOI18N

        jCheckBoxSkipTaskbar.setText(resourceMap.getString("jCheckBoxSkipTaskbar.text")) // NOI18N
        jCheckBoxSkipTaskbar.setName("jCheckBoxSkipTaskbar") // NOI18N

        jCheckBoxSkipPager.setText(resourceMap.getString("jCheckBoxSkipPager.text")) // NOI18N
        jCheckBoxSkipPager.setName("jCheckBoxSkipPager") // NOI18N

        val lOwnWindowClass = new javax.swing.JLabel
        val lOwnWindowTitle = new javax.swing.JLabel
        val lOwnWindowHints = new javax.swing.JLabel
        val lOwnWindowType = new javax.swing.JLabel

        lOwnWindowClass.setText(resourceMap.getString("lOwnWindowClass.text")) // NOI18N
        lOwnWindowClass.setName("lOwnWindowClass") // NOI18N

        lOwnWindowTitle.setText(resourceMap.getString("lOwnWindowTitle.text")) // NOI18N
        lOwnWindowTitle.setName("lOwnWindowTitle") // NOI18N

        lOwnWindowHints.setText(resourceMap.getString("lOwnWindowHints.text")) // NOI18N
        lOwnWindowHints.setName("lOwnWindowHints") // NOI18N

        lOwnWindowType.setText(resourceMap.getString("lOwnWindowType.text")) // NOI18N
        lOwnWindowType.setName("lOwnWindowType") // NOI18N

        // add all the components
        this.rowEnd( checkBoxOwnWindow )

        this.rowAppend( checkBoxOwnWindowTransparent )
        this.rowEnd( bcOwnWindowColour )

        this.rowAppend(lOwnWindowClass )
        this.rowEnd( textFieldOwnWindowClass )

        this.rowAppend(lOwnWindowTitle )
        this.rowEnd( textFieldOwnWindowTitle )

        this.rowAppend(lOwnWindowType )
        this.rowEnd( comboBoxOwnWindowType )

        this.rowEnd( lOwnWindowHints )

        this.rowAppend( jCheckBoxBelow )
        this.rowEnd( jCheckBoxSticky )

        this.rowAppend( jCheckBoxSkipTaskbar )
        this.rowEnd( jCheckBoxSkipPager )

        this.rowAppend( jCheckBoxUndecorated )
        this.rowEnd( jCheckBoxAbove )

        this.alignLeftTopCorner
    }

    def isOwnWindow: String = this.checkBoxOwnWindow.isSelectedCheckBox
    
    def setOwnWindow(value: String) { this.checkBoxOwnWindow.setSelected(value) }

    def isOwnWindowTransparent: String = this.checkBoxOwnWindowTransparent.isSelectedCheckBox
    
    def setOwnWindowTransparent(value: String) { this.checkBoxOwnWindowTransparent.setSelected(value) }

    def getOwnWindowClass: String = this.textFieldOwnWindowClass.getText
    
    def setOwnWindowClass(value: String) {
        this.textFieldOwnWindowClass.setText(value)
    }

    def getOwnWindowColour: String = this.bcOwnWindowColour.getColorRGB
    
    def setOwnWindowColour(color: Color) { this.bcOwnWindowColour.setColor(color) }

    def getOwnWindowTitle: String = this.textFieldOwnWindowTitle.getText
    
    def setOwnWindowTitle(title: String) { this.textFieldOwnWindowTitle.setText(title) }

    def getOwnWindowHints: String = {
        // undecorated,below,above,sticky,skip_taskbar,skip_pager
        var hints = ""
        if(this.jCheckBoxUndecorated.isSelected) {
            hints += this.jCheckBoxUndecorated.getText
        }
        if(this.jCheckBoxBelow.isSelected) {
            hints += "," + this.jCheckBoxBelow.getText
        }
        if(this.jCheckBoxAbove.isSelected) {
            hints += "," + this.jCheckBoxAbove.getText
        }
        if(this.jCheckBoxSticky.isSelected) {
            hints += "," + this.jCheckBoxSticky.getText
        }
        if(this.jCheckBoxSkipTaskbar.isSelected) {
            hints += "," + this.jCheckBoxSkipTaskbar.getText
        }
        if(this.jCheckBoxSkipPager.isSelected) {
            hints += "," + this.jCheckBoxSkipPager.getText
        }
        hints
    }

    def setUndecorated(value: Boolean) { this.jCheckBoxUndecorated.setSelected(value) }

    def setBelow(value: Boolean) { this.jCheckBoxBelow.setSelected(value) }

    def setAbove(value: Boolean) { this.jCheckBoxAbove.setSelected(value) }

    def setSticky(value: Boolean) { this.jCheckBoxSticky.setSelected(value) }

    def setSkipTaskBar (value: Boolean) { this.jCheckBoxSkipTaskbar.setSelected(value) }

    def setSkipPager(value: Boolean) { this.jCheckBoxSkipPager.setSelected(value) }

    def getOwnWindowType : String = this.comboBoxOwnWindowType.getSelectedItem.toString

    // type is a Scala reserved word.
    def setOwnWindowType(typee: String) { this.comboBoxOwnWindowType.setSelectedItem(typee) }

    def reset {
        this.setOwnWindow(CheckBox.NO)
        this.setOwnWindowTransparent(CheckBox.NO)
        this.setOwnWindowColour(Color.WHITE)
        this.setOwnWindowClass("")
        this.setOwnWindowTitle("")
        this.setOwnWindowType("normal")
        this.setUndecorated(false)
        this.setBelow(false)
        this.setAbove(false)
        this.setSticky(false)
        this.setSkipTaskBar(false)
        this.setSkipPager(false)
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.checkBoxOwnWindow.setEnabled(value)
        this.checkBoxOwnWindowTransparent.setEnabled(value)
        this.comboBoxOwnWindowType.setEnabled(value)
        this.jCheckBoxAbove.setEnabled(value)
        this.jCheckBoxBelow.setEnabled(value)
        this.jCheckBoxSkipPager.setEnabled(value)
        this.jCheckBoxSkipTaskbar.setEnabled(value)
        this.jCheckBoxSticky.setEnabled(value)
        this.jCheckBoxUndecorated.setEnabled(value)
        this.bcOwnWindowColour.setEnabled(value)
        this.textFieldOwnWindowClass.setEnabled(value)
        this.textFieldOwnWindowTitle.setEnabled(value)
    }
    
}
