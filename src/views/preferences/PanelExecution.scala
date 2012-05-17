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
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package views.preferences

import custom.swing.text.StyledDocumentShell
import javax.swing.JFrame
import javax.swing.text.Document
import javax.swing.text.EditorKit
import javax.swing.text.StyledEditorKit
import controllers.ConkyGUI

/**
 * PanelExecution singleton.
 */
object PanelExecution {

  private var instance: PanelExecution = null

  def getInstance: PanelExecution = {
    if( PanelExecution.instance == null )
        PanelExecution.instance = new Execution
    return PanelExecution.instance
  }
}

/**
 * PanelExecution interface.
 */
trait PanelExecution extends javax.swing.JPanel {
  def getCommand: String
  def setCommand(command: String)
}

/**
 * PanelExecution implementation.
 */
private class Execution extends PanelExecution {

  private val textPaneCommand = new javax.swing.JTextPane

  this.initComponents
  this.addCommandStyledDocument

  private def initComponents {

    val jLabel2 = new javax.swing.JLabel
    val jLabel3 = new javax.swing.JLabel
    val jLabel1 = new javax.swing.JLabel
    val jLabel4 = new javax.swing.JLabel
    val jScrollPane1 = new javax.swing.JScrollPane
    val jButtonReset = new javax.swing.JButton

    val resourceMap = org.jdesktop.application.Application.
      getInstance(classOf[ConkyGUI]).getContext.
      getResourceMap(classOf[PanelExecution])
    jLabel2.setText(resourceMap.getString("jLabel2.text")) // NOI18N
    jLabel2.setName("jLabel2") // NOI18N

    setName("Form") // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")) // NOI18N
    jLabel3.setName("jLabel3") // NOI18N

    jLabel1.setFont(resourceMap.getFont("jLabel1.font")) // NOI18N
    jLabel1.setText(resourceMap.getString("jLabel1.text")) // NOI18N
    jLabel1.setName("jLabel1") // NOI18N

    jLabel4.setFont(resourceMap.getFont("jLabel4.font")) // NOI18N
    jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")) // NOI18N
    jLabel4.setText(resourceMap.getString("jLabel4.text")) // NOI18N
    jLabel4.setName("jLabel4") // NOI18N

    jScrollPane1.setName("jScrollPane1") // NOI18N

    textPaneCommand.setText(models.UserPreference.DEFAULT_COMMAND)
    textPaneCommand.setName("textPaneCommand") // NOI18N
    jScrollPane1.setViewportView(textPaneCommand)

    jButtonReset.setIcon(resourceMap.getIcon("jButtonReset.icon")) // NOI18N
    jButtonReset.setText(resourceMap.getString("jButtonReset.text")) // NOI18N
    jButtonReset.setName("jButtonReset") // NOI18N
    jButtonReset.addActionListener(new java.awt.event.ActionListener {
        def actionPerformed(evt: java.awt.event.ActionEvent) {
            jButtonResetActionPerformed(evt)
        }
    })

    val layout = new javax.swing.GroupLayout(this)
    this.setLayout(layout)
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup
            .addGap(34, 34, 34)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(33, java.lang.Short.MAX_VALUE))
    )
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup
            .addContainerGap(24, java.lang.Short.MAX_VALUE)
            .addComponent(jLabel3)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)))
            .addGap(35, 35, 35))
    )
  }

  private def jButtonResetActionPerformed(evt: java.awt.event.ActionEvent) {
    this.textPaneCommand.setText(models.UserPreference.DEFAULT_COMMAND)
  }

  private def addCommandStyledDocument {
    val editorKit = new StyledEditorKit {
      override def createDefaultDocument: Document = new StyledDocumentShell
    }
    val contentType = "shell command"
    this.textPaneCommand.setEditorKitForContentType(contentType, editorKit)
    this.textPaneCommand.setContentType(contentType)
  }

  /**
   * PUBLIC METHODS
   */

  def getCommand: String = {
    if(this.textPaneCommand == null)
      return ""
    return this.textPaneCommand.getText
  }

  def setCommand(command: String) { this.textPaneCommand.setText(command) }

}