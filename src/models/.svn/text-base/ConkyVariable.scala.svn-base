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

import java.io.FileNotFoundException
import java.io.IOException
import scala.xml.Elem
import scala.xml.XML
import scala.io.Source
import java.util.ArrayList
import java.util.Collections

//TODO I don't remmeber why I have this here
/*
 * http://stubbisms.wordpress.com/2009/02/18/fighting-scala-scala-to-java-list-conversion/
 */
//implicit def listToJavaList[T](l: List[T]) = java.util.Arrays.asList(l.toArray: _*)


object ConkyVariable {

    val LIST: java.util.ArrayList[String] = ConkyVariable.getVariablesList

    private def getVariablesList:ArrayList[String] = {
        val numConkyObjects = 250 //TODO just a guess
        val list = new ArrayList[String](numConkyObjects)
        try {
            val source = Source.fromFile( Path.VARIABLES_FILE )
            val xmlString = source.getLines.mkString
            val xml:Elem = XML.loadString(xmlString)
            // varlistentry \ term \ command \ option
            for( nodeSeq <- (xml \ "varlistentry" \ "term" \ "command" \ "option") ) {
                list.add(nodeSeq.text)
            }
            Collections.sort(list)
            list.trimToSize
            return list
        } catch {
            case ex: FileNotFoundException =>
                println(ex.getMessage)
            case ex: IOException =>
                println(ex.getMessage)
        }
        return list
    }
}