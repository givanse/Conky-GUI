/*
 *  Copyrigh 2008 Gastón I. Silva E.
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

import org.junit.Test
import org.junit.Assert._
import java.awt.Color

class ConkyColorTest {

    /**
     * Test toColor method.
     */
    @Test
    def testToColor {
        assertEquals(Color.RED, ConkyColor.toColor("red"))
        assertEquals(Color.BLACK, ConkyColor.toColor("black"))
        assertEquals(new Color(0, 0, 0), ConkyColor.toColor("000000"))
        assertEquals(new Color(255, 255, 255), ConkyColor.toColor("FFFFFF"))
        assertEquals(Color.WHITE, ConkyColor.toColor("FFFFFF"))
        assertEquals(new Color(253, 102, 6), ConkyColor.toColor("FD6606"))
    }
    
}
