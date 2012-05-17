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

package custom.swing;

import javax.swing.JOptionPane;

/**
 *
 * @author gaston
 */
public class ErrorPane {

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showErrorMessage(String message, Exception ex) {
        message += "\nMessage:\n" + ex.getMessage();
        int numLines = 4;
        if( ex.getStackTrace().length < numLines) {
            numLines = ex.getStackTrace().length;
        }
        message += "\nStack Trace:";
        for (int i = 0; i < numLines; i++) {
            message += "\n" + ex.getStackTrace()[i];
        }
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showErrorMessageComplete(String message, Exception ex) {
        message += "\n" + ex.getMessage();
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            message += "\n" + ex.getStackTrace()[i];
        }
        JOptionPane.showMessageDialog(null, message);
    }
}
