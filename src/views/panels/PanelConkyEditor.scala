/*
 *  Copyrigh 2012 Gastón Silva
 *  This program is distributed under the terms of the GNU General def License
 * 
 *  This file is part of ConkyGUI.
 * 
 *  ConkyGUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General def License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ConkyGUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General def License for more details.
 *
 *  You should have received a copy of the GNU General def License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package views.panels

import texteditor.conky.ConkyFileWriter
import texteditor.conky.ConkyTextPane
import java.awt.Color
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.JScrollPane
import javax.swing.border.TitledBorder
import listeners.ExecuteFileListener

/**
 * This panel wraps the ConkyTextPane and a ToolBar that contains
 * the refresh and stop buttons.
 *
 * It has a panel that changes its border title according to the name
 * of the file that is being edited.
 *
 */
class PanelConkyEditor(exeFileListener: ExecuteFileListener)
extends docking.DockablePanel("Editor") {
  
    private val panelConkyEditor: PanelConkyEditor = this
    private val conkyTextPane = new ConkyTextPane(this)
    private var filePath = ""
    private val conkyFileWriter = new ConkyFileWriter
    private val jButtonStop = new javax.swing.JButton
    private val jButtonRefresh = new javax.swing.JButton
    private val borderPanel = new javax.swing.JPanel

    this.initComponents
    this.updateCurrentFilePath(models.Path.DEFAULT_CONKY_FILE, false)
    this.jButtonRefresh.addActionListener(exeFileListener)

    private def initComponents {
      this.setName("Form")
      val resourceMap =
        org.jdesktop.application.Application.
        getInstance(classOf[controllers.ConkyGUI]).getContext.
        getResourceMap(classOf[PanelConkyEditor])
      this.buildBorderPanel(resourceMap)
      this.add( borderPanel )
      this.add( this.buildToolBar(resourceMap) )
    }

    private def buildBorderPanel(
    resourceMap: org.jdesktop.application.ResourceMap) {
      this.borderPanel.setBorder(javax.swing.BorderFactory.
        createTitledBorder(null, "The title will be set after initComponents",
        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        javax.swing.border.TitledBorder.DEFAULT_POSITION,
        resourceMap.getFont("jPanel2.border.titleFont"), Color.RED))
      this.borderPanel.setName("jPanel2") // NOI18N
      this.borderPanel.setLayout(
        new javax.swing.BoxLayout(
          this.borderPanel, javax.swing.BoxLayout.Y_AXIS))
      val sp = new JScrollPane
      sp.setViewportView(conkyTextPane)
      borderPanel.add(sp)
    }

    private def buildToolBar(
    resourceMap: org.jdesktop.application.ResourceMap):
    javax.swing.JToolBar = {
        val jToolBar = new javax.swing.JToolBar
        jToolBar.setFloatable(false)
        jToolBar.setRollover(true)
        jToolBar.setMaximumSize(new java.awt.Dimension(32767, 100))
        jToolBar.setMinimumSize(new java.awt.Dimension(242, 44))
        jToolBar.setPreferredSize(new java.awt.Dimension(400, 40))
        //jToolBar1.setLayout(
          //new javax.swing.BoxLayout(jToolBar, javax.swing.BoxLayout.X_AXIS))

        jButtonStop.setIcon(resourceMap.getIcon("jButtonStop.icon"))
        jButtonStop.setMnemonic('s')
        jButtonStop.setText(resourceMap.getString("jButtonStop.text"))
        jButtonStop.setFocusable(false)
        jButtonStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER)
        jButtonStop.setMaximumSize(new java.awt.Dimension(38, 38))
        jButtonStop.setMinimumSize(new java.awt.Dimension(38, 38))
        jButtonStop.setName("jButtonStop")
        jButtonStop.setPreferredSize(new java.awt.Dimension(38, 38))
        jButtonStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM)
        jButtonStop.addActionListener(new java.awt.event.ActionListener {
            def actionPerformed(evt: java.awt.event.ActionEvent) {
              panelConkyEditor.stop
            }
          })
        jToolBar.add(jButtonStop)

        jButtonRefresh.setIcon(resourceMap.getIcon("jButtonRefresh.icon"))
        jButtonRefresh.setText(resourceMap.getString("jButtonRefresh.text"))
        jButtonRefresh.setFocusable(false)
        jButtonRefresh.setHorizontalTextPosition(
          javax.swing.SwingConstants.CENTER)
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(38, 38))
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(38, 38))
        jButtonRefresh.setName("jButtonRefresh")
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(38, 38))
        jButtonRefresh.setVerticalTextPosition(
          javax.swing.SwingConstants.BOTTOM)
        jToolBar.add(jButtonRefresh)
        return jToolBar
    }

    private def updateCurrentFilePathColor(fileSaved: Boolean) {
        val titledBorder: TitledBorder =
          this.borderPanel.getBorder.asInstanceOf[TitledBorder]
        if(fileSaved) {
            titledBorder.setTitleColor(Color.BLACK)
        } else {
            titledBorder.setTitleColor(Color.RED)
        }
        this.borderPanel.updateUI
    }

    /**
     *                       Public Functions
     */
    def saveFile(isSaveAs: Boolean, comments: String): String = {
        val TEXT = this.conkyTextPane.getText
        var path = models.Path.DEFAULT_CONKY_FILE
        if(isSaveAs) {
            path = this.conkyFileWriter.saveFile("", comments, TEXT)
            this.updateCurrentFilePath(path, true)
        } else {
            path = this.conkyFileWriter.saveFile(this.filePath, comments, TEXT)
            this.updateCurrentFilePathColor(true)
        }
        return path
    }

    def stop {
        this.exeFileListener.stopConky
    }

    def updateCurrentFilePath(path: String, fileSaved: Boolean) {
      if( path != null) {
        if(!path.isEmpty || !path.equals( models.Path.DEFAULT_CONKY_FILE )) {
          this.filePath = path
          // plus one because its inclusive
          val fileName = path.substring( path.lastIndexOf('/')+1 )
          val titledBorder: TitledBorder =
            this.borderPanel.getBorder.asInstanceOf[TitledBorder]
          titledBorder.setTitle(fileName)
        }
      }
      this.updateCurrentFilePathColor(fileSaved)
    }

    def getFilePath: String = this.filePath
    
    def reset {
        this.updateCurrentFilePath(models.Path.DEFAULT_CONKY_FILE, false)
        this.setText("")
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.borderPanel.setEnabled(value)
        this.jButtonStop.setEnabled(value)
        this.jButtonRefresh.setEnabled(value)
    }

    def isFileEdited: Boolean = {
        val titledBorder: TitledBorder =
          this.borderPanel.getBorder.asInstanceOf[TitledBorder]
        return titledBorder.getTitleColor == Color.RED
    }

    def setText(text: String) {
        this.conkyTextPane.setText(text)
    }

    def getUndoAction: AbstractAction =
      this.conkyTextPane.getUndoAction

    def getRedoAction: AbstractAction =
      this.conkyTextPane.getRedoAction
    
    def getActionByName(name: String): Action =
      this.conkyTextPane.getActionByName(name)
    
}