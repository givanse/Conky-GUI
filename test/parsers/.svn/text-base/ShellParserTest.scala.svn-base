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

class ShellParserTest extends ShellParser {

    @Test
    def testID {
        assertTrue( parseAll(id, "killall").successful )
        assertTrue( parseAll(id, "i810").successful )
        assertTrue( parseAll(id, "apt-get").successful )
        assertTrue( parseAll(id, "acpi_available").successful )
        assertTrue( parseAll(id, "ps2ascii").successful )
        assertTrue( parseAll(id, "fix-broken").successful )
        assertFalse( parseAll(id, "-fix-broken").successful )
        assertFalse( parseAll(id, "").successful )
        assertFalse( parseAll(id, " ").successful )
        assertFalse( parseAll(id, "\n").successful )
        assertFalse( parseAll(id, " \n").successful )
        assertFalse( parseAll(id, "").successful )
    }

    @Test
    def testEndOp {
        assertTrue( parseAll(endOp, ";").successful )
        assertTrue( parseAll(endOp, "&").successful )
        assertTrue( parseAll(endOp, "\n").successful )
        assertTrue( parseAll(endOp, " ;").successful )
        assertTrue( parseAll(endOp, "& ").successful )
        assertTrue( parseAll(endOp, "& \n").successful )
        assertTrue( parseAll(endOp, "& \n ").successful )
        assertTrue( parseAll(endOp, "&\n  ").successful )
        assertTrue( parseAll(endOp, "; \n \n ").successful )
        assertTrue( parseAll(endOp, "\n ").successful )
        assertTrue( parseAll(endOp, " \n").successful )
        assertTrue( parseAll(endOp, " \n ").successful )
        assertTrue( parseAll(endOp, "\n \n ").successful )
        assertFalse( parseAll(endOp, "\n &").successful )
        assertFalse( parseAll(endOp, "").successful )
        assertFalse( parseAll(endOp, " ").successful )
    }

    @Test
    def testEndOpAST {
        assertEquals( EndOp(Pos(1,1), 0), parseAll(endOp, ";").get )
        assertEquals( N(), parseAll(endOp, "\n").get )
        assertEquals( EndOp(Pos(1,2), 1), parseAll(endOp, " &").get )
        assertEquals( EndOp(Pos(1,2), 1), parseAll(endOp, " &  ").get )
        assertEquals( EndOp(Pos(1,2), 1), parseAll(endOp, " &\n \n ").get )
    }

    @Test
    def testArgs {
        assertTrue( parseAll(arg, " $FILE").successful )
        assertTrue( parseAll(arg, " arg1").successful )
        assertTrue( parseAll(arg, " 5340").successful )
        assertTrue( parseAll(arg, " i810").successful )
        assertTrue( parseAll(arg, " arg1").successful )
        assertTrue( parseAll(arg, " a").successful )
        assertTrue( parseAll(arg, " A").successful )
        assertTrue( parseAll(arg, " 0").successful )
        assertTrue( parseAll(arg, " ~/.conkyrc").successful )
        assertTrue( parseAll(arg, " file.scala").successful )
        assertTrue( parseAll(arg, " /home/user/conkyrc").successful )
        assertTrue( parseAll(arg, " /home/user/.conkyrc").successful )
        assertTrue( parseAll(arg, " /etc/fstab").successful )
        assertTrue( parseAll(arg, """ /home/user/my\ files/conky""").successful )
        assertTrue( parseAll(arg, """ ~/my\ files/conky\ """).successful )
        assertTrue( parseAll(arg, """ /my\ files/conky_rc""").successful )
        assertTrue( parseAll(arg, " ~myfiles/conky").successful )
        assertTrue( parseAll(arg, """ \ """).successful )
        assertFalse( parseAll(arg, """ \  """).successful )
        assertFalse( parseAll(arg, " $file").successful )
        assertFalse( parseAll(arg, "-arg").successful )
        assertFalse( parseAll(arg, " 5340 ").successful )
        assertFalse( parseAll(arg, "i810").successful )
        assertFalse( parseAll(arg, " ").successful )
        assertFalse( parseAll(arg, "\n").successful )
        assertFalse( parseAll(arg, " \n").successful )
        assertFalse( parseAll(arg, "").successful )
        assertFalse( parseAll(arg, """ ~/my\ files/conky \  """).successful )
        assertFalse( parseAll(arg, """ ~/my\ files/conky \""").successful )
    }

    def testArgsAST {
        assertEquals( Arg("a", Pos(1,2), 1), parseAll(arg, " a").get )
        assertEquals( Arg("~/.conkyrc", Pos(1,2), 1), parseAll(arg, " ~/.conkyrc").get )
        assertEquals( Arg("/home/user/my\\ files/conky", Pos(1,2), 1), parseAll(arg, " /home/user/my\\ files/conky").get )
        assertEquals( Arg("file.scala", Pos(1,2), 1), parseAll(arg, " file.scala").get )
        assertEquals( Arg("i810", Pos(1,2), 1), parseAll(arg, " i810").get )
        assertEquals( Arg("5679", Pos(1,2), 1), parseAll(arg, " 5679").get )
        assertEquals( Arg("""~/\ \ conky\ \ """, Pos(1,2), 1), parseAll(arg, """ ~/\ \ conky\ \ """).get )
        assertEquals( Arg("""~/conky\ """, Pos(1,2), 1), parseAll(arg, """ ~/conky\ """).get )
        assertEquals( Arg("~/files/", Pos(1,2), 1), parseAll(arg, " ~/files/").get )
        assertEquals( Arg("""~/files/\ conky\ """, Pos(1,2), 1), parseAll(arg, """ ~/files/\ conky\ """).get )
        assertEquals( Arg("""/home/user/my\ files/conky""" , Pos(1,2), 1), parseAll(arg, """ /home/user/my\ files/conky""" ).get )
        assertEquals( Arg("~myfiles/conky", Pos(1,2), 1), parseAll(arg, " ~myfiles/conky").get )
    }

    @Test
    def testOption {
        assertTrue( parseAll(option, " -c $FILE").successful )
        assertTrue( parseAll(option, " -fix-broken arg1").successful )
        assertTrue( parseAll(option, " -fitsbroken arg1").successful )
        assertTrue( parseAll(option, " --fixbroken arg1").successful )
        assertTrue( parseAll(option, " --fix-broken arg1").successful )
        assertTrue( parseAll(option, " --purge 5340").successful )
        assertTrue( parseAll(option, " -v i810").successful )
        assertFalse( parseAll(option, " -c").successful )
        assertFalse( parseAll(option, " fix-broken arg1").successful )
        assertFalse( parseAll(option, "--purge 5340").successful )
        assertFalse( parseAll(option, "--fix-broken $FILE").successful )
        assertFalse( parseAll(option, " -c $FILE ").successful )
    }

    @Test
    def testOptionAST {
        assertEquals(
            Arg("$FILE", Pos(1,5), 4),
            parseAll(option, " -c $FILE").get )
        assertEquals(
            Arg("arg1", Pos(1,15), 14),
            parseAll(option, " --fix-broken arg1").get )
    }

    @Test
    def testCommand {
        assertTrue( parseAll(command, "conky -c $FILE").successful )
        assertTrue( parseAll(command, "conky -c /home/user/documents/conky").successful )
        assertTrue( parseAll(command, "killall conky").successful )
        assertTrue( parseAll(command, "java -cp ~/.conkyrc -jar main").successful )
        assertTrue( parseAll(command, "gedit file1 file2 file3").successful )
        assertTrue( parseAll(command, "scalac -unchecked file1 file2").successful )
        assertTrue( parseAll(command, "killall conky").successful )
        assertTrue( parseAll(command, "exit").successful )
    }

    @Test
    def testCommandAST {
        assertEquals(
            List(Arg("$FILE", Pos(1,10), 9)),
            parseAll(command, "conky -c $FILE").get )
        assertEquals(
            Nil,
            parseAll(command, "java -cp ~/.conkyrc -jar main").get )
    }

    @Test
    def testCommands {
        //assertTrue( parseAll(commands, ";").successful )
        assertTrue( parseAll(commands, "exit").successful )
        assertTrue( parseAll(commands, "exit;").successful )
        assertTrue( parseAll(commands, "exit; \n; ; ;").successful )
        assertTrue( parseAll(commands, "conky &").successful )
        assertTrue( parseAll(commands, "killall conky;").successful )
        assertTrue( parseAll(commands, "conky -c $FILE &").successful )
        assertTrue( parseAll(commands, "killall conky; conky -c $FILE &").successful )
        assertTrue( parseAll(commands, "killall conky;\nconky -c $FILE &").successful )
        assertTrue( parseAll(commands, "scalac file.scala; java -cp ~/.conkyrc -jar main").successful )
    }

    @Test
    def testCommandsAST {
        assertEquals(
            List( Arg("$FILE", Pos(1,10), 9), EndOp(Pos(1,15), 14) ),
            parseAll(commands, "conky -c $FILE;").get )
        assertEquals(
            List( Arg("$FILE", Pos(1,10), 9), N() ),
            parseAll(commands, "conky -c $FILE\n").get )
        assertEquals(
            List( EndOp(Pos(1,14), 13), Arg("$FILE", Pos(2,10), 24), EndOp(Pos(2,16), 30) ),
            parseAll(commands, "killall conky;\nconky -c $FILE &").get )
        assertEquals(
            List( EndOp(Pos(2,30), 58), EndOp(Pos(2,38), 66) ),
            parseAll(commands, "scalac -unchecked file.scala\njava -cp ~/.conkyrc -jar main; conky &").get )
    }
}