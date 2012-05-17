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
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package custom.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 * This button will show a color chooser when its clicked.
 * @author gaston
 */
class ColorButton extends JButton("ColorButton") with ActionListener {

    /**
     * Creates a ColorButton that defaults to white.
     */
    private var color = Color.WHITE
    private var image = this.createColorIcon
    this.addActionListener(this)
    
    /**
     * Changes the color of the button.
     * @param e
     */
    def actionPerformed(e: ActionEvent) {
        val newColor = JColorChooser.showDialog(this,"Select a new color", this.color)
        if(newColor != null) {
            this.setColor(newColor)
        }
    }

    /**
     *
     * @return A square Image filled with the current color and black borders.
     */
    private def createColorIcon: Image = {
        val iconSize = 15//Pixels
        val totalPixels = iconSize * iconSize
        val pixels: Array[Int] = new Array[Int](totalPixels)
        val borderColor = Color.BLACK.getRGB
        for( i <- List.range(0, iconSize)) {
            pixels(i) = borderColor//Top border
        }
        for(i <- List.range(iconSize, totalPixels-iconSize) ) {
            if(i%iconSize == 0 || i%iconSize == (iconSize-1)) {
                // Left and Right border
                pixels(i) = borderColor
            } else {
                // Fill it with the current color
                pixels(i) = color.getRGB
            }
        }
        for(i <- List.range(totalPixels-iconSize, totalPixels)) {//Bottom border
            pixels(i) = borderColor
        }
        val memoryImageSource = new MemoryImageSource(
                iconSize, iconSize, ColorModel.getRGBdefault, pixels, 0, iconSize)
        return this.createImage(memoryImageSource)
    }
    
    override def paint(g: Graphics) {
        super.paint(g)
        if(this.image != null) {
            val xOffset = 9
            val yOffset = 9
            g.drawImage(this.image, xOffset, yOffset, null)
        } else {
            println("ColorButton: Couldn't draw a ColorButton")
        }
    }
    
    override def setText(text: String) {
        val emptySpace = "      "
        val trailingEmptySpace = "  "
        super.setText(emptySpace + text + trailingEmptySpace)
    }

    /**
     * Sets the color of this button.
     * @param color
     */
    def setColor(color: Color) {
        this.color = color
        this.image = this.createColorIcon
        this.updateUI
    }
    
    /**
     * Returns the value of the color of this button
     * representing each of its components with 2 digits in hexadecimal base.
     * Like this: ffffff, 00ff00, 00AA0F etc.
     * @return the selected color in RGB format
     */
    def getColorRGB: String = {
        val r = this.completeToTwoDigits(Integer.toHexString(this.color.getRed))
        val g = this.completeToTwoDigits(Integer.toHexString(this.color.getGreen))
        val b = this.completeToTwoDigits(Integer.toHexString(this.color.getBlue))
        return r + g + b
    }
    
    private def completeToTwoDigits(value: String): String = {
        if (value.length == 1) {
            return "0" + value
        } else {
            return value
        }
    }

}
