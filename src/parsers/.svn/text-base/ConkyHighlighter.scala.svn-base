/*
 *  Copyrigh 2012 Gastón I. Silva E.
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

import java.awt.event.ActionEvent
import javax.swing.text.BadLocationException
import custom.swing.ErrorPane
import models.FontAttributeSet
import custom.swing.text.StyledDocumentConky

class ConkyHighlighter(doc: StyledDocumentConky, offset: Int, strLength: Int)
extends java.awt.event.ActionListener {

  private val conkyParser: ConkyParser = new ConkyParser();

  override def actionPerformed(arg0: ActionEvent) {
    try {
      val allText: String = this.doc.getText(0, this.doc.getLength());
      this.processChangedLines(this.offset, this.strLength, allText);
    } catch {
      case ex: BadLocationException =>
        ErrorPane.showErrorMessage("ConkyHighlighter: ", ex);
    }
  }

  private def processChangedLines(offset: Int, strLength: Int, allText: String) {
    val ast = this.conkyParser.parse(allText) // a list filled with Nodes
    for(node <- ast) {
      node match {
        case node: Comment =>
          this.doc.setCharacterAttributes(
              node.offset, node.string.length, FontAttributeSet.COMMENT, false);
        case node: Key =>
          this.doc.setCharacterAttributes(
              node.offset, 1, FontAttributeSet.VAR_DELIM, false);
          this.doc.setCharacterAttributes(
              node.offset+1, node.string.length, FontAttributeSet.KEY, false);
        case node: Var =>
          this.doc.setCharacterAttributes(
              node.offset, 2, FontAttributeSet.VAR_DELIM, false);
          this.doc.setCharacterAttributes(
              node.offset+2, node.string.length-1, FontAttributeSet.VAR_ARGS, false);
          this.doc.setCharacterAttributes(
              node.offset+node.string.length-1, 1, FontAttributeSet.VAR_DELIM, false);
          val key = node.key.string;
          highlightKeyword(node.key)
        case node: Txt =>
          this.doc.setCharacterAttributes(
              node.offset, node.string.length, FontAttributeSet.NORMAL, false);
      }
    }
  }

  private def highlightKeyword(key: Key) {
    if( models.ConkyVariable.LIST.contains(key.string) ) {
      this.doc.setCharacterAttributes(
          key.offset, key.string.length, FontAttributeSet.KEY, false);
    }
  }
}
