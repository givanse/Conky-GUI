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
 *  but WITHOUT ANY WARRANTY without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Conky GUI.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package views.windows

import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.awt.BorderLayout
import javax.swing.JMenuItem
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JSeparator
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.Timer
import javax.swing.Icon
import javax.swing.JDialog
import javax.swing.JEditorPane
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.BoxLayout
import javax.swing.text.DefaultEditorKit
import org.jdesktop.application.Action
import org.jdesktop.application.FrameView
import org.jdesktop.application.ResourceMap
import org.jdesktop.application.SingleFrameApplication
import org.jdesktop.application.TaskMonitor
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.PrintWriter
import com.vlsolutions.swing.docking._
import views.panels._
import custom.swing.ErrorPane
import custom.swing.InformationPanel
import custom.swing.InformationPopup
import custom.swing.MenuRecentFiles
import models.UserPreference
import texteditor.conky.ConkyFileReader
import listeners.ClosePreferencesWindowListener
import listeners.ExecuteFileListener

/**
 * This is the main frame where everything is initialized and displayed.
 *
 * The application doesn't start from this class,
 * it starts at GUIApp and from there ConkyGUI is called.
 * 
 */
class ConkyGUI(app: SingleFrameApplication) extends FrameView(app) {

  // components
  private val mainPanel: JPanel = new javax.swing.JPanel
  this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS))

  private val userPreference = UserPreference.getInstance
  private val exefl = new ExecuteFileListener(this, this.userPreference)
  private val panelConkyEditor = new PanelConkyEditor( this.exefl )
  this.exefl.setPanelConkyEditor(this.panelConkyEditor)

  // the desktop (which will contain dockables)
  private val dockingDesktop: DockingDesktop = new DockingDesktop

  private val context = org.jdesktop.application.Application.
      getInstance(classOf[controllers.ConkyGUI]).getContext

  // properties
  private val resourceMap = context.getResourceMap(classOf[ConkyGUI])
  // actions
  private val actionMap = context.getActionMap(classOf[ConkyGUI], this)

  // initialiaze menus
  private val menuRecentFiles: MenuRecentFiles = initMenuRecentFiles
  private val menuFile: JMenu = initMenuFile
  private val menuEdit: JMenu = initMenuEdit
  private val menuHelp: JMenu = initMenuHelp
  private val menuRun: JMenu = initMenuRun
  private val menuBar: JMenuBar = initMenuBar

  this.setComponent(mainPanel)
  this.setMenuBar(menuBar)
  this.addDocks

  /*
  private val progressBar: javax.swing.JProgressBar
  private val statusAnimationLabel: JLabel
  private val statusMessageLabel: JLabel
  private val statusPanel: JPanel
  private val busyIconIndex = 0
  status bar initialization - message timeout, idle icon and busy animation, etc
  this.initStatusBar
  */

  /**
   * PRIVATE METHODS
   */

  private def initMenuRecentFiles: MenuRecentFiles = {
    val menuRecentFiles = new MenuRecentFiles( this.panelConkyEditor )
    menuRecentFiles.setName("menuRecentFiles")
    menuRecentFiles.setRecentFiles(this.userPreference.getFiles)
    return menuRecentFiles
  }

  private def initMenuFile: JMenu = {
    val menuItemNewFile = new JMenuItem
    menuItemNewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK))
    menuItemNewFile.setIcon(resourceMap.getIcon("menuItemNewFile.icon"))
    menuItemNewFile.setMnemonic('n')
    menuItemNewFile.setText(resourceMap.getString("menuItemNewFile.text"))
    menuItemNewFile.setName("menuItemNewFile")
    menuItemNewFile.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            ConkyGUI.this.reset
        }
    })

    val menuItemOpen = new JMenuItem
    menuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK))
    menuItemOpen.setIcon(resourceMap.getIcon("menuItemOpen.icon"))
    menuItemOpen.setMnemonic('o')
    menuItemOpen.setText(resourceMap.getString("menuItemOpen.text"))
    menuItemOpen.setName("menuItemOpen")
    menuItemOpen.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            openFile
        }
    })

    val menuItemSave = new javax.swing.JMenuItem
    menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK))
    menuItemSave.setIcon(resourceMap.getIcon("menuItemSave.icon"))
    menuItemSave.setMnemonic('s')
    menuItemSave.setText(resourceMap.getString("menuItemSave.text"))
    menuItemSave.setName("menuItemSave")
    menuItemSave.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            val saveAs = false
            saveFile( saveAs )
        }
    })

    val menuItemSaveAs = new javax.swing.JMenuItem
    menuItemSaveAs.setText(resourceMap.getString("menuItemSaveAs.text"))
    menuItemSaveAs.setName("menuItemSaveAs")
    menuItemSaveAs.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            val saveAs = true
            saveFile( saveAs )
        }
    })

    // menu item quit
    val menuItemQuit = new javax.swing.JMenuItem
    menuItemQuit.setAction(actionMap.get("quit"))
    menuItemQuit.setIcon(resourceMap.getIcon("menuItemQuit.icon"))
    menuItemQuit.setText(resourceMap.getString("menuItemQuit.text"))
    menuItemQuit.setName("menuItemQuit")
    menuItemQuit.setMnemonic('q') // The "quit" action already sets the mnemonic to X by default.

    val menuFile = new javax.swing.JMenu
    menuFile.setMnemonic('f')
    menuFile.setText(resourceMap.getString("menuFile.text"))
    menuFile.setName("menuFile")
    menuFile.add(menuItemNewFile)
    menuFile.add(menuItemOpen)
    menuFile.add(menuRecentFiles)
    menuFile.add( new javax.swing.JSeparator )
    menuFile.add(menuItemSave)
    menuFile.add(menuItemSaveAs)
    menuFile.add( new javax.swing.JSeparator )
    menuFile.add(menuItemQuit)
    return menuFile
  }

  private def initMenuEdit: JMenu = {
    val jMenuItemPreferences = new javax.swing.JMenuItem
    jMenuItemPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK))
    jMenuItemPreferences.setIcon(resourceMap.getIcon("jMenuItemPreferences.icon"))
    jMenuItemPreferences.setMnemonic('p')
    jMenuItemPreferences.setText(resourceMap.getString("jMenuItemPreferences.text"))
    jMenuItemPreferences.setName("jMenuItemPreferences")
    jMenuItemPreferences.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
          val preferencesWindow = Preferences.getInstance(ConkyGUI.this)
          preferencesWindow.setVisible(true)
          setEnabled(false)
        }
      })

    val menuEdit = new javax.swing.JMenu
    menuEdit.setMnemonic('e')
    menuEdit.setText(resourceMap.getString("menuEdit.text"))
    menuEdit.setName("menuEdit")
    menuEdit.add(this.panelConkyEditor.getUndoAction)
    menuEdit.add(this.panelConkyEditor.getRedoAction)
    menuEdit.addSeparator
    menuEdit.add(this.panelConkyEditor.getActionByName(DefaultEditorKit.cutAction))
    menuEdit.add(this.panelConkyEditor.getActionByName(DefaultEditorKit.copyAction))
    menuEdit.add(this.panelConkyEditor.getActionByName(DefaultEditorKit.pasteAction))
    menuEdit.add( new javax.swing.JSeparator )
    menuEdit.add(jMenuItemPreferences)
    return menuEdit
  }

  private def initMenuHelp: JMenu = {
    // HelpOnline
    val helpOnline = new javax.swing.JMenuItem
    helpOnline.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0))
    helpOnline.setIcon(resourceMap.getIcon("helpOnline.icon"))
    helpOnline.setMnemonic('v')
    helpOnline.setText(resourceMap.getString("helpOnline.text"))
    helpOnline.setName("helpOnline")
    helpOnline.addActionListener(new java.awt.event.ActionListener {
        def actionPerformed(evt: ActionEvent) {
            helpOnlineActionPerformed(evt)
        }
    })
    // About
    val about = new JMenuItem
    about.setAction(actionMap.get("showAboutBox"))
    about.setIcon(resourceMap.getIcon("about.icon"))
    about.setText(resourceMap.getString("about.text"))
    about.addActionListener(new java.awt.event.ActionListener {
        def actionPerformed(evt: ActionEvent) {
            val cga = controllers.ConkyGUI.getApplication
            cga.show( views.dialogs.About.getInstance )
        }
    })
    // Menu Help
    val menuHelp = new javax.swing.JMenu
    menuHelp.setMnemonic('h')
    menuHelp.setText(resourceMap.getString("menuHelp.text"))
    menuHelp.setName("menuHelp")
    menuHelp.add(helpOnline)
    menuHelp.add(about)
    return menuHelp
   }

  private def initMenuRun: JMenu = {
    val menuItemRun = new javax.swing.JMenuItem
    menuItemRun.setAccelerator(
      javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK))
    menuItemRun.setIcon(resourceMap.getIcon("menuItemRun.icon"))
    menuItemRun.setMnemonic('r')
    menuItemRun.setText(resourceMap.getString("menuItemRun.text"))
    menuItemRun.setName("menuItemRun")

    val menuItemStop = new javax.swing.JMenuItem
    menuItemStop.setAccelerator(
      javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK))
    menuItemStop.setIcon(resourceMap.getIcon("menuItemStop.icon"))
    menuItemStop.setMnemonic('s')
    menuItemStop.setText(resourceMap.getString("menuItemStop.text"))
    menuItemStop.setName("menuItemStop")
    menuItemStop.addActionListener(new ActionListener {
        def actionPerformed(evt: ActionEvent) {
            panelConkyEditor.stop
        }
    })
    menuItemRun.addActionListener(exefl)

    val menuRun = new javax.swing.JMenu
    menuRun.setMnemonic('r')
    menuRun.setText(resourceMap.getString("menuRun.text"))
    menuRun.setName("menuRun")
    menuRun.add(menuItemRun)
    menuRun.add(menuItemStop)
    return menuRun
  }

  private def initMenuBar: JMenuBar = {
      val menuBar = new javax.swing.JMenuBar
      menuBar.setName("menuBar")
      menuBar.add(menuFile)
      menuBar.add(menuEdit)
      menuBar.add(menuRun)
      menuBar.add(menuHelp)
      return menuBar
  }

  /*statusPanel = new JPanel
  statusPanel.setName("statusPanel")
  statusPanel.setPreferredSize(new java.awt.Dimension(615, 25))

  val statusPanelSeparator = new JSeparator
  statusPanelSeparator.setName("statusPanelSeparator")

  statusMessageLabel = new javax.swing.JLabel
  statusMessageLabel.setName("statusMessageLabel")

  statusAnimationLabel = new javax.swing.JLabel
  statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT)
  statusAnimationLabel.setName("statusAnimationLabel")

  progressBar = new javax.swing.JProgressBar
  progressBar.setName("progressBar")
  */

  //setStatusBar(statusPanel)

  private def addDocks {
    // insert our desktop as the only one component of the frame
    this.getFrame.getContentPane.add(this.dockingDesktop, BorderLayout.CENTER)
    // set the initial dockable
    // This method is used to set the initial component of the desktop.
    this.dockingDesktop.addDockable(this.panelConkyEditor)
    this.dockingDesktop.addHiddenDockable(PanelColors,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelConky,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelGraphics,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelLayout,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelNet,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelText,
                                          RelativeDockablePosition.LEFT)
    this.dockingDesktop.addHiddenDockable(PanelWindow,
                                          RelativeDockablePosition.LEFT)
    val oneFifthOfTheFrame = 0.2
    this.dockingDesktop.split(this.panelConkyEditor, PanelLua,
                              DockingConstants.SPLIT_TOP, oneFifthOfTheFrame)
    
    // reload workspace
    try {
      val f = new File( models.Path.WORKSPACE_FILE )
      val fis = new FileInputStream(f)
      this.dockingDesktop.readXML(fis)
      fis.close
    }  catch {
      case ex: FileNotFoundException =>
      // use default layout
      case ex: IOException =>
          ex.printStackTrace
      case ex: javax.xml.parsers.ParserConfigurationException =>
          ex.printStackTrace
      case ex: org.xml.sax.SAXException =>
          ex.printStackTrace
    }
  }

  /*private def initStatusBar {
      final Timer messageTimer
      final Timer busyIconTimer
      final Icon idleIcon
      final Icon[] busyIcons = new Icon[15]
      ResourceMap resourceMap = getResourceMap
      int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout")
      messageTimer = new Timer(messageTimeout, new ActionListener {
          public void actionPerformed(ActionEvent e) {
              statusMessageLabel.setText("")
          }
      })
      messageTimer.setRepeats(false)
      val busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate")
      for (int i = 0 i < busyIcons.length i++) {
          busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]")
      }
      busyIconTimer = new Timer(busyAnimationRate, new ActionListener {
          public void actionPerformed(ActionEvent e) {
              busyIconIndex = (busyIconIndex + 1) % busyIcons.length
              statusAnimationLabel.setIcon(busyIcons[busyIconIndex])
          }
      })
      idleIcon = resourceMap.getIcon("StatusBar.idleIcon")
      statusAnimationLabel.setIcon(idleIcon)
      progressBar.setVisible(false)

      // connecting action tasks to status bar via TaskMonitor
      TaskMonitor taskMonitor = new TaskMonitor(getApplication.getContext)
      taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener {
          public void propertyChange(java.beans.PropertyChangeEvent evt) {
              String propertyName = evt.getPropertyName
              if ("started".equals(propertyName)) {
                  if (!busyIconTimer.isRunning) {
                      statusAnimationLabel.setIcon(busyIcons[0])
                      busyIconIndex = 0
                      busyIconTimer.start
                  }
                  progressBar.setVisible(true)
                  progressBar.setIndeterminate(true)
              } else if ("done".equals(propertyName)) {
                  busyIconTimer.stop
                  statusAnimationLabel.setIcon(idleIcon)
                  progressBar.setVisible(false)
                  progressBar.setValue(0)
              } else if ("message".equals(propertyName)) {
                  String text = (String)(evt.getNewValue)
                  statusMessageLabel.setText((text == null) ? "" : text)
                  messageTimer.restart
              } else if ("progress".equals(propertyName)) {
                  int value = (Integer)(evt.getNewValue)
                  progressBar.setVisible(true)
                  progressBar.setIndeterminate(false)
                  progressBar.setValue(value)
              }
          }
      })
  }*/

  private def helpOnlineActionPerformed(evt: java.awt.event.ActionEvent) {
      //TODO hardcoded url
      val URL = "http://conky.sourceforge.net/variables.html"
      try  {
          val editorPane: JEditorPane = new JEditorPane(URL)
          editorPane.setEditable(false)
          val frame = new JFrame(URL)
          frame.add(new JScrollPane(editorPane))
          frame.setSize(800, 600)
          val d: Dimension = Toolkit.getDefaultToolkit.getScreenSize
          frame.setLocation((d.width/2) - 400, (d.height/2) - 300)
          frame.setVisible(true)
      } catch {
          case ex: Exception =>
              ErrorPane.showErrorMessage("ConkyConkyGUI: ", ex)
      }
  }

  /**
   * PUBLIC METHODS
   */

  def saveFile(isSaveAs: Boolean) {

      //val textPanel: views.preferences.Text = this.preferencesWindow.getTextPanel
      //val comments = textPanel.getCommentDateTime + textPanel.getComments

      val comments = this.userPreference.getComments
      val path = this.panelConkyEditor.saveFile(isSaveAs, comments)
      if(!path.equals( models.Path.DEFAULT_CONKY_FILE )) {
          this.menuRecentFiles.addNewRecentFile(path)
          //Show pop up
          val popup = new InformationPopup(this.getFrame, new InformationPanel)
          popup.show("Saved file")
      }
  }

  def openFile {
      val cr = new ConkyFileReader
      val filePath = cr.openFileInSameDirectory(this.panelConkyEditor, this.panelConkyEditor.getFilePath)
      //String filePath = this.jPanelTextEditor.getFilePath
      if(!filePath.isEmpty) {
          this.menuRecentFiles.addNewRecentFile(filePath)
      }
      super.getFrame.requestFocus
  }

  /**
   * Saves the user preferences and the state of the application.
   */
  def saveConkyGUIPreferences {
      // Save user preferences (preferences window values)
      val recentFiles = this.menuRecentFiles.getRecentFiles
      this.userPreference.setFiles( recentFiles )
      this.userPreference.writeFile
      // Save the state of the workspace (docks and panels)
      try {
          val f: File = new File( models.Path.WORKSPACE_FILE )
          val pw: PrintWriter = new PrintWriter(f)
          val baos: ByteArrayOutputStream = new ByteArrayOutputStream
          this.dockingDesktop.writeXML(baos)
          pw.write(baos.toString)
          baos.close
          pw.close
      } catch {
          case ex: FileNotFoundException =>
              ex.printStackTrace
          case ex: IOException =>
              ex.printStackTrace
      }
  }

  def reset {
      panelConkyEditor.reset
      PanelColors.reset
      PanelConky.reset
      PanelGraphics.reset
      PanelLayout.reset
      PanelNet.reset
      PanelText.reset
      PanelWindow.reset
      PanelLua.reset
  }

  /**
   * Enables or disables all the GUI components.
   *
   * @param value: the flag that tells wether to enable or disable the GUI
   */
  def setEnabled(value: Boolean) {
      this.menuFile.setEnabled(value)
      this.menuEdit.setEnabled(value)
      this.menuRun.setEnabled(value)
      this.menuHelp.setEnabled(value)
      // panels
      this.panelConkyEditor.setEnabled(value)
      PanelColors.setEnabled(value)
      PanelConky.setEnabled(value)
      PanelGraphics.setEnabled(value)
      PanelLayout.setEnabled(value)
      PanelNet.setEnabled(value)
      PanelText.setEnabled(value)
      PanelWindow.setEnabled(value)
      PanelLua.setEnabled(value)
  }
}