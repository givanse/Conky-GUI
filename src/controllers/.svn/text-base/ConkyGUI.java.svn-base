/**
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

package controllers;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 * It only starts up the application in a thread safe way by calling the ConkyGUI view.
 */
public class ConkyGUI extends SingleFrameApplication {

    private views.windows.ConkyGUI conkyGUIView;

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        this.conkyGUIView = new views.windows.ConkyGUI( this );
        super.show(this.conkyGUIView);
    }

    /**
     * Does all the logic needed after receiving the EXIT event, but
     * before exiting the application.
     *
     * @param event
     */
    @Override
    public void exit(java.util.EventObject event) {
        this.conkyGUIView.saveConkyGUIPreferences();
        System.out.println("Ok");
        super.exit(event);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
        JFrame mainFrame = this.getMainFrame();
        // TODO change to a resource in a propertie file
        String conkyguiIcon = "/images/about.png";
        URL iconURL = this.getClass().getResource(conkyguiIcon);
        mainFrame.setIconImage(new ImageIcon(iconURL).getImage());
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ConkyguiApp
     */
    public static ConkyGUI getApplication() {
        return Application.getInstance(ConkyGUI.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            ConkyGUI.launch(controllers.ConkyGUI.class, args);
        } else {
            models.ConkyGUI.handleArguments(args);
        }
    }

}