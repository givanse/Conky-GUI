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
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package views.panels

import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JScrollPane
import javax.swing.JTextPane

// It allows you to read the properties file
private class PanelLua {}

object PanelLua extends docking.DockablePanel("Lua") {

  private val textPane = new JTextPane

  this.initComponents

  private def initComponents {
    this.setName("Lua") // NOI18N
    this.setLayout(
      new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS))
    val resourceMap = org.jdesktop.application.Application.getInstance(
      classOf[controllers.ConkyGUI]).getContext.
      getResourceMap(classOf[PanelLua])
    this.getDockKey.setIcon( new ImageIcon(
      getClass.getResource(resourceMap.getString("JPanelLua.icon"))))
    this.setPreferredSize(new Dimension(350,200))

    val sp = new JScrollPane
    sp.setViewportView(this.textPane)

    this.add(sp)
  }

  /**
   * PUBLIC METHODS
   */

  /**
   * Resets this component to a initial state, the default values.
   */
  def reset { this.textPane.setText("") }

  override def setEnabled(value: Boolean) { super.setEnabled(value) }

  def setText(text: String) { this.textPane.setText(text) }

  def appendLine(text: String) {
    val txt = this.textPane.getText
    if(txt.isEmpty)
      this.textPane.setText(text)
    else
      this.textPane.setText(txt+"\n"+text)
  }

  def getText: String = this.textPane.getText
    
}