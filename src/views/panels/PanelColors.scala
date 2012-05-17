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

package views.panels

import java.awt.Color
import javax.swing.GroupLayout

// It allows you to read the properties file
private class PanelColors {}

/**
 * A JPanelColors has the controlls of type ColosButton that allow to
 * set all the color variables for Conky.
 */
object PanelColors extends docking.DockableView("Colors") {

    private val bcDefaultColor = new custom.swing.ColorButton
    private val bcDefaultOutlineColor = new custom.swing.ColorButton
    private val bcDefaultShadeColor = new custom.swing.ColorButton
    private val bc0 = new custom.swing.ColorButton
    private val bc1 = new custom.swing.ColorButton
    private val bc2 = new custom.swing.ColorButton
    private val bc3 = new custom.swing.ColorButton
    private val bc4 = new custom.swing.ColorButton
    private val bc5 = new custom.swing.ColorButton
    private val bc6 = new custom.swing.ColorButton
    private val bc7 = new custom.swing.ColorButton
    private val bc8 = new custom.swing.ColorButton
    private val bc9 = new custom.swing.ColorButton

    this.initComponents
    this.reset
    
    private def initComponents {

        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelColors])
        this.bcDefaultColor.setText(resourceMap.getString("colorLabelDefaultColor.text")) // NOI18N
        this.bcDefaultColor.setName("colorLabelDefaultColor") // NOI18N

        this.bcDefaultOutlineColor.setText(resourceMap.getString("colorLabelDefaultOutlineColor.text")) // NOI18N
        this.bcDefaultOutlineColor.setName("colorLabelDefaultOutlineColor") // NOI18N

        this.bcDefaultShadeColor.setText(resourceMap.getString("colorLabelDefaultShadeColor.text")) // NOI18N
        this.bcDefaultShadeColor.setName("colorLabelDefaultShadeColor") // NOI18N

        this.bc0.setText(resourceMap.getString("colorLabel0.text")) // NOI18N
        this.bc0.setName("colorLabel0") // NOI18N

        this.bc1.setText(resourceMap.getString("colorLabel1.text")) // NOI18N
        this.bc1.setName("colorLabel1") // NOI18N

        this.bc2.setText(resourceMap.getString("colorLabel2.text")) // NOI18N
        this.bc2.setName("colorLabel2") // NOI18N

        this.bc3.setText(resourceMap.getString("colorLabel3.text")) // NOI18N
        this.bc3.setName("colorLabel3") // NOI18N

        this.bc4.setText(resourceMap.getString("colorLabel4.text")) // NOI18N
        this.bc4.setName("colorLabel4") // NOI18N

        this.bc5.setText(resourceMap.getString("colorLabel5.text")) // NOI18N
        this.bc5.setName("colorLabel5") // NOI18N

        this.bc6.setText(resourceMap.getString("colorLabel6.text")) // NOI18N
        this.bc6.setName("colorLabel6") // NOI18N

        this.bc7.setText(resourceMap.getString("colorLabel7.text")) // NOI18N
        this.bc7.setName("colorLabel7") // NOI18N

        this.bc8.setText(resourceMap.getString("colorLabel8.text")) // NOI18N
        this.bc8.setName("colorLabel8") // NOI18N

        this.bc9.setText(resourceMap.getString("colorLabel9.text")) // NOI18N
        this.bc9.setName("colorLabel9") // NOI18N

        // add all the ColorButton objects
        this.setLayoutInsets(0, 0, 0, 0)
        this.rowEnd( this.bcDefaultColor )
        this.rowEnd( this.bcDefaultOutlineColor )
        this.rowEnd( this.bcDefaultShadeColor )

        this.rowAppend( this.bc0 )
        this.rowEnd( this.bc5 )

        this.rowAppend( this.bc1 )
        this.rowEnd( this.bc6 )

        this.rowAppend( this.bc2 )
        this.rowEnd( this.bc7 )

        this.rowAppend( this.bc3 )
        this.rowEnd( this.bc8 )

        this.rowAppend( this.bc4 )
        this.rowEnd( this.bc9 )

        this.alignLeftTopCorner
    }

    // getters
    def getDefaultColor: String = this.bcDefaultColor.getColorRGB

    def getDefOutlineColor: String = this.bcDefaultOutlineColor.getColorRGB
    
    def getDefShadeColor: String = this.bcDefaultShadeColor.getColorRGB
    
    def getColor0: String = this.bc0.getColorRGB
    
    def getColor1: String = this.bc1.getColorRGB
    
    def getColor2: String = this.bc2.getColorRGB
    
    def getColor3: String = this.bc3.getColorRGB
    
    def getColor4: String = this.bc4.getColorRGB
    
    def getColor5: String = this.bc5.getColorRGB
    
    def getColor6: String = this.bc6.getColorRGB
    
    def getColor7: String = this.bc7.getColorRGB
    
    def getColor8: String = this.bc8.getColorRGB
    
    def getColor9: String = this.bc9.getColorRGB

    // setters
    def setDefaultColor(color: Color) { this.bcDefaultColor.setColor(color) }

    def setDefOutlineColor(color: Color) { this.bcDefaultOutlineColor.setColor(color) }

    def setDefShadeColor(color: Color) { this.bcDefaultShadeColor.setColor(color) }

    def setColor0(color: Color) { this.bc0.setColor(color) }

    def setColor1(color: Color) { this.bc1.setColor(color) }

    def setColor2(color: Color) { this.bc2.setColor(color) }

    def setColor3(color: Color) { this.bc3.setColor(color) }

    def setColor4(color: Color) { this.bc4.setColor(color) }

    def setColor5(color: Color) { this.bc5.setColor(color) }

    def setColor6(color: Color) { this.bc6.setColor(color) }

    def setColor7(color: Color) { this.bc7.setColor(color) }

    def setColor8(color: Color) { this.bc8.setColor(color) }

    def setColor9(color: Color) { this.bc9.setColor(color) }

    def reset {
        this.bcDefaultColor.setColor(Color.BLACK)
        this.bcDefaultOutlineColor.setColor(Color.WHITE)
        this.bcDefaultShadeColor.setColor(Color.LIGHT_GRAY)
        this.bc0.setColor(Color.RED)
        this.bc1.setColor(Color.BLUE)
        this.bc2.setColor(Color.YELLOW)
        this.bc3.setColor(Color.GREEN)
        this.bc4.setColor(Color.PINK)
        this.bc5.setColor(Color.ORANGE)
        this.bc6.setColor(Color.MAGENTA)
        this.bc7.setColor(Color.CYAN)
        this.bc8.setColor(Color.GRAY)
        this.bc9.setColor(Color.DARK_GRAY)

    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.bcDefaultColor.setEnabled(value)
        this.bcDefaultOutlineColor.setEnabled(value)
        this.bcDefaultShadeColor.setEnabled(value)
        this.bc0.setEnabled(value)
        this.bc1.setEnabled(value)
        this.bc2.setEnabled(value)
        this.bc3.setEnabled(value)
        this.bc4.setEnabled(value)
        this.bc5.setEnabled(value)
        this.bc6.setEnabled(value)
        this.bc7.setEnabled(value)
        this.bc8.setEnabled(value)
        this.bc9.setEnabled(value)
    }
}
