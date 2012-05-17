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

package models

import java.awt.Color
import java.awt.Font
import javax.swing.text.MutableAttributeSet
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

object FontAttributeSet {

    val NORMAL = new SimpleAttributeSet
    StyleConstants.setForeground(NORMAL, Color.BLACK)
    StyleConstants.setBold(NORMAL, true)
    StyleConstants.setFontSize(NORMAL, 13)
    StyleConstants.setFontFamily(NORMAL, Font.MONOSPACED)

    // attributes for the conky editor
    val KEY = createColoredAttributeSet(NORMAL, CustomColor.PINK)
    val VAR_DELIM = createColoredAttributeSet(NORMAL, Color.GRAY)
    val VAR_ARGS = createColoredAttributeSet(NORMAL, Color.GREEN.darker())
    val COMMENT = createColoredAttributeSet(NORMAL, Color.BLUE )

    // attributes for the preferences window (shell parser)
    val COMMAND = createColoredAttributeSet(NORMAL, Color.BLACK)
    val FILE_TOKEN = createColoredAttributeSet(COMMAND, Color.RED)
    val END_COMMAND = createColoredAttributeSet(COMMAND, Color.LIGHT_GRAY)

    private def createColoredAttributeSet(
        baseAttributeSet: MutableAttributeSet,
        color: Color): MutableAttributeSet = {
        val attributeSet = new SimpleAttributeSet(baseAttributeSet)
        StyleConstants.setForeground(attributeSet, color)
        return attributeSet
    }

}
