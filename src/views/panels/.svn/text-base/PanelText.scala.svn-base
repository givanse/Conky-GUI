/** 
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
import javax.swing.JSpinner
import javax.swing.SpinnerNumberModel
import util.CLIExecuter

// It allows you to read the properties file
private class PanelText {}

object PanelText extends docking.DockableView("Text") {

    private val spinnerPadPercents = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val spinnerTextBufferSize = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val spinnerMaxUserText = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val spinnerXFTAlpha = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.001))
    private val checkBoxUseXFT = new custom.swing.CheckBox
    private val checkBoxUppercase = new custom.swing.CheckBox
    private val checkBoxOverrideUTF8 = new custom.swing.CheckBox
    private val checkBoxShortUnits = new custom.swing.CheckBox
    private val jComboBoxFont = new javax.swing.JComboBox
    private val jTextFieldXFTFont = new javax.swing.JTextField
    private val jButtonXFontsel = new javax.swing.JButton

    this.initComponents
    this.loadFonts
    this.reset
 
    private def initComponents {

        setName("Form") // NOI18N

        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelText])
        
        spinnerPadPercents.setMaximumSize(new java.awt.Dimension(100, 20))
        spinnerPadPercents.setName("spinnerPadPercents") // NOI18N

        spinnerTextBufferSize.setMaximumSize(new java.awt.Dimension(100, 20))
        spinnerTextBufferSize.setName("spinnerTextBufferSize") // NOI18N

        spinnerMaxUserText.setMaximumSize(new java.awt.Dimension(100, 20))
        spinnerMaxUserText.setName("spinnerMaxUserText") // NOI18N

        spinnerXFTAlpha.setEditor(new JSpinner.NumberEditor(spinnerXFTAlpha, "0.000"))
        spinnerXFTAlpha.setName("spinnerXFTAlpha") // NOI18N
        
        checkBoxUseXFT.setText(resourceMap.getString("checkBoxUseXFT.text")) // NOI18N
        checkBoxUseXFT.setName("checkBoxUseXFT") // NOI18N

        checkBoxUppercase.setText(resourceMap.getString("checkBoxUppercase.text")) // NOI18N
        checkBoxUppercase.setName("checkBoxUppercase") // NOI18N

        checkBoxOverrideUTF8.setText(resourceMap.getString("checkBoxOverrideUTF8.text")) // NOI18N
        checkBoxOverrideUTF8.setName("checkBoxOverrideUTF8") // NOI18N

        checkBoxShortUnits.setText(resourceMap.getString("checkBoxShortUnits.text")) // NOI18N
        checkBoxShortUnits.setName("checkBoxShortUnits") // NOI18N

        jComboBoxFont.setEditable(true)
        jComboBoxFont.setName("jComboBoxFont") // NOI18N
        jComboBoxFont.setPreferredSize(new java.awt.Dimension(250, 25))

        jTextFieldXFTFont.setText(resourceMap.getString("jTextFieldXFTFont.text")) // NOI18N
        jTextFieldXFTFont.setName("jTextFieldXFTFont") // NOI18N
        jTextFieldXFTFont.setPreferredSize(new java.awt.Dimension(250, 25))

        jButtonXFontsel.setText(resourceMap.getString("jButtonXFontsel.text")) // NOI18N
        jButtonXFontsel.setToolTipText(resourceMap.getString("jButtonXFontsel.toolTipText")) // NOI18N
        jButtonXFontsel.setName("jButtonXFontsel") // NOI18N
        jButtonXFontsel.addActionListener(new java.awt.event.ActionListener {
            def actionPerformed(evt: java.awt.event.ActionEvent) {
                jButtonXFontselActionPerformed(evt)
            }
        })

        val lPadPercents = new javax.swing.JLabel( resourceMap.getString("lPadPercents.text") )
        val lTextBufferSize = new javax.swing.JLabel( resourceMap.getString("lTextBufferSize.text") )
        val lBytes = new javax.swing.JLabel( resourceMap.getString("lBytes.text") )
        val lMaxUserText = new javax.swing.JLabel( resourceMap.getString("lMaxUserText.text") )
        val lBytes2 = new javax.swing.JLabel( resourceMap.getString("lBytes2.text") )
        val lFont = new javax.swing.JLabel( resourceMap.getString("lFont.text") )
        val lXFTAlpha = new javax.swing.JLabel( resourceMap.getString("lXFTAlpha.text") )
        val lXFTFont = new javax.swing.JLabel( resourceMap.getString("lXFTFont.text") )

        // add all the components
        this.rowEnd( checkBoxUppercase )
        this.rowEnd( checkBoxOverrideUTF8 )
        this.rowEnd( checkBoxShortUnits )

        this.rowAppend( lPadPercents, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerPadPercents )

        this.rowAppend( lTextBufferSize, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerTextBufferSize )
        this.rowEnd( lBytes )
        
        this.rowAppend( lMaxUserText, java.awt.GridBagConstraints.EAST )
        this.rowAppend( spinnerMaxUserText )
        this.rowEnd( lBytes2 )

        this.rowAppend( lFont, java.awt.GridBagConstraints.EAST )
        val cellWidth = 2
        this.rowAdd( jComboBoxFont, cellWidth )
        this.rowEnd( new java.awt.Label )

        this.rowAppend( checkBoxUseXFT, java.awt.GridBagConstraints.EAST )
        this.rowEnd( new java.awt.Label )

        this.rowAppend( lXFTAlpha, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerXFTAlpha )

        
        this.rowAppend( lXFTFont, java.awt.GridBagConstraints.EAST )
        this.rowAdd( jTextFieldXFTFont, cellWidth )
        this.rowEnd( jButtonXFontsel )

        this.alignLeftTopCorner
    }

    private def jButtonXFontselActionPerformed(evt: java.awt.event.ActionEvent) {
        this.jTextFieldXFTFont.setText( models.SystemFont.xfontsel )
    }

    def isUppercase: String = this.checkBoxUppercase.isSelectedCheckBox
    
    def setUppercase(value: String) {
        this.checkBoxUppercase.setSelected(value)
    }

    def isOverrideUTF8: String = this.checkBoxOverrideUTF8.isSelectedCheckBox
    
    def setOverrideUTF8(value: String) {
        this.checkBoxOverrideUTF8.setSelected(value)
    }

    def isUseXFT: String = this.checkBoxUseXFT.isSelectedCheckBox
    
    def setUseXFT(value: String) {
        this.checkBoxUseXFT.setSelected(value)
    }

    def isShortUnits: String = this.checkBoxShortUnits.isSelectedCheckBox
    
    def setShortUnits(value: String) {
        this.checkBoxShortUnits.setSelected(value)
    }

    def getFontConky: String =  this.jComboBoxFont.getSelectedItem.toString
    
    def setFontConky(font: String) {
        if(font == null || font.isEmpty) {
            return
        } else {
            this.jComboBoxFont.setSelectedItem(font)
            val size = this.jComboBoxFont.getItemCount
            for( i <- List.range(0, size) ) {
                if(this.jComboBoxFont.getItemAt(i).equals(font)) {
                    return
                }
            }
            this.jComboBoxFont.addItem(font)
        }
    }

    def getXFTFont: String = this.jTextFieldXFTFont.getText
    
    def setXFTFont(font: String) {
        this.jTextFieldXFTFont.setText(font)
    }

    def getMaxUserText: String = this.spinnerMaxUserText.getValue.toString
    
    def setMaxUserText(value: Int) {
        this.spinnerMaxUserText.setValue(value)
    }

    def getXFTAlpha: String = this.spinnerXFTAlpha.getValue.toString
    
    def setXFTAlpha(value: Double) {
        var v = 0.0
        if( value > 1.0) v = 1.0
        if( value < 0.0) v = 0.0
        this.spinnerXFTAlpha.setValue(v)
    }

    def getTextBufferSize: String = this.spinnerTextBufferSize.getValue.toString
    
    def setTextBufferSize(value: Int) {
        this.spinnerTextBufferSize.setValue(value)
    }

    def getPadPercents: String = this.spinnerPadPercents.getValue.toString
    
    def setPadPercents(value: Int) {
        this.spinnerPadPercents.setValue(value)
    }

    def reset {
        this.setUppercase(CheckBox.NO)
        this.setOverrideUTF8(CheckBox.NO)
        this.setShortUnits(CheckBox.NO)
        this.setPadPercents(0)
        this.setTextBufferSize(128)
        this.setMaxUserText(16384)
        this.setFontConky("Bitstream Charter:style=Regular")
        this.setUseXFT(CheckBox.NO)
        this.setXFTAlpha(0.000)
        this.setXFTFont("")
    }

    private def loadFonts {
        val fonts = models.SystemFont.fcList
        for(index <- List.range(0, fonts.length) ) {
            this.jComboBoxFont.addItem(fonts(index))
        }
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.checkBoxOverrideUTF8.setEnabled(value)
        this.checkBoxShortUnits.setEnabled(value)
        this.checkBoxUppercase.setEnabled(value)
        this.checkBoxUseXFT.setEnabled(value)
        this.jButtonXFontsel.setEnabled(value)
        this.jComboBoxFont.setEnabled(value)
        this.jTextFieldXFTFont.setEnabled(value)
        this.spinnerMaxUserText.setEnabled(value)
        this.spinnerPadPercents.setEnabled(value)
        this.spinnerTextBufferSize.setEnabled(value)
        this.spinnerXFTAlpha.setEnabled(value)
    }
}
