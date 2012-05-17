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

package parsers

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import custom.swing.ErrorPane;
import javax.swing.text.Element;
import javax.swing.text.BadLocationException;
import models.FontAttributeSet;
import custom.swing.text.StyledDocumentShell;

class ShellHighlighter(doc: StyledDocumentShell, offset: Int, strLength: Int) extends ActionListener {

    private val shellParser: ShellParser = new ShellParser();

    override def actionPerformed(arg0: ActionEvent) {
        try {
            val allText: String = this.doc.getText(0, this.doc.getLength());
            this.processChangedLines(this.offset, this.strLength, allText);
        } catch {
            case ex: BadLocationException =>
                ErrorPane.showErrorMessage("ShellHighlighter: ", ex);
        }
    }

    private def processChangedLines(offset: Int, strLength: Int, allText: String) {
        val ast = this.shellParser.parse(allText)// a list filled with Nodes
        for(node <- ast) {
            node match {
                case node: EndOp =>
                    this.doc.setCharacterAttributes(node.offset, 1, FontAttributeSet.END_COMMAND, false);
                case node: Arg => // $FILE
                    this.doc.setCharacterAttributes(node.offset, 5, FontAttributeSet.FILE_TOKEN, false);
            }
        }
    }

}