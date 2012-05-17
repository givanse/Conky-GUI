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

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class UserPreferenceTest {

    private var instance: UserPreference = null

    @Before
    def setUp {
        this.instance = UserPreference.readFile("empty-preferences.xml")
    }

    @Test
    def testUserPreferenceSingleton {
        val expected = UserPreference.getInstance
        val result   = UserPreference.getInstance
        assertEquals(expected, result);
    }

    @Test
    def testReadUserPreferences {
        val eDefault = UserPreference.readFile("not-found-preferences-file.xml")
        val rDefault = UserPreference.readFile("empty-preferences.xml")
        assertEquals(eDefault, rDefault)

        //val eHandmade = "<comment></comment>"
        //val rHandmade   = UserPreference.readFile("handmade.preferences.xml")
        //assertEquals(eHandmade, rHandmade.toString)
    }

    @Test
    def testToString() {
      val expValue = """<comment>author: conky</comment>
        |<command>killall conky; conky -c $FILE &amp;</command>
        |<file>empty</file>
        |<file>empty</file>
        |<file>empty</file>
        |<file>empty</file>
        |<file>empty</file>
        |<file>empty</file>
        |<file>empty</file>""".stripMargin
      val value = UserPreference.readFile("empty-preferences.xml").toString
      assertEquals(expValue, value)

      val files: String = ""
      //assertEquals(files, this.instance.getFiles.toString)
    }

}