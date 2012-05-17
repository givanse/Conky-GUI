/*
 *  Copyrigh 2010 Gastón I. Silva E.
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

import javax.swing.JTextField
import javax.swing.SpinnerNumberModel
import javax.swing.JSpinner

// It allows you to read the properties file
private class PanelNet {}

object PanelNet extends docking.DockableView("Net") {

    private val textFieldMailSpool = new JTextField
    // POP3
    private val textFieldPOP3Host = new JTextField
    private val textFieldPOP3User = new JTextField
    private val textFieldPOP3Pass = new JTextField
    private val spinnerPOP3Interval = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val spinnerPOP3Port = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val textFieldPOP3Command = new JTextField
    // IMAP
    private val textFieldIMAPHost = new JTextField
    private val textFieldIMAPUser = new JTextField
    private val textFieldIMAPPass = new JTextField
    private val spinnerIMAPInterval = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val spinnerIMAPPort = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val textFieldIMAPCommand = new JTextField
    private val textFieldIMAPFolder = new JTextField
    // MPD
    private val textFieldMPDHost = new JTextField
    private val spinnerMPDPort = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1))
    private val textFieldMPDPass = new JTextField
   
    this.initComponents
    this.reset

    private def initComponents {

        setName("Form") // NOI18N

        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelNet])
        
        textFieldMailSpool.setText(resourceMap.getString("textFieldMailSpool.text")) // NOI18N
        textFieldMailSpool.setPreferredSize(new java.awt.Dimension(250, 25))

        val lMailSpool = new javax.swing.JLabel(resourceMap.getString("lMailSpool.text"))

        // init and add the 3 network panels
        val jTabbedPane = new javax.swing.JTabbedPane
        jTabbedPane.addTab("POP3", this.initComponentsPOP3(resourceMap))
        jTabbedPane.addTab("IMAP", this.initComponentsIMAP(resourceMap))
        jTabbedPane.addTab("MPD", this.initComponentsMPD(resourceMap))

        // add all the components
        this.rowAppend(lMailSpool, java.awt.GridBagConstraints.EAST)
        this.rowEnd(textFieldMailSpool)

        this.rowEnd(jTabbedPane)

        this.alignLeftTopCorner
    }

    // init and return the POP3 panel
    private def initComponentsPOP3(
    resourceMap: org.jdesktop.application.ResourceMap): javax.swing.JPanel = {

        val panelPOP3 = new custom.swing.GridBagPanel
        val lPOP3Host = new javax.swing.JLabel(resourceMap.getString("lPOP3Host.text"))
        val lPOP3User = new javax.swing.JLabel(resourceMap.getString("lPOP3User.text"))
        val lPOP3Pass = new javax.swing.JLabel(resourceMap.getString("lPOP3Pass.text"))
        val lPOP3Interval = new javax.swing.JLabel(resourceMap.getString("lPOP3Interval.text"))
        val lPOP3Minutes = new javax.swing.JLabel(resourceMap.getString("lPOP3Minutes.text"))
        val lPOP3Port = new javax.swing.JLabel(resourceMap.getString("lPOP3Port.text"))
        val lPOP3Command = new javax.swing.JLabel(resourceMap.getString("lPOP3Command.text"))

        panelPOP3.setBorder(javax.swing.BorderFactory.createTitledBorder("POP3"))
        
        this.textFieldPOP3Host.setText(resourceMap.getString("textFieldPOP3Host.text")) // NOI18N
        this.textFieldPOP3Host.setPreferredSize(new java.awt.Dimension(250, 25))

        this.textFieldPOP3User.setText(resourceMap.getString("textFieldPOP3User.text")) // NOI18N
        this.textFieldPOP3User.setPreferredSize(new java.awt.Dimension(250, 25))

        this.textFieldPOP3Pass.setText(resourceMap.getString("textFieldPOP3Pass.text")) // NOI18N
        this.textFieldPOP3Pass.setPreferredSize(new java.awt.Dimension(250, 25))

        this.spinnerPOP3Interval.setName("spinnerPOP3Interval") // NOI18N

        this.spinnerPOP3Port.setName("spinnerPOP3Port") // NOI18N

        this.textFieldPOP3Command.setText(resourceMap.getString("textFieldPOP3Command.text")) // NOI18N
        this.textFieldPOP3Command.setPreferredSize(new java.awt.Dimension(250, 25))

        // add components

        panelPOP3.rowAppend( lPOP3Host, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowEnd( this.textFieldPOP3Host )

        panelPOP3.rowAppend( lPOP3User, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowEnd( this.textFieldPOP3User )

        panelPOP3.rowAppend( lPOP3Pass, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowEnd( this.textFieldPOP3Pass )

        panelPOP3.rowAppend( lPOP3Interval, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowAppend( this.spinnerPOP3Interval )
        panelPOP3.rowEnd( lPOP3Minutes )

        panelPOP3.rowAppend( lPOP3Port, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowEnd( this.spinnerPOP3Port )

        panelPOP3.rowAppend( lPOP3Command, java.awt.GridBagConstraints.EAST )
        panelPOP3.rowEnd( this.textFieldPOP3Command )
        panelPOP3.alignLeftTopCorner
        return panelPOP3
    }

    // init and return the IMAP panel
    private def initComponentsIMAP(
    resourceMap: org.jdesktop.application.ResourceMap): javax.swing.JPanel = {

        val panelIMAP = new custom.swing.GridBagPanel
        val lIMAPHost = new javax.swing.JLabel(resourceMap.getString("lIMAPHost.text"))
        val lIMAPUser = new javax.swing.JLabel(resourceMap.getString("lIMAPUser.text"))
        val lIMAPPass = new javax.swing.JLabel(resourceMap.getString("lIMAPPass.text"))
        val lIMAPInterval = new javax.swing.JLabel(resourceMap.getString("lIMAPInterval.text"))
        val lIMAPMinutes = new javax.swing.JLabel(resourceMap.getString("lIMAPMinutes.text"))
        val lIMAPPort = new javax.swing.JLabel(resourceMap.getString("lIMAPPort.text"))
        val lIMAPCommand = new javax.swing.JLabel(resourceMap.getString("lIMAPCommand.text"))
        val lIMAPFolder = new javax.swing.JLabel(resourceMap.getString("lIMAPFolder.text"))
        
        panelIMAP.setBorder(javax.swing.BorderFactory.createTitledBorder("IMAP"))
        panelIMAP.setName("jPanel3") // NOI18N

        textFieldIMAPHost.setText(resourceMap.getString("textFieldIMAPHost.text")) // NOI18N
        textFieldIMAPHost.setPreferredSize(new java.awt.Dimension(250, 25))

        textFieldIMAPUser.setText(resourceMap.getString("textFieldIMAPUser.text")) // NOI18N
        textFieldIMAPUser.setPreferredSize(new java.awt.Dimension(250, 25))

        textFieldIMAPPass.setText(resourceMap.getString("textFieldIMAPPass.text")) // NOI18N
        textFieldIMAPPass.setPreferredSize(new java.awt.Dimension(250, 25))

        spinnerIMAPInterval.setName("spinnerIMAPInterval") // NOI18N

        spinnerIMAPPort.setName("spinnerIMAPPort") // NOI18N

        textFieldIMAPCommand.setText(resourceMap.getString("textFieldIMAPCommand.text")) // NOI18N
        textFieldIMAPCommand.setPreferredSize(new java.awt.Dimension(250, 25))

        textFieldIMAPFolder.setText(resourceMap.getString("textFieldIMAPFolder.text")) // NOI18N
        textFieldIMAPFolder.setPreferredSize(new java.awt.Dimension(250, 25))

        // add components
        panelIMAP.rowAppend( lIMAPHost, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.textFieldIMAPHost )
        
        panelIMAP.rowAppend( lIMAPUser, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.textFieldIMAPUser )

        panelIMAP.rowAppend( lIMAPPass, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.textFieldIMAPPass )

        panelIMAP.rowAppend( lIMAPInterval, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowAppend( this.spinnerIMAPInterval )
        panelIMAP.rowEnd( lIMAPMinutes )

        panelIMAP.rowAppend( lIMAPFolder, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.textFieldIMAPFolder )

        panelIMAP.rowAppend( lIMAPPort, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.spinnerIMAPPort )

        panelIMAP.rowAppend( lIMAPCommand, java.awt.GridBagConstraints.EAST )
        panelIMAP.rowEnd( this.textFieldIMAPCommand )
        panelIMAP.alignLeftTopCorner
        return panelIMAP
    }

    // init and return the MPD panel
    private def initComponentsMPD(
    resourceMap: org.jdesktop.application.ResourceMap): javax.swing.JPanel = {

        val panelMPD = new custom.swing.GridBagPanel
        val lMPDHost = new javax.swing.JLabel(resourceMap.getString("lMPDHost.text"))
        val lMPDPort = new javax.swing.JLabel(resourceMap.getString("lMPDPort.text"))
        val lMPDPass = new javax.swing.JLabel(resourceMap.getString("lMPDPass.text"))
        
        panelMPD.setBorder(javax.swing.BorderFactory.createTitledBorder("MPD"))

        this.textFieldMPDHost.setText(resourceMap.getString("textFieldMPDHost.text")) // NOI18N
        this.textFieldMPDHost.setPreferredSize(new java.awt.Dimension(250, 25))

        this.spinnerMPDPort.setName("spinnerMPDPort") // NOI18N

        this.textFieldMPDPass.setText(resourceMap.getString("textFieldMPDPass.text")) // NOI18N
        this.textFieldMPDPass.setPreferredSize(new java.awt.Dimension(250, 25))

        panelMPD.rowAppend(lMPDHost)
        panelMPD.rowEnd(this.textFieldMPDHost)

        panelMPD.rowAppend(lMPDPort)
        panelMPD.rowEnd(this.spinnerMPDPort)

        panelMPD.rowAppend(lMPDPass)
        panelMPD.rowEnd(this.textFieldMPDPass)
        panelMPD.alignLeftTopCorner
        return panelMPD
    }

    // MAIL SPOOLL
    def getMailSpool: String = this.textFieldMailSpool.getText
    
    def setMailSpool(text: String) {
        this.textFieldMailSpool.setText(text)
    }

    // MPD
    def getMPDHost: String = this.textFieldMPDHost.getText
    
    def setMPDHost(text: String) {
        this.textFieldMPDHost.setText(text)
    }

    def getMPDPort: String = this.spinnerMPDPort.getValue.toString
    
    def setMPDPort(value: Int) {
        this.spinnerMPDPort.setValue(value)
    }

    def getMPDPassword: String = this.textFieldMPDPass.getText
    
    def setMPDPassword(text: String) {
        this.textFieldMPDPass.setText(text)
    }

    /**
     * Default global POP3 server. Arguments are: "host user pass [-i interval] [-p port] [-e command]".
     * Default port is 110, default interval is 5 minutes.
     * If the password is supplied as '*', you will be prompted to enter the password when Conky starts.
     * @POP3 values
     */
    def getPOP3: String = {
        if(this.textFieldPOP3Host.getText.isEmpty) {
            ""
        } else {
            this.textFieldPOP3Host.getText + " " + this.textFieldPOP3User.getText + " " + this.textFieldPOP3Pass.getText +
                " -i " + this.spinnerPOP3Interval.getValue.toString + " -p " + this.spinnerPOP3Port.getValue.toString +
                " -e " + this.textFieldPOP3Command.getText
        }
    }

    def setPOP3(host: String, user: String, pass: String, interval: Int, port: Int, command: String) {
        this.textFieldPOP3Host.setText(host); this.textFieldPOP3User.setText(user); this.textFieldPOP3Pass.setText(pass)
        this.spinnerPOP3Interval.setValue(interval); this.spinnerPOP3Port.setValue(port)
        this.textFieldPOP3Command.setText(command)
    }

    /**
     * imap
     * Default global IMAP server. Arguments are: "host user pass [-i interval] [-f folder] [-p port] [-e command]".
     * Default port is 143, default folder is 'INBOX', default interval is 5 minutes.
     * If the password is supplied as '*', you will be prompted to enter the passwap
     * ord when Conky starts.
     * @IMAP values
     */
    def getIMAP: String = {
        if(this.textFieldIMAPHost.getText.isEmpty) {
            ""
        } else {
            this.textFieldIMAPHost.getText + " " + this.textFieldIMAPUser.getText + " " + this.textFieldIMAPPass.getText +
                " -i " + this.spinnerIMAPInterval.getValue.toString + " -f " + this.textFieldIMAPFolder.getText +
                " -p " + this.spinnerIMAPPort.getValue.toString + " -e " + this.textFieldIMAPCommand.getText
        }
    }

    def setIMAP(host: String, user: String, pass: String, interval: Int, folder: String, port: Int, command: String) {
        this.textFieldIMAPHost.setText(host); this.textFieldIMAPUser.setText(user); this.textFieldIMAPPass.setText(pass)
        this.spinnerIMAPInterval.setValue(interval); this.textFieldIMAPFolder.setText(folder); this.spinnerIMAPPort.setValue(port)
        this.textFieldIMAPCommand.setText(command)
    }

    def reset {
        this.setMailSpool("")
        this.setPOP3("", "", "", 5, 110, "")
        this.setIMAP("", "", "", 5, "", 143, "")
        this.setMPDHost("")
        this.setMPDPort(6600)
        this.setMPDPassword("")
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.spinnerIMAPInterval.setEnabled(value)
        this.spinnerIMAPPort.setEnabled(value)
        this.spinnerMPDPort.setEnabled(value)
        this.spinnerPOP3Interval.setEnabled(value)
        this.spinnerPOP3Port.setEnabled(value)
        this.textFieldIMAPCommand.setEnabled(value)
        this.textFieldIMAPFolder.setEnabled(value)
        this.textFieldIMAPHost.setEnabled(value)
        this.textFieldIMAPPass.setEnabled(value)
        this.textFieldIMAPUser.setEnabled(value)
        this.textFieldMPDHost.setEnabled(value)
        this.textFieldMPDPass.setEnabled(value)
        this.textFieldMailSpool.setEnabled(value)
        this.textFieldPOP3Command.setEnabled(value)
        this.textFieldPOP3Host.setEnabled(value)
        this.textFieldPOP3Pass.setEnabled(value)
        this.textFieldPOP3User.setEnabled(value)
    }

}
