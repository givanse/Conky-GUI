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

import java.awt.Dimension

/**
 * PanelText singleton.
 */
object PanelText {

    private var instance: PanelText = null

    def getInstance: PanelText = {
        if( PanelText.instance == null ) {
            PanelText.instance = new Text
        }
        return PanelText.instance
    }
}

/**
 * PanelText interface.
 */
trait PanelText extends javax.swing.JPanel {
    // TODO maybe delete this methods
    def getCommentDateTime: String
    def getComments: String
    def setComment(text: String)
}

/**
 * PanelText implementation.
 */
private class Text extends PanelText {

   private val textAreaComments = new javax.swing.JTextArea
   private val commentDateTime = new javax.swing.JTextField

   this.initComponents

   private def initComponents {

        val jLabel2 = new javax.swing.JLabel
        val jScrollPane1 = new javax.swing.JScrollPane
        val jLabel1 = new javax.swing.JLabel
        val commentLabelSize = new Dimension(8,20)
        val jLabel5 = new javax.swing.JLabel
        val jLabel6 = new javax.swing.JLabel
        val jLabel7 = new javax.swing.JLabel
        val jLabel8 = new javax.swing.JLabel
        val jLabel9 = new javax.swing.JLabel
        val jLabel10 = new javax.swing.JLabel
        val jLabel11 = new javax.swing.JLabel

        setName("Form") // NOI18N

        commentDateTime.setText( models.Time.getSystemDate )
        val resourceMap =
            org.jdesktop.application.Application.getInstance(classOf[controllers.ConkyGUI]).getContext.getResourceMap(classOf[views.preferences.PanelText])
        commentDateTime.setToolTipText(resourceMap.getString("commentDateTime.toolTipText"))

        jLabel2.setText(resourceMap.getString("jLabel2.text")) // NOI18N
        jLabel2.setToolTipText(resourceMap.getString("jLabel2.toolTipText")) // NOI18N
        jLabel2.setName("jLabel2") // NOI18N

        jScrollPane1.setName("jScrollPane1") // NOI18N

        textAreaComments.setColumns(20)
        textAreaComments.setRows(5)
        textAreaComments.setText(resourceMap.getString("textAreaComments.text")) // NOI18N
        textAreaComments.setName("textAreaComments") // NOI18N
        jScrollPane1.setViewportView(textAreaComments)

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")) // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")) // NOI18N
        jLabel1.setName("jLabel1") // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")) // NOI18N
        jLabel5.setMaximumSize(commentLabelSize)
        jLabel5.setMinimumSize(commentLabelSize)
        jLabel5.setName("jLabel5") // NOI18N
        jLabel5.setPreferredSize(commentLabelSize)

        jLabel6.setText(resourceMap.getString("jLabel6.text")) // NOI18N
        jLabel6.setName("jLabel6") // NOI18N
        jLabel6.setPreferredSize(commentLabelSize)

        jLabel7.setText(resourceMap.getString("jLabel7.text")) // NOI18N
        jLabel7.setName("jLabel7") // NOI18N
        jLabel7.setPreferredSize(commentLabelSize)

        jLabel8.setText(resourceMap.getString("jLabel8.text")) // NOI18N
        jLabel8.setName("jLabel8") // NOI18N
        jLabel8.setPreferredSize(commentLabelSize)

        jLabel9.setText(resourceMap.getString("jLabel9.text")) // NOI18N
        jLabel9.setName("jLabel9") // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")) // NOI18N
        jLabel10.setName("jLabel10") // NOI18N
        jLabel10.setPreferredSize(commentLabelSize)

        jLabel11.setText(resourceMap.getString("jLabel11.text")) // NOI18N
        jLabel11.setName("jLabel11") // NOI18N
        jLabel11.setPreferredSize(commentLabelSize)

        val layout = new javax.swing.GroupLayout(this)
        this.setLayout(layout)
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commentDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 334, java.lang.Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, java.lang.Short.MAX_VALUE)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        )
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 25, java.lang.Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commentDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, java.lang.Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel1)
                .addGap(93, 93, 93))
        )
    }

    /**
     * PUBLIC METHODS
     */

    def getCommentDateTime: String = '#' + this.commentDateTime.getText

    def getComments: String = "\n#"+this.textAreaComments.getText.replaceAll("\n", "\n#")

    def setComment(text: String) {
        val newText = text.replaceFirst("\n#", "").replaceAll("\n#", "\n")
        this.textAreaComments.setText(newText)
    }

}