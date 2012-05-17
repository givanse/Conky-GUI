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

object ConkyColor {

    /**
     * Translates color keywords or hexadecimal values to Color objects.
     *
     * @param color, A color name like "black" or a hexadecimal value as a string
     * @return A Color object equivalent to the one received as a parametrer.
     * If the argument can't be parsed at all the return value is Color.WHITE.
     */
    def toColor(color: String):Color = {
        color.toLowerCase().trim();
        try {
            val r:Int = Integer.decode("0x" + color.charAt(0) + color.charAt(1)).intValue
            val g:Int = Integer.decode("0x" + color.charAt(2) + color.charAt(3)).intValue
            val b:Int = Integer.decode("0x" + color.charAt(4) + color.charAt(5)).intValue
            new Color(r, g, b)
        } catch {
            case ex: NumberFormatException => color match {
                case "black" => Color.BLACK
                case "white" => Color.WHITE
                case "gray" => Color.GRAY
                case "red" => Color.RED
                case "blue" => Color.BLUE
                case "orange" => Color.ORANGE
                case "pink" => Color.PINK
                case "brown" => new Color(158, 90, 0)
                case "cyan" => Color.CYAN
                case "green" => Color.GREEN
                case "yellow" => Color.YELLOW
                case "magenta" => Color.MAGENTA
                case _ => Color.WHITE
            }
        }
    }
    
}