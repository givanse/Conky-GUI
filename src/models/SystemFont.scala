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

import java.io.BufferedReader
import java.io.InputStreamReader

object SystemFont {

    def fcList: Array[String] = {
        val command = Array("/bin/sh", "-c", "fc-list")
        var output = new scala.collection.mutable.ListBuffer[String]
        try {
            val process = Runtime.getRuntime.exec(command)
            val br = new BufferedReader(new InputStreamReader(process.getInputStream))
            var line = br.readLine
            while ( line != null ) {
                output += line
                line = br.readLine
            }
            process.destroy
            br.close
        } catch {
            case ex: Exception =>
                println("mesg "+ex.getMessage)
                println("cause "+ex.getCause)
                println("lmesg "+ex.getLocalizedMessage)
                println(ex.getStackTrace.mkString("\n"))
        }
        return output.toArray
    }

    def xfontsel: String = {
        val command = Array("/bin/sh", "-c", "xfontsel -geometry 800x300 -print")
        var output = ""
        try {
            val process = Runtime.getRuntime.exec(command);
            val br = new BufferedReader(new InputStreamReader(process.getInputStream))
            var line = br.readLine
            while (line != null) {
                output = line
                line = br.readLine
            }
            process.destroy
            br.close
        } catch {
            case ex: Exception =>
                println("mesg "+ex.getMessage)
                println("cause "+ex.getCause)
                println("lmesg "+ex.getLocalizedMessage)
                println(ex.getStackTrace.mkString("\n"))
        }
        return output
    }

}
