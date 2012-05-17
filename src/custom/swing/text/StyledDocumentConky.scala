/*
 *  Copyrigh 2012 Gastón Silva
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

package custom.swing.text

import texteditor.EditorTimer
import javax.swing.text.AttributeSet
import javax.swing.text.BadLocationException
import javax.swing.text.DefaultEditorKit
import javax.swing.text.DefaultStyledDocument
import models.FontAttributeSet
import parsers.ConkyHighlighter

class StyledDocumentConky(textEditor: texteditor.conky.ConkyTextPane)
extends DefaultStyledDocument {

  private val syntaxHighlightTimer = new EditorTimer()

  def ConkyStyledDocument {
    this.putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n")
  }

  /**
   * Auto-completes variables structure (${}) when a '$' is typed.
   * If its other character, it re-starts the SyntaxHiglightTimer.
  */
  override def insertString(offset: Int, str: String, a: AttributeSet) {
    if (str.equals("$")) {
      super.insertString(offset, "$", FontAttributeSet.VAR_DELIM)
      super.insertString(offset + 1, "{", FontAttributeSet.VAR_DELIM)
      super.insertString(offset + 2, "}", FontAttributeSet.VAR_DELIM)
      this.textEditor.setCaretPosition(this.textEditor.getCaretPosition() - 1)
    } else {
      super.insertString(offset, str, FontAttributeSet.NORMAL)
      this.highlight(offset, str.length)
    }
}

  override def remove(offset: Int, length: Int) {
    super.remove(offset, length)
    this.highlight(offset, length)
  }

  private def highlight(offset: Int, length: Int) {
    this.syntaxHighlightTimer.restart
    this.syntaxHighlightTimer.addActionListener(
      new ConkyHighlighter(this, offset, length))
  }
}