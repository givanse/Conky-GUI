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

package parsers

import org.junit.Test
import org.junit.Assert._

class TestConkyParser extends ConkyParser {

    @Test
    def testText {
        assertTrue( parseAll(text, "a").successful )
        assertTrue( parseAll(text, ".").successful )
        assertTrue( parseAll(text, "vieulent").successful )
        assertTrue( parseAll(text, "random text").successful )
        assertTrue( parseAll(text, "32wfs sdfw 211.2 1-1_`&*23vy ").successful )
        assertTrue( parseAll(text, "money\\\\$").successful )
        assertTrue( parseAll(text, "money \\\\$").successful )
        assertTrue( parseAll(text, "comment with \\#").successful )
        assertTrue( parseAll(text, "  txt ").successful )
        assertTrue( parseAll(text, "txt   ").successful )
        assertTrue( parseAll(text, " ").successful ) // empty node N()
        assertTrue( parseAll(text, " \n").successful )
        assertTrue( parseAll(text, " \t").successful )
        assertFalse( parseAll(text, "").successful )
        assertFalse( parseAll(text, "money$").successful )
        assertFalse( parseAll(text, "money $").successful )
        assertFalse( parseAll(text, "comment with #").successful )
        assertFalse( parseAll(text, "## this is a comment").successful )
        assertFalse( parseAll(text, "$var").successful )
        assertFalse( parseAll(text, "txt $var").successful )
        assertFalse( parseAll(text, "txt${var}txt").successful )
        assertFalse( parseAll(text, "${cpu cpu0} CPU").successful )
        assertFalse( parseAll(text, "CPU:${cpu cpu0}").successful )
    }

    @Test
    def testComment {
        assertTrue( parseAll(comment, "# this is a comment").successful )
        assertTrue( parseAll(comment, "## this is a comment").successful )
        assertTrue( parseAll(comment, "# this is a comment ##").successful )
        assertFalse( parseAll(comment, "this is a comment").successful )
        assertFalse( parseAll(comment, "this #is a comment").successful )
        assertFalse( parseAll(comment, "this\n# is a comment").successful )
    }

    @Test
    def testCommentAST {
        assertEquals( Comment("# this is a comment", Pos(1,1), 0), parseAll(comment, "# this is a comment").get )
        assertEquals( Comment("## this is a comment", Pos(1,1), 0), parseAll(comment, "## this is a comment").get )
        assertEquals( Comment("# this is a comment ##", Pos(1,1), 0), parseAll(comment, "# this is a comment ##").get )
    }

    @Test
    def testVariable {
        assertTrue( parseAll(variable, "$").successful )
        assertTrue( parseAll(variable, "$cpu").successful )
        assertTrue( parseAll(variable, "$xmms2_elapsed").successful )
        assertTrue( parseAll(variable, "$i8k_left_fan_status").successful )
        assertTrue( parseAll(variable, "${top}").successful )
        assertTrue( parseAll(variable, "${  color0}").successful )
        assertTrue( parseAll(variable, "${color0  }").successful )
        assertTrue( parseAll(variable, "${  color0  }").successful )
        assertTrue( parseAll(variable, "${cpu cpu0}").successful )
        assertTrue( parseAll(variable, "${voffset 350}").successful )
        assertTrue( parseAll(variable, "${voffset -350}").successful )
        assertTrue( parseAll(variable, "${voffset   -350}").successful )
        assertTrue( parseAll(variable, "${voffset -350  }").successful )
        assertTrue( parseAll(variable, "${  voffset -350}").successful )
        assertTrue( parseAll(variable, "${  voffset   -350  }").successful )
        assertTrue( parseAll(variable, "${wireless_bitrate eth1}").successful )
        assertTrue( parseAll(variable, "${color0}").successful )
        assertTrue( parseAll(variable, "${cpugraph cpu0 70,250 FF0077 FF0000 -l -t}").successful )
        assertTrue( parseAll(variable, "${execi 1 sensors | grep 'Core 0' | cut -c15-20}").successful )
        assertTrue( parseAll(variable, "${top pid 3}").successful )
        assertTrue( parseAll(variable, "${execi 60 date +%R | cut -c1}").successful )
        assertTrue( parseAll(variable, "${font BlockyCounterClockwise:size=80}").successful )
        assertTrue( parseAll(variable, "${execi 60 date | cut -c26}").successful )
        assertTrue( parseAll(variable, "${time %d %b %Y%t}").successful )
        assertTrue( parseAll(variable, "${execi 60 date | cut -c26}").successful )
        assertFalse( parseAll(variable, "{color0}").successful )
        assertFalse( parseAll(variable, "color0").successful )
        assertFalse( parseAll(variable, "{color0").successful )
        assertFalse( parseAll(variable, "color0}").successful )
        assertFalse( parseAll(variable, "${color0").successful )
        assertFalse( parseAll(variable, "$color0}").successful )
        assertFalse( parseAll(variable, "$ {color0}").successful )
        assertFalse( parseAll(variable, "\\$").successful )
    }

    @Test
    def testVariableAST {
        assertEquals( 
            Var("${top}", Pos(1,1), 0, Key("top", Pos(1,3), 2)),
            parseAll(variable, "${top}").get )
        assertEquals(
            Key("$cpu", Pos(1,1), 0),
            parseAll(variable, "$cpu").get )
        assertEquals(
            Var("${time %d %b %Y%t}", Pos(1,1), 0, Key("time", Pos(1,3), 2)),
            parseAll(variable, "${time %d %b %Y%t}").get )
        assertEquals(
            Var("${execi 60 date | cut -c26}", Pos(1,1), 0, Key("execi", Pos(1,3), 2)),
            parseAll(variable, "${execi 60 date | cut -c26}").get )
        assertEquals(
            Var("${font DejaVu Sans Mono:bold:size=9}", Pos(1,1), 0, Key("font", Pos(1,3), 2)),
            parseAll(variable, "${font DejaVu Sans Mono:bold:size=9}").get )
    }

    @Test
    def testConkyText {
        assertTrue( parseAll(conkyText, "cpu").successful )
        assertTrue( parseAll(conkyText, "${cpu cpu0}").successful )
        assertTrue( parseAll(conkyText, "# a comment").successful )
        assertTrue( parseAll(conkyText, "${cpu cpu0} CPU").successful )
        assertTrue( parseAll(conkyText, "${cpu cpu0} CPU ").successful )
        assertTrue( parseAll(conkyText, "CPU:${cpu cpu0}").successful )
        assertTrue( parseAll(conkyText, " CPU: ${cpu cpu0}").successful )
        assertTrue( parseAll(conkyText, "${color lightgrey}Temperatures:").successful )
        assertTrue( parseAll(conkyText, "$nodename - ${sysname} ${kernel} on ${machine}").successful )
        assertTrue( parseAll(conkyText, "${color lightgrey}CPU Usage:${color #5000a0} ${cpu}% ${cpubar}").successful )
        assertTrue( parseAll(conkyText, "${color #88aadd}${alignc}${mpd_status}").successful )
        assertTrue(
            parseAll(conkyText, "#This is a comment\n${color #88aadd}${alignc}${mpd_status}").successful )
        assertTrue( parseAll(conkyText, """\n\
                ${voffset 60}${color3}\n\
            ${offset 100}${time %d %b %Y%t}\n\
            ${offset 100}${time %r%t}\n\
            ${color3}${offset 100}CPU: ${color}$cpu%\n\
        """).successful )
        assertTrue( parseAll(conkyText, """${voffset 0}${font Bitstream Vera Sans Mono:style=Roman:size=10}\n\
            ${color}CPU:\n\
            ${color0}${cpu}%\n\
            ${color}MEM:\n\
            # random comment\n\
            ${color0}$memperc% ${color1}${mem}\n\
            ${color}SWAP:\n\
            ${color0}${swapperc}% ${color1}${swap}\n\
            ${color}ESSID:\n\
            random text #\n\
            ${color0}${wireless_essid eth1}\n\
            ${color}LINK:     ${color0}${wireless_link_qual_perc eth1}%\n\
            ${color}UP:       ${color0}${upspeed eth1} KB/S\n\
            ${color}DOWN:     ${color0}${downspeed eth1} KB/S\n\
            ${color}BIT RATE: ${color0}${wireless_bitrate eth1}\n\
            ${color}BATTERY:  ${color0}${battery} ${battery_time}\n\
        """).successful )
    }

    @Test
    def testConkyTextAST {
        assertEquals(
                     List(
                         Txt("CPU: ", Pos(1, 1), 0),
                         Var("${cpu cpu0}", Pos(1, 6), 5, Key("cpu", Pos(1, 8), 7))
                     ),
                     parseAll(conkyText, "CPU: ${cpu cpu0}").get )
        assertEquals(
                     List(
                         Var("${color lightgrey}", Pos(1, 1), 0, Key("color", Pos(1, 3), 2)),
                         Txt("Temperatures:", Pos(1, 19), 18)
                     ),
                     parseAll(conkyText, "${color lightgrey}Temperatures:").get )
        assertEquals(
                     List(
                          Key("$nodename", Pos(1, 1), 0),
                          Txt("- ", Pos(1, 11), 10),
                          Var("${sysname}", Pos(1, 13), 12, Key("sysname", Pos(1, 15), 14)),
                          N(),
                          Var("${kernel}", Pos(1, 24), 23, Key("kernel", Pos(1, 26), 25)),
                          Txt("on ", Pos(1, 34), 33),
                          Var("${machine}", Pos(1, 37), 36, Key("machine", Pos(1, 39), 38))
                     ),
                     parseAll(conkyText, "$nodename - ${sysname} ${kernel} on ${machine}").get )
        assertEquals(
                     List(
                        Var("${color lightgrey}", Pos(1, 1), 0, Key("color", Pos(1, 3), 2)),
                        Txt("CPU Usage:", Pos(1, 19), 18),
                        Var("${color #5000a0}", Pos(1, 29), 28, Key("color", Pos(1, 31), 30)),
                        N(),
                        Var("${cpu}", Pos(1, 46), 45, Key("cpu", Pos(1, 48), 47)),
                        Txt("% ", Pos(1, 52), 51),
                        Var("${cpubar}", Pos(1, 54), 53, Key("cpubar", Pos(1, 56), 55))
                     ),
                     parseAll(conkyText, "${color lightgrey}CPU Usage:${color #5000a0} ${cpu}% ${cpubar}").get )
        assertEquals(
                     List(
                        Var("${color #88aadd}", Pos(1, 1), 0, Key("color", Pos(1, 3), 2)),
                        Var("${alignc}", Pos(1, 17), 16, Key("alignc", Pos(1, 19), 18)),
                        Var("${mpd_status}", Pos(1, 26), 25, Key("mpd_status", Pos(1, 28), 27))
                     ),
                     parseAll(conkyText, "${color #88aadd}${alignc}${mpd_status}").get )
        assertEquals(
                     List(
                        N(),
                        Var("${font DejaVu Sans Mono:bold:size=9}", Pos(3, 1), 2, Key("font", Pos(3, 3), 4)),
                        N(),
                        Var("${kernel}", Pos(4, 1), 39, Key("kernel", Pos(4, 3), 41)),
                        N(),
                        Var("${machine}", Pos(4, 11), 49, Key("machine", Pos(4, 13), 51))
                     ),
                     parseAll(conkyText, "\n\n${font DejaVu Sans Mono:bold:size=9}\n${kernel} ${machine}").get )
    }
}