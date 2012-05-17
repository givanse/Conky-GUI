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

package texteditor.conky

import texteditor.EditorTimer
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.text.BadLocationException
import custom.swing.ErrorPane

class WordCompletionDocListener(textEditor: ConkyTextPane) extends DocumentListener {

    private val wordCompletionTimer = new EditorTimer(500)

    override def insertUpdate(docEvent: DocumentEvent) {
        this.textEditor.handleTextChangedEvent
        val lengthOfChange = docEvent.getLength
        if (lengthOfChange == 1) {
            this.wordCompletionTimer.restart
            val offset = docEvent.getOffset
            var text = ""
            try {
                text = this.textEditor.getStyledDocument.getText(0, offset + 1);
            } catch {
                case ex: BadLocationException =>
                    ErrorPane.showErrorMessage("WordCompletionDocumentListener: failed to get text ", ex)
            }
            this.wordCompletionTimer.addActionListener(new WordCompleter(this.textEditor, offset, text))
        }
    }
    
    override def removeUpdate(arg0: DocumentEvent) {
        this.textEditor.handleTextChangedEvent
    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     * @param arg0
     */
    override def changedUpdate(arg0: DocumentEvent) {}
    
}
