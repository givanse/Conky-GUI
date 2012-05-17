package listeners

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import views.windows.ConkyGUI
import views.windows.Preferences

class ClosePreferencesWindowListener(conkyGUI: ConkyGUI) extends WindowAdapter with ActionListener with WindowListener {

    private var preferencesWindow: Preferences = null;

    def setPreferences(preferencesWindow: Preferences) {
        this.preferencesWindow = preferencesWindow
    }
    
    def actionPerformed(arg0: ActionEvent) {
        this.hidePreferencesWindow
    }

    override def windowClosing(e: WindowEvent) {
        this.hidePreferencesWindow
    }

    private def hidePreferencesWindow {
        this.preferencesWindow.setVisible(false)
        this.conkyGUI.setEnabled(true)
    }

}