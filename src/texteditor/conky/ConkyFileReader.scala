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

package texteditor.conky

import models.ConkyColor
import views.panels._
import java.io.BufferedReader
import java.io.FileReader
import java.net.URL
import java.util.NoSuchElementException
import java.util.StringTokenizer
import javax.swing.ImageIcon
import javax.swing.JFileChooser
import javax.swing.JOptionPane
import custom.swing.ErrorPane
import java.io.File
import java.io.IOException

object Keywords {
    val Lua = "# Lua"
    val TEXT = "TEXT"
}

class ConkyFileReader {

  private val commentDelimiter = "#"
  private val stDelimiter = " "
  private val okNoErrors = "ok"
  private val fileChooser: JFileChooser = new JFileChooser()

  fileChooser.setDialogTitle("Select a Conky file");
  fileChooser.setDialogType(javax.swing.JFileChooser.OPEN_DIALOG);
  fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
  fileChooser.setName("fileChooser");

  def openFileInSameDirectory(pce: PanelConkyEditor, 
                              currentPath: String): String = {
    val filePath = this.selectFile(currentPath)
      if (filePath==null || filePath.isEmpty()) {
      return ""
    } else {
      // TODO ConkyGUI.reset
      this.openFile(pce, filePath)
      return filePath
    }
  }

  def openFile(pce: PanelConkyEditor, filePath: String) {
      var errorMessage: String = null
      try {
          val br = new BufferedReader(new FileReader(filePath))
          errorMessage = this.readConkyConfigurationVariables( br )
          var TEXT = ""
          var line = br.readLine
          while (line != null) {
              TEXT += line + "\n"
              line = br.readLine
          }
          pce.setText(TEXT)
          br.close
      } catch {
          case ex: IOException =>
              ErrorPane.showErrorMessage("ConkyFileReader.IOException: ", ex)
      }
      pce.updateCurrentFilePath(filePath, true);
      // Display errors, if any
      if (errorMessage != null && !errorMessage.isEmpty) {
          val url = this.getClass.getResource("../../conkygui/resources/images/error.png")
          val ii = new ImageIcon(url)
          JOptionPane.showMessageDialog(
              null, errorMessage, "Read errors", JOptionPane.ERROR_MESSAGE, ii)
      }
  }

  /**
    * Pop ups a dialog where the user can select a file.
    *
    * @return the path to the selected file.
    */
  private def selectFile(currentPath: String):String = {
      if(currentPath==null || currentPath.isEmpty) {
          this.fileChooser.setCurrentDirectory(new File( models.Path.HOME_FOLDER ))
      } else {
          this.fileChooser.setCurrentDirectory(new File(currentPath))
      }
      val returnValue = this.fileChooser.showOpenDialog(null)
      if (returnValue == JFileChooser.APPROVE_OPTION) {
          return this.fileChooser.getSelectedFile.getPath
      } else {
          return null
      }
  }

  private def readConkyConfigurationVariables(br: BufferedReader): String = {

      def isComment(line: String):Boolean = {
          if (line.startsWith(this.commentDelimiter))
              return true else return false
      }

      var line = br.readLine
      var errorMessage = ""
      while (line != null) {
          line = br.readLine.trim
          line match {
              case "" =>
                  // if empty, do nothing
              case Keywords.Lua =>
                  line = br.readLine.trim
                  while(line!=Keywords.TEXT) {
                      PanelLua.appendLine(line)
                      line = br.readLine.trim
                  }
                  return errorMessage
              case Keywords.TEXT =>
                  return errorMessage
              case l =>
                  if(!isComment(line)) {
                      val result = this.processLine( line )
                      result match {
                          case this.okNoErrors => // do nothing
                          case _ => errorMessage += result + "\n"
                      }
                  }
          }
      }
      return errorMessage;
  }

  /**
    * Lines are processed under the assumption that each line contains only one keyword, so the following words
    * are the values assigned to them.
    *
    * @param pc the JPanelConfiguration object where the values read from the <i>line</i> will be saved
    * @param line the string to be processed
    */
  private def processLine(line: String):String = {
      val st = new StringTokenizer(line, this.stDelimiter)
      val keyword = st.nextToken
      try {
          val isLua = this.processConkyKeyword(st, keyword)
          if(isLua)
              PanelLua.appendLine(line)
          return this.okNoErrors
      } catch {
          case ex: MatchError =>
              println("ConkyFileReader: match error "+ex.getMessage)
              return ""
          case ex: Exception =>
              return "Illegal value for: " + keyword + ", " + ex.getMessage
      }
  }

  private def processConkyKeyword(
  st: StringTokenizer, keyword: String): Boolean = {
      val pcc = PanelConky
      val pt = PanelText
      val pw = PanelWindow
      val pg = PanelGraphics
      val pl = PanelLayout
      val pc = PanelColors
      val pn = PanelNet
      keyword match {
          // Config
          case "background" => pcc.setBackground(st.nextToken)
          case "no_buffers" => pcc.setNoBuffers(st.nextToken)
          case "out_to_console" => pcc.setOutToConsole(st.nextToken)
          case "top_cpu_separate" => pcc.setTopCPUSeparate(st.nextToken)
          case "max_port_monitor_connections" =>
              pcc.setMaxPortMonitorConnections(Integer.parseInt(st.nextToken))
          case "cpu_avg_samples" => pcc.setCPUAvgSamples(Integer.parseInt(st.nextToken))
          case "net_avg_samples" => pcc.setNetAvgSamples(Integer.parseInt(st.nextToken))
          case "total_run_times" => pcc.setTotalRunTimes(Integer.parseInt(st.nextToken))
          case "update_interval" =>
              val interval:Int = java.lang.Double.parseDouble(st.nextToken).intValue
              pcc.setUpdateInterval(interval)
          case "music_player_interval" => pcc.setMusicPlayerInterval(Integer.parseInt(st.nextToken))
          // Text config
          case "uppercase" => pt.setUppercase(st.nextToken)
          case "override_utf8_locale" => pt.setOverrideUTF8(st.nextToken)
          case "short_units" => pt.setShortUnits(st.nextToken)
          case "pad_percents" => pt.setPadPercents(Integer.parseInt(st.nextToken))
          case "text_buffer_size" => pt.setTextBufferSize(Integer.parseInt(st.nextToken))
          case "max_user_text" => pt.setMaxUserText(Integer.parseInt(st.nextToken))
          case "font" => pt.setFontConky(this.readRestOfTheLine(st))
          case "use_xft" => pt.setUseXFT(st.nextToken)
          case "xftalpha" =>
              val alpha = java.lang.Double.parseDouble(st.nextToken)
              pt.setXFTAlpha(alpha)
          case "xftfont" => pt.setXFTFont(this.readRestOfTheLine(st))
          // Window config
          case "own_window" => pw.setOwnWindow(st.nextToken)
          case "own_window_class" => pw.setOwnWindowClass(st.nextToken)
          case "own_window_colour" => pw.setOwnWindowColour(ConkyColor.toColor(st.nextToken))
          case "own_window_transparent" => pw.setOwnWindowTransparent(st.nextToken)
          case "own_window_title" => pw.setOwnWindowTitle(st.nextToken)
          case "own_window_hints" => this.processOwnWindowHints(st.nextToken)
          case "own_window_type" => pw.setOwnWindowType(st.nextToken)
          // Window hints

          // Graphics config
          case "double_buffer" => pg.setDoubleBuffer(st.nextToken)
          case "draw_borders" => pg.setDrawBorders(st.nextToken)
          case "draw_graph_borders" => pg.setDrawGraphBorders(st.nextToken)
          case "draw_shades" => pg.setDrawShades(st.nextToken)
          case "draw_outline" => pg.setDrawOutline(st.nextToken)
          case "stippled_borders" => pg.setStippleBorders(Integer.parseInt(st.nextToken))
          case "max_specials" => pg.setMaxSpecials(Integer.parseInt(st.nextToken))
          // Layout
          case "alignment" => pl.setAlignment(st.nextToken)
          case "gap_x" => pl.setGapX(Integer.parseInt(st.nextToken))
          case "gap_y" => pl.setGapY(Integer.parseInt(st.nextToken))
          case "maximum_width" => pl.setMaximumWidth(Integer.parseInt(st.nextToken))
          case "minimum_size" =>
              pl.setMinimumSizeConky(Integer.parseInt(st.nextToken), Integer.parseInt(st.nextToken))
          case "use_spacer" => pl.setUseSpacer(st.nextToken)
          case "border_margin" => pl.setBorderMargin(Integer.parseInt(st.nextToken))
          case "border_width" => pl.setBorderWidth(Integer.parseInt(st.nextToken))
          // Colors
          case "default_color" => pc.setDefaultColor(ConkyColor.toColor(st.nextToken))
          case "default_outline_color" => pc.setDefShadeColor(ConkyColor.toColor(st.nextToken))
          case "default_shade_color" => pc.setDefOutlineColor(ConkyColor.toColor(st.nextToken))
          case "color0" => pc.setColor0(ConkyColor.toColor(st.nextToken))
          case "color1" => pc.setColor1(ConkyColor.toColor(st.nextToken))
          case "color2" => pc.setColor2(ConkyColor.toColor(st.nextToken))
          case "color3" => pc.setColor3(ConkyColor.toColor(st.nextToken))
          case "color4" => pc.setColor4(ConkyColor.toColor(st.nextToken))
          case "color5" => pc.setColor5(ConkyColor.toColor(st.nextToken))
          case "color6" => pc.setColor6(ConkyColor.toColor(st.nextToken))
          case "color7" => pc.setColor7(ConkyColor.toColor(st.nextToken))
          case "color8" => pc.setColor8(ConkyColor.toColor(st.nextToken))
          case "color9" => pc.setColor9(ConkyColor.toColor(st.nextToken))
          // Network
          case "mail_spool" => pn.setMailSpool(st.nextToken)
          // MPD
          case "mpd_host" => pn.setMPDHost(st.nextToken)
          case "mpd_port" => pn.setMPDPort(Integer.parseInt(st.nextToken))
          case "mpd_password" => pn.setMPDPassword(st.nextToken)
          // POP3
          case "pop3" =>
              val host = st.nextToken
              val user = st.nextToken
              val pass = st.nextToken
              var interval = 0
              if (st.nextToken.equals("-i"))
                  interval = Integer.parseInt(st.nextToken)
              var port = 0
              if (st.nextToken.equals("-p"))
                  port = Integer.parseInt(st.nextToken)
              var command = ""
              if (st.nextToken.equals("-e"))
                  command = st.nextToken
              pn.setPOP3(host, user, pass, interval, port, command)
          // IMAP
          case "imap" =>
              val host = st.nextToken
              val user = st.nextToken
              val pass = st.nextToken
              var interval = 0
              if (st.nextToken.equals("-i"))
                  interval = Integer.parseInt(st.nextToken)
              var folder = ""
              if (st.nextToken.equals("-f"))
                  folder = st.nextToken
              var port = 0
              if (st.nextToken.equals("-p"))
                  port = Integer.parseInt(st.nextToken)
              var command = ""
              if (st.nextToken.equals("-e"))
                  command = st.nextToken
              pn.setIMAP(host, user, pass, interval, folder, port, command)
          // Lua
          case _ => return true
      }
      return false
  }

  private def readRestOfTheLine(st: StringTokenizer):String = {
      var font = st.nextToken + " "
      while (st.hasMoreTokens) {
          font += st.nextToken + " "
      }
      return font.trim
  }

  private def processOwnWindowHints(hints: String) {
      val st = new StringTokenizer(hints, ",")
      while (st.hasMoreTokens) {
          st.nextToken match {
              // undecorated,below,above,sticky,skip_taskbar,skip_pager
              case "undecorated" => PanelWindow.setUndecorated(true)
              case "below" => PanelWindow.setBelow(true)
              case "above" => PanelWindow.setAbove(true)
              case "sticky" => PanelWindow.setSticky(true)
              case "skip_taskbar" => PanelWindow.setSkipTaskBar(true)
              case "skip_pager" => PanelWindow.setSkipPager(true)
              case _ => // do nothing
          }
      }
  }

}