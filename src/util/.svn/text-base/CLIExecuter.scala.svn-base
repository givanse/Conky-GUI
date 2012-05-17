/* 
 *  Copyrigh 2012 Gastón I. Silva E.
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

package util

import custom.swing.ErrorPane
import views.panels.PanelShell
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import javax.swing.SwingWorker

class CLIExecuter {

    private val shellWindow = new PanelShell()

    def executeConkyFile(command: String) {
        val shellCommand = Array("/bin/sh", "-c", command)
        this.executeCommand(shellCommand)
    }

    def stopConky {
        val command = Array("/bin/sh", "-c", "killall conky")
        this.executeCommand(command)
    }

    private def executeCommand(command: Array[String]): ExecuteCommand = {
        val exeCommand = new ExecuteCommand(command)
        exeCommand.execute()
        return exeCommand
    }

    /**
     * Worker Thread
     */
    private class ExecuteCommand(command: Array[String]) extends SwingWorker[String, String] {

        shellWindow.setVisible(true)
        
        protected override def doInBackground: String = {
            try {
                val process = Runtime.getRuntime().exec(this.command);
         
                //Print command
                var output = ""//this.command[0]+" ";
                for (c <- this.command) {
                    output += c + "\n"
                }
                shellWindow.setText(output)
                //Read InputStream
                this.ProcessCommandOutput(process.getInputStream())
                //Read ErrorStream
                this.ProcessCommandOutput(process.getErrorStream())
            } catch {
                case ex: Exception =>
                    ErrorPane.showErrorMessage("CommandLineExecuter: \ncommand: " + this.command, ex)
            }
            return shellWindow.getText()
        }

        protected def process(chunks: Array[String]) {
            for(string <- chunks) {
                shellWindow.append(" => " + string + "\n");
            }
        }

        /**
         * Adds the inputStream to the textArea
         * 
         * @param inputStream
         * @throws java.io.IOException
         */
        private def ProcessCommandOutput(inputStream: InputStream) {//throws IOException {
            val br = new BufferedReader(new InputStreamReader(inputStream));
            var text = shellWindow.getText()
            var line = br.readLine
            while(line != null) {
                text += (line + '\n');
                shellWindow.setText(text)
                line = br.readLine
            }
            br.close()
        }
    }
}
