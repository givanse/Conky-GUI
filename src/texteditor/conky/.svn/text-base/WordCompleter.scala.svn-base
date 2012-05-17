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

package texteditor.conky;

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.Collections
import javax.swing.AbstractAction
import javax.swing.ActionMap
import javax.swing.InputMap
import javax.swing.KeyStroke
import javax.swing.SwingUtilities
import javax.swing.text.BadLocationException
import custom.swing.ErrorPane

class WordCompleter(textEditor: ConkyTextPane, offset: Int, text: String) extends ActionListener {

  private var showCompletion = false;
  private val COMMIT_COMPLETION = "commit completion";
  //InputMap
  val inputMap = this.textEditor.getInputMap();
  inputMap.put(KeyStroke.getKeyStroke("ENTER"), COMMIT_COMPLETION);
  //ActionMap
  val actionMap = this.textEditor.getActionMap();
  actionMap.put(COMMIT_COMPLETION, new CommitCompletion());

  /**
   * Processes an update
   * @param arg0
   */
  def actionPerformed(arg0: ActionEvent) {
    val prefixStart = this.getPrefixStart(this.text, this.offset)
    if (prefixStart == -1) {
      return;
    } else {
      this.processPrefix(this.offset, prefixStart, this.text.substring(prefixStart + 1).toLowerCase());
    }
  }

  def processPrefix(offset: Int, prefixStart: Int, prefix: String) {
    val prefixIndex = this.getPrefixIndex(prefix);
    //System.out.println(" prefix: " + prefix + ", " + prefixIndex);
    if (this.isPrefixIndexValid(prefixIndex)) {
      val matchWord = models.ConkyVariable.LIST.get(prefixIndex);
      //System.out.println(" match: " + match + "\n");
      if (matchWord.startsWith(prefix)) {
        // A completion is found
        val completion = matchWord.substring(offset - prefixStart)
        // We cannot modify Document from within notification,
        // so we submit a task that does the change later (threadsafe)
        SwingUtilities.invokeLater(new ShowCompletion(completion, offset + 1))
      }
    }
  }

  /**
   *
   * @param text
   * @param offset
   * @return The starting offset of a prefix
   */
  private def getPrefixStart(text: String, offset: Int ):Int = {
    var o = offset
    val list = List.range(0, offset).reverse
    for (o <-list ) {
      var tmpChar = text.charAt(o);
      //Only complete words that start right after a opening bracket {
      if (Character.isWhitespace(tmpChar)) {
        return -1;
      } else {
        if (tmpChar == '{') {
          if (text.charAt(o - 1) == '$') {
            return o
          }
        }
      }
    }
    return o
  }

  /**
   *
   * @param prefix
   * @return The position inside the list of KEYWORDS_LIST
   */
  private def getPrefixIndex(prefix: String):Int = {
    val prefixIndex = Collections.binarySearch(models.ConkyVariable.LIST, prefix) //the return value will be >= 0 if and only if the key is found.
    if (prefixIndex < 0) {
      //The key was not found, so the element before it is retrieved
      return -prefixIndex - 1//Makes it a positive number and is decreased by one
    } else {
      //The key was found
      return prefixIndex+1
    }
  }

  private def isPrefixIndexValid(prefixIndex: Int):Boolean = {
    if (prefixIndex >= 0 && prefixIndex < models.ConkyVariable.LIST.size) {
      return true
    } else {
      return false
    }
  }

  /*
   *
   * Inner Classes
   *
   */

  private class ShowCompletion(strCompletion: String, position: Int) extends Runnable {

    override def run {
      try {
        textEditor.getDocument().insertString(position, strCompletion, null);
      } catch {
        case ex: BadLocationException =>
          ErrorPane.showErrorMessage(
            "JConkyTextEditor, CompletionTask: ", ex);
      }
      textEditor.setCaretPosition(position + strCompletion.length());
      textEditor.moveCaretPosition(position);
      showCompletion = true;
    }
  }

  private class CommitCompletion extends AbstractAction {

    override def actionPerformed(ev: ActionEvent) {
      if (showCompletion) {
        val selectionEnd = textEditor.getSelectionEnd();
        try {
          textEditor.getDocument().insertString(selectionEnd, " ", null);
        } catch {
          case ex: BadLocationException =>
            ErrorPane.showErrorMessage(
              "JConkyTextEditor, CommitCompletion: ", ex);
        }
        textEditor.setCaretPosition(selectionEnd + 1);
        showCompletion = false;
      } else {
        //The usual (default) behavior for the "Enter" key
        textEditor.replaceSelection("\n");
      }
    }
  }

}