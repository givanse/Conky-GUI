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

package views.windows

import models.UserPreference
import views.preferences._
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.WindowListener
import listeners.ClosePreferencesWindowListener

/**
 * Preference singleton.
 */
object Preferences {

  private var instance: Preferences = null

  def getInstance(cgui: ConkyGUI): Preferences = {
    if( Preferences.instance == null ) {
      val cpwl = new ClosePreferencesWindowListener( cgui )
      Preferences.instance = new PreferencesWindow(cpwl)
      cpwl.setPreferences( Preferences.instance )
    }
    return Preferences.instance
  }
}

/**
 * Preferences interface.
 */
trait Preferences extends javax.swing.JFrame {}

/**
 * Preferences implementation.
 */
private class PreferencesWindow(cpwl: ClosePreferencesWindowListener)
extends Preferences {

    private val userPreference = UserPreference.getInstance
    private val panelText = PanelText.getInstance
    private val panelExecution = PanelExecution.getInstance
    
    this.panelText.setComment(this.userPreference.getComments)
    this.panelExecution.setCommand(this.userPreference.getCommand)

    private val jButtonOK = new javax.swing.JButton
    private val jButtonCancel = new javax.swing.JButton

    this.initComponents
    this.jButtonCancel.addActionListener(cpwl)
    this.jButtonOK.addActionListener(cpwl)
    this.addWindowListener(cpwl)

    /**
     * PRIVATE METHODS
     */

    private def initComponents {

        val jTabbedPane = new javax.swing.JTabbedPane
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE)
        
        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[Preferences])
        setTitle(resourceMap.getString("Form.title")) // NOI18N
        setIconImage(resourceMap.getImageIcon("Form.icon").getImage)
        setName("Form") // NOI18N
        setResizable(false)

        jButtonOK.setMnemonic('o')
        jButtonOK.setText(resourceMap.getString("jButtonOK.text")) // NOI18N
        jButtonOK.setName("jButtonOK") // NOI18N
        jButtonOK.setPreferredSize(new java.awt.Dimension(75, 25))
        jButtonOK.addActionListener(new java.awt.event.ActionListener {
            def actionPerformed(evt: java.awt.event.ActionEvent) {
                PreferencesWindow.this.updatePreferencesSet
            }
        })

        jButtonCancel.setMnemonic('c')
        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")) // NOI18N
        jButtonCancel.setName("jButtonCancel") // NOI18N

        jTabbedPane.setName("jTabbedPane") // NOI18N
        jTabbedPane.addKeyListener(new java.awt.event.KeyAdapter {
            override def keyTyped(evt: java.awt.event.KeyEvent) {
                jTabbedPaneKeyTyped(evt)
            }
        })

        panelText.setName("panelText") // NOI18N
        jTabbedPane.addTab(resourceMap.getString("panelText.TabConstraints.tabTitle"), panelText) // NOI18N

        panelExecution.setName("panelExecution") // NOI18N
        jTabbedPane.addTab(resourceMap.getString("panelExecution.TabConstraints.tabTitle"), panelExecution) // NOI18N

        val layout = new javax.swing.GroupLayout(getContentPane)
        getContentPane.setLayout(layout)
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup
                .addContainerGap(263, java.lang.Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 416, java.lang.Short.MAX_VALUE)
        )
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 335, java.lang.Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel))
                .addContainerGap)
        )
        this.pack
        val d = Toolkit.getDefaultToolkit.getScreenSize
        val x = (d.width / 2) - (this.getWidth / 2)
        val y = (d.height / 2) - (this.getHeight / 2)
        this.setLocation(x, y)
    }

    private def jTabbedPaneKeyTyped(evt: java.awt.event.KeyEvent) {
        if(evt.getKeyChar==KeyEvent.VK_ESCAPE) {
            this.jButtonCancel.doClick
        }
    }

    private def updatePreferencesSet {
        // command
        val command = this.panelExecution.getCommand
        this.userPreference.setCommand(command)
        // comment
        val comment = this.panelText.getComments
        this.userPreference.setComments(comment)
    }

}