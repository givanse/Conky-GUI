/* 
 * Copyrigh 2012 Gastón Silva
 * This program is distributed under the terms of the GNU General Public License
 * 
 * This file is part of ConkyGUI.
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

import views.panels.PanelConkyEditor
import java.awt.event.ActionEvent
import java.util.HashMap
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.event.UndoableEditEvent
import javax.swing.event.UndoableEditListener
import javax.swing.text.Document
import javax.swing.text.EditorKit
import javax.swing.text.JTextComponent
import javax.swing.text.StyledEditorKit
import javax.swing.undo.CannotRedoException
import javax.swing.undo.CannotUndoException
import javax.swing.undo.UndoManager

/**
 * DocumentListener code:
 * http://java.sun.com/docs/books/tutorial/uiswing/components/textarea.html
 * 
 */
class ConkyTextPane(conkyEditor: PanelConkyEditor)
extends javax.swing.JTextPane {

  //text pane actions map
  private val actions: HashMap[Object, Action] = this.createActionTable(this)
  //undo helpers
  //Undo and redo are actions of our own creation.
  private val undoAction = new UndoAction()
  private val redoAction = new RedoAction()
  private val undoManager = new UndoManager()

  this.addStyledSyntaxDocument
  //Add DocumentListener
  this.getStyledDocument.addDocumentListener(
    new WordCompletionDocListener(this))
  this.getDocument.addUndoableEditListener(new MyUndoableEditListener())

  private def addStyledSyntaxDocument {
      val jConkyTextEditor = this
      val editorKit = new StyledEditorKit() {
          override def createDefaultDocument: Document = {
              return new custom.swing.text.StyledDocumentConky(jConkyTextEditor)
          }
      }
      val contentType = "conky"
      this.setEditorKitForContentType(contentType, editorKit)
      this.setContentType(contentType)
  }

  /**
   * Loads an array of actions into a HashMap so the program can retrieve an action by name.
   *
   * http://java.sun.com/docs/books/tutorial/uiswing/components/generaltext.html#commands
   *
   * @param textComponent, The text component from where actions are going to be retrieved.
   * @return A HashMap that contains all the actions from the JTextComponent.
   */
  private def createActionTable(textComponent: JTextComponent): HashMap[Object, Action] = {
      val actionsMap = new HashMap[Object, Action]()
      val actionsArray: Array[Action] = textComponent.getActions
      for (a <- actionsArray) {
          actionsMap.put(a.getValue(Action.NAME), a)
      }
      return actionsMap
  }

  def getActionByName(name: String): Action = {
      return this.actions.get(name)
  }

  def getUndoAction: UndoAction = {
      return this.undoAction
  }

  def getRedoAction: RedoAction = {
      return this.redoAction
  }

  def handleTextChangedEvent {
      this.conkyEditor.updateCurrentFilePath(null, false)
  }

  /*
   * INNER CLASSES
   */
  /**
   * http://java.sun.com/docs/books/tutorial/uiswing/components/generaltext.html#undo
   */
  private class MyUndoableEditListener extends UndoableEditListener {

      override def undoableEditHappened(e: UndoableEditEvent) {
          //Remember the edit and update the menus
          if( !e.getEdit.getPresentationName.equals("style change") ) {
              //System.out.println(e.getEdit.getPresentationName)
              undoManager.addEdit(e.getEdit)
              undoAction.updateUndoState
              redoAction.updateRedoState
          } else {
              //System.out.println(" => Style edit")
          }
      }
  }

  class UndoAction extends AbstractAction("Undo") {

      this.setEnabled(false)

      override def actionPerformed(e: ActionEvent) {
          try {
              undoManager.undo
          } catch {
              case ex: CannotUndoException =>
                  System.out.println("Unable to undo: " + ex)
                  ex.printStackTrace
          }
          updateUndoState
          redoAction.updateRedoState
      }

      def updateUndoState {
          if (undoManager.canUndo) {
              this.setEnabled(true)
              this.putValue(Action.NAME, undoManager.getUndoPresentationName)
          } else {
              this.setEnabled(false)
              this.putValue(Action.NAME, "Undo")
          }
      }
  }

  class RedoAction extends AbstractAction("Redo") {

      this.setEnabled(false)

      override def actionPerformed(e: ActionEvent) {
          try {
              undoManager.redo
          } catch {
              case ex: CannotRedoException =>
                  System.out.println("Unable to redo: " + ex)
                  ex.printStackTrace
          }
          updateRedoState
          undoAction.updateUndoState
      }

      def updateRedoState {
          if (undoManager.canRedo) {
              setEnabled(true)
              putValue(Action.NAME, undoManager.getRedoPresentationName)
          } else {
              setEnabled(false)
              putValue(Action.NAME, "Redo")
          }
      }
  }
}
