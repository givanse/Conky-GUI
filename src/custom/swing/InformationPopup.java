/*
 * 
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

import java.awt.Component;
import javax.swing.Popup;
import javax.swing.SwingWorker;

public class InformationPopup extends Popup {
    
    private final long popupDuration = 700;//milliseconds
    private InformationPanel panel;
    private Popup popup;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 75;

    public InformationPopup(Component owner, InformationPanel panel) {
        super(owner, panel, owner.getX() + owner.getWidth()/2 - InformationPopup.WIDTH/2, owner.getY() + owner.getHeight()/2 - InformationPopup.HEIGHT );
        this.panel = panel;
        this.popup = this;
    }

    public void show(String message) {
        this.panel.setMessage(message);
        this.show();
    }

    @Override
    public void show() {
        super.show();
        HidePopup hidePopup = new HidePopup();
        hidePopup.execute();
    }

    private class HidePopup extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                Thread.sleep(popupDuration);
            } catch (InterruptedException ex) {
                ErrorPane.showErrorMessage("InformationPopup: ", ex);
            }
            popup.hide();
            return "";
        }
    }
}
