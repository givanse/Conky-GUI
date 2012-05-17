/*
 * 
 *  Copyrigh 2008 Gastón I. Silva E.
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

package views.panels;

import java.awt.Dimension;
import java.awt.Toolkit;

/** Creates new form ShellWindow */
public class PanelShell extends javax.swing.JFrame {

    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;

    public PanelShell() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() / 2) - (this.getWidth() / 2));
        int y = (int) ((screenSize.getHeight() / 2) - (this.getHeight() / 2));
        this.setLocation(x, y);
        this.pack();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(controllers.ConkyGUI.class).getContext().getResourceMap(PanelShell.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea.setColumns(60);
        jTextArea.setEditable(false);
        jTextArea.setRows(10);
        jTextArea.setName("jTextArea"); // NOI18N
        jScrollPane1.setViewportView(jTextArea);

        this.getContentPane().add(jScrollPane1);

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 250));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 30, 5));
        this.getContentPane().add(jPanel1);

        pack();
    }

    public void setText(String str) {
        this.jTextArea.setText(str);
    }

    public String getText() {
        return this.jTextArea.getText();
    }

    public void append(String str) {
        this.jTextArea.append(str);
    }

}