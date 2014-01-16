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

package views.dialogs

import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JOptionPane
import java.awt.Frame
import javax.swing.JLabel
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * About singleton.
 */
object About {

  private var instance: About = null;

  def getInstance: About = {
    if( About.instance == null ) {
      val cga = controllers.ConkyGUI.getApplication
      val mainFrame = cga.getMainFrame
      About.instance = new AboutBox( mainFrame )
    }
    return About.instance
  }
}

/**
 * About interface.
 */
trait About extends javax.swing.JDialog {
    //TODO possible bad smell.
    /**
     * This class extends from JDialog so its subclasses can use their methods,
     * even when they are referenced by a variable of type About.
     *
     * Solves the visibility of methods reduction when you assign the reference
     * of a subclass (a Child)to a variable declared with the type of
     * a supertype (a Parent).
     */
}

/**
 * About implementation.
 */
private class AboutBox(parent: Frame)
extends custom.swing.GridBagDialog(parent) with About {

  val context = org.jdesktop.application.Application.
      getInstance(classOf[controllers.ConkyGUI]).getContext
  val resourceMap = context.getResourceMap(classOf[About])

  this.init

  /**
   * PRIVATE METHODS
   */

  private def init {
    this.setLocationRelativeTo( this.parent )
    this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE)
    this.setTitle(resourceMap.getString("title"))
    this.setPreferredSize(new java.awt.Dimension(500, 250))
    this.setResizable(false)
    //this.setModal(true)
    // turn off bsaf xml save state feature
    // http://kenai.com/projects/bsaf/forums/open-discussion/topics/2845-How-to-Disable-the-Session-State-Feature-
    // val ps: org.jdesktop.application.session.PropertySupport = null
    // context.getSessionStorage.registerPropertySupport(classOf[AboutBox], ps);

    val appTitleLabel = new JLabel
    appTitleLabel.setFont(
      appTitleLabel.getFont.deriveFont(
        appTitleLabel.getFont.getStyle | java.awt.Font.BOLD,
        appTitleLabel.getFont.getSize+4))
    appTitleLabel.setText(resourceMap.getString("Application.title"))
    appTitleLabel.setName("appTitleLabel")

    val versionLabel = new JLabel
    versionLabel.setFont(
      versionLabel.getFont.deriveFont(
        versionLabel.getFont.getStyle | java.awt.Font.BOLD))
    versionLabel.setText(resourceMap.getString("versionLabel.text"))
    versionLabel.setName("versionLabel")

    val appVersionLabel = new JLabel
    appVersionLabel.setText(resourceMap.getString("Application.version"))
    appVersionLabel.setName("appVersionLabel")

    val homepageLabel = new JLabel
    homepageLabel.setFont(homepageLabel.getFont.deriveFont(homepageLabel.getFont.getStyle | java.awt.Font.BOLD))
    homepageLabel.setText(resourceMap.getString("homepageLabel.text"))
    homepageLabel.setName("homepageLabel")

    val appHomepageLabel = new JLabel
    appHomepageLabel.setText(resourceMap.getString("Application.homepage"))
    appHomepageLabel.setName("appHomepageLabel")

    val appDescLabel = new JLabel
    appDescLabel.setText(resourceMap.getString("appDescLabel.text"))
    appDescLabel.setName("appDescLabel")

    val imageLabel = new JLabel
    imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon"))
    imageLabel.setName("imageLabel")

    val buttonsDimension = new java.awt.Dimension(100, 25)

    val buttonCredits = new javax.swing.JButton
    buttonCredits.setPreferredSize( buttonsDimension )
    buttonCredits.setMnemonic('r')
    buttonCredits.setText(resourceMap.getString("buttonCredits.text"))
    buttonCredits.setName("buttonCredits")
    buttonCredits.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            buttonCreditsActionPerformed(evt)
        }
    })

    val buttonLicense = new javax.swing.JButton
    buttonLicense.setPreferredSize( buttonsDimension )
    buttonLicense.setMnemonic('l')
    buttonLicense.setText(resourceMap.getString("buttonLicense.text"))
    buttonLicense.setName("buttonLicense")
    buttonLicense.addActionListener(new java.awt.event.ActionListener {
        def actionPerformed(evt: ActionEvent) {
            buttonLicenseActionPerformed(evt)
        }
    })

    val buttonClose = new javax.swing.JButton
    buttonClose.setPreferredSize( buttonsDimension )
    buttonClose.setMnemonic('c')
    buttonClose.setText(resourceMap.getString("buttonClose.text"))
    buttonClose.setName("buttonClose")
    buttonClose.addActionListener(new java.awt.event.ActionListener {
        def actionPerformed(evt: ActionEvent) {
            setVisible(false)
        }
    })

    // add components
    val cellHeigth = 6
    this.columnAdd( imageLabel, cellHeigth )
    this.rowEnd( appTitleLabel )
    this.rowEnd( appDescLabel )

    this.rowEnd( versionLabel )
    this.rowEnd( appVersionLabel )

    this.rowEnd( homepageLabel )
    this.rowEnd( appHomepageLabel )

    this.rowAppend( buttonCredits, java.awt.GridBagConstraints.EAST)
    this.rowAppend( buttonLicense, java.awt.GridBagConstraints.EAST)
    this.rowEnd( buttonClose, java.awt.GridBagConstraints.EAST)
  }

  private def buttonCreditsActionPerformed(evt: ActionEvent) {
    val credits =
            "                    ConkyGUI - Gastón I. Silva Echegaray\n" +
            "                          Icons - vistaicons.com\n" +
            "                         Layout - vlsolutions.com/en/products/docking/\n" +
            "Application Framework - kenai.com/projects/bsaf"
    val icon = resourceMap.getIcon("construction.icon")
    JOptionPane.showMessageDialog(
      this, credits, "Credits", JOptionPane.INFORMATION_MESSAGE, icon)
  }

  private def buttonLicenseActionPerformed(evt: ActionEvent) {
    val license =
        "                          GNU GENERAL PUBLIC LICENSE\n" +
        "                               Version 3, 29 June 2007\n\n" +
        "Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>\n" +
        "    Everyone is permitted to copy and distribute verbatim copies\n" +
        "       of this license document, but changing it is not allowed."
    val icon = resourceMap.getIcon("gpl.icon")
    JOptionPane.showMessageDialog(
      this, license, "License", JOptionPane.INFORMATION_MESSAGE, icon)
  }
}
