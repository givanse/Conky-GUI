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
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General def License for more details.
 *
 *  You should have received a copy of the GNU General def License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package views.panels

import custom.swing.CheckBox
import javax.swing.SpinnerNumberModel

// It allows you to read the properties file
private class PanelConky {}

/**
 * PanelConky is the panel with the most general Conky configuration variables.
 */
object PanelConky extends docking.DockableView("Conky") {

    // default values
    private val fbackground = CheckBox.YES
    private val cpuAvgSamples = 3
    private val maxPortMonitorConnections = 256
    private val musicPlayerInterval = 3
    private val netAvgSamples = 3
    private val noBuffers = CheckBox.YES
    private val outToConsole = CheckBox.NO
    private val topCPUSeparate = CheckBox.NO
    private val updateInterval = 1
    private val totalRunTimes = 0

    // components
    private val spinnerMusicPlayerInterval = new javax.swing.JSpinner
    private val checkBoxBackground = new custom.swing.CheckBox
    private val checkBoxNoBuffers = new custom.swing.CheckBox
    private val checkBoxOutToConsole = new custom.swing.CheckBox
    private val checkBoxTopCPUSeparate = new custom.swing.CheckBox
    private val spinnerMaxPortMonitorConnections = new javax.swing.JSpinner
    private val spinnerCPUAvgSamples = new javax.swing.JSpinner
    private val spinnerNetAvgSamples = new javax.swing.JSpinner
    private val spinnerTotalRunTimes = new javax.swing.JSpinner
    private val spinnerUpdateInterval = new javax.swing.JSpinner

    this.initComponents
    this.reset

    private def initComponents {
        
        this.setName("Form") // NOI18N
        // http://stackoverflow.com/questions/1135248/scala-equivalent-of-java-java-lang-classt-object
        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[PanelConky])


        spinnerMaxPortMonitorConnections.setModel(new SpinnerNumberModel(this.maxPortMonitorConnections, 1, 999, 1));
        spinnerMaxPortMonitorConnections.setName("spinnerMaxPortMonitorConnections"); // NOI18N

        spinnerCPUAvgSamples.setModel(new SpinnerNumberModel(this.cpuAvgSamples, 1, 999, 1));
        spinnerCPUAvgSamples.setName("spinnerCPUAvgSamples"); // NOI18N

        spinnerNetAvgSamples.setModel(new SpinnerNumberModel(this.netAvgSamples, 1, 999, 1));
        spinnerNetAvgSamples.setName("spinnerNetAvgSamples"); // NOI18N

        spinnerTotalRunTimes.setModel(new SpinnerNumberModel(this.totalRunTimes, 0, 999, 1));
        spinnerTotalRunTimes.setName("spinnerTotalRunTimes"); // NOI18N

        spinnerUpdateInterval.setModel(new SpinnerNumberModel(this.updateInterval, 1, 999, 1));
        spinnerUpdateInterval.setName("spinnerUpdateInterval"); // NOI18N

        spinnerMusicPlayerInterval.setModel(new SpinnerNumberModel(this.musicPlayerInterval, 1, 999, 1));
        spinnerMusicPlayerInterval.setName("spinnerMusicPlayerInterval"); // NOI18N

        checkBoxBackground.setText(resourceMap.getString("checkBoxBackground.text")); // NOI18N
        checkBoxBackground.setName("checkBoxBackground"); // NOI18N

        checkBoxNoBuffers.setText(resourceMap.getString("checkBoxNoBuffers.text")); // NOI18N
        checkBoxNoBuffers.setName("checkBoxNoBuffers"); // NOI18N

        checkBoxOutToConsole.setText(resourceMap.getString("checkBoxOutToConsole.text")); // NOI18N
        checkBoxOutToConsole.setName("checkBoxOutToConsole"); // NOI18N

        checkBoxTopCPUSeparate.setText(resourceMap.getString("checkBoxTopCPUSeparate.text")); // NOI18N
        checkBoxTopCPUSeparate.setName("checkBoxTopCPUSeparate"); // NOI18N

        val lMaxPortMonitorConnections = new javax.swing.JLabel(resourceMap.getString("lMaxPortMonitorConnections.text"))
        val lCPUavgSamples = new javax.swing.JLabel(resourceMap.getString("lCPUavgSamples.text"))
        val lNetAvgSamples = new javax.swing.JLabel(resourceMap.getString("lNetAvgSamples.text"))
        val lTotalRunTimes = new javax.swing.JLabel(resourceMap.getString("lTotalRunTimes.text"))
        val lUpdateInterval = new javax.swing.JLabel(resourceMap.getString("lUpdateInterval.text"))
        val lMusicPlayerInterval = new javax.swing.JLabel(resourceMap.getString("lMusicPlayerInterval.text"))

        // add all the objects
        // checkboxes
        this.rowEnd( checkBoxBackground )
        this.rowEnd( checkBoxNoBuffers )
        this.rowEnd( checkBoxOutToConsole )
        this.rowEnd( checkBoxTopCPUSeparate )

        /* spinners */
        this.rowAppend( lMaxPortMonitorConnections, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerMaxPortMonitorConnections, java.awt.GridBagConstraints.WEST )
        
        this.rowAppend( lCPUavgSamples, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerCPUAvgSamples, java.awt.GridBagConstraints.WEST )

        this.rowAppend( lNetAvgSamples, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerNetAvgSamples, java.awt.GridBagConstraints.WEST )

        this.rowAppend( lTotalRunTimes, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerTotalRunTimes, java.awt.GridBagConstraints.WEST )

        this.rowAppend( lUpdateInterval, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerUpdateInterval, java.awt.GridBagConstraints.WEST )

        this.rowAppend( lMusicPlayerInterval, java.awt.GridBagConstraints.EAST )
        this.rowEnd( spinnerMusicPlayerInterval, java.awt.GridBagConstraints.WEST )

        this.alignLeftTopCorner
    }

    def isBackground: String = this.checkBoxBackground.isSelectedCheckBox

    def setBackground(value: String) { this.checkBoxBackground.setSelected(value) }

    def isNoBuffers: String = this.checkBoxNoBuffers.isSelectedCheckBox

    def setNoBuffers(value: String) { this.checkBoxNoBuffers.setSelected(value) }

    def isOutToConsole: String = this.checkBoxOutToConsole.isSelectedCheckBox

    def setOutToConsole(value: String) { this.checkBoxOutToConsole.setSelected(value) }

    def isTopCPUSeparate: String = this.checkBoxTopCPUSeparate.isSelectedCheckBox

    def setTopCPUSeparate(value: String) { this.checkBoxTopCPUSeparate.setSelected(value) }

    def getUpdateInterval: String = this.spinnerUpdateInterval.getValue.toString

    def setUpdateInterval(value: Int) { this.spinnerUpdateInterval.setValue(value) }

    def getTotalRunTimes: String = this.spinnerTotalRunTimes.getValue.toString

    def setTotalRunTimes(value: Int) { this.spinnerTotalRunTimes.setValue(value) }

    def getCPUAvgSamples: String = this.spinnerCPUAvgSamples.getValue.toString
    
    def setCPUAvgSamples(value: Int) { this.spinnerCPUAvgSamples.setValue(value) }

    def getNetAvgSamples: String = this.spinnerNetAvgSamples.getValue.toString

    def setNetAvgSamples(value: Int) { this.spinnerNetAvgSamples.setValue(value) }

    def getMaxPortMonitorConnections: String = this.spinnerMaxPortMonitorConnections.getValue.toString
    
    def setMaxPortMonitorConnections(value: Int) { this.spinnerMaxPortMonitorConnections.setValue(value) }

    def getMusicPlayerInterval: String = this.spinnerMusicPlayerInterval.getValue.toString
    
    def setMusicPlayerInterval(value: Int) { this.spinnerMusicPlayerInterval.setValue(value) }

    def reset {
        this.setBackground(this.fbackground)
        this.setCPUAvgSamples(this.cpuAvgSamples)
        this.setMaxPortMonitorConnections(this.maxPortMonitorConnections)
        this.setMusicPlayerInterval(this.musicPlayerInterval)
        this.setNetAvgSamples(this.netAvgSamples)
        this.setNoBuffers(this.noBuffers)
        this.setOutToConsole(this.outToConsole)
        this.setTopCPUSeparate(this.topCPUSeparate)
        this.setTotalRunTimes(this.totalRunTimes)
        this.setUpdateInterval(this.updateInterval)
    }

    override def setEnabled(value: Boolean) {
        super.setEnabled(value)
        this.checkBoxBackground.setEnabled(value)
        this.checkBoxNoBuffers.setEnabled(value)
        this.checkBoxOutToConsole.setEnabled(value)
        this.checkBoxTopCPUSeparate.setEnabled(value)
        this.spinnerCPUAvgSamples.setEnabled(value)
        this.spinnerMaxPortMonitorConnections.setEnabled(value)
        this.spinnerMusicPlayerInterval.setEnabled(value)
        this.spinnerNetAvgSamples.setEnabled(value)
        this.spinnerTotalRunTimes.setEnabled(value)
        this.spinnerUpdateInterval.setEnabled(value)
    }

}
