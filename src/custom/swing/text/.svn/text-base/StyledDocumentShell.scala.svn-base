/**
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

import javax.swing.text.AttributeSet
import javax.swing.text.DefaultEditorKit
import javax.swing.text.BadLocationException
import models.FontAttributeSet
import parsers.ShellHighlighter

class StyledDocumentShell extends javax.swing.text.DefaultStyledDocument {

  private val fileHighlightTimer = new texteditor.EditorTimer()

  this.putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");

  //TODO last argument never used?
  override def insertString(offset: Int, str: String, a: AttributeSet) {
    //@throw(classOf[BadLocationException])
    super.insertString(offset, str, FontAttributeSet.COMMAND);
    this.highlight(offset, str.length)
  }

  override def remove(offset: Int, length: Int) {
    super.remove(offset, length)
    this.highlight(offset, length)
  }

  private def highlight(offset: Int, length: Int) {
    this.fileHighlightTimer.restart
    this.fileHighlightTimer.addActionListener(
      new ShellHighlighter(this, offset, length))
  }

}
