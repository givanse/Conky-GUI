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

import scala.util.parsing.combinator.RegexParsers

class ShellParser extends RegexParsers {

    protected override val whiteSpace = """([\t\f\r]|(#.*))+""".r

    def commands: Parser[List[Node]] = //falta incluir $FILE
        command ~ (rep(endOp ~ command ^^ {
                    case e ~ lstF => if(e.isInstanceOf[EndOp]) e::lstF else lstF
                }) ^^ {
                case lst => lst.flatten
            }) ~ opt(rep(endOp)) ^^ {
                case c ~ eOpLst ~ Some(lstEndOp) => c++eOpLst++lstEndOp
                case c ~ eOpLst ~ _ => c++eOpLst // none | Some(e: N)
        }

    def command: Parser[List[Arg]] =
        id ~ rep(option) ~ rep(arg) ^^ {
            case cmdID ~ optLst ~ argsLst => //Cmd(cmdID, optLst, argsLst)
                val lst: List[Arg] = optLst++argsLst
                def isFileToken(arg: Arg): Boolean =
                    arg.string=="$FILE"
                lst.filter(isFileToken)
        }

    def option: Parser[Arg] =
        rep1(" ") ~> "--?".r ~ id ~ arg ^^ {
            case prfx ~ optID ~ arg => arg//Opt(prfx+optID, arg)
        }

    def arg: Parser[Arg] =
        rep1(" ") ~> pos("$FILE" | "~?((\\\\ )|[\\w./-])+".r) ^^ {
            case (str, pos, offset) => Arg(str, pos, offset)
        }

    def endOp: Parser[Node] =
        " *".r ~> ( pos("\n") | pos(";") | pos("&")) <~ rep("[ \n]".r) ^^ {
            case ("\n", _, _) => N()
            case (str, pos, offset) => EndOp(pos, offset)
        }
        
    def id: Parser[String] =
        """[a-zA-Z][\w-]*""".r ^^ { case lst => lst.mkString }

    def parse(text: String): List[Node] = {
        this.parseAll(commands, text) match {
            case ns: NoSuccess => Nil
            case Success(list, _next) => list
        }
    }

    private def pos(p: Parser[String]): Parser[(String, Pos, Int)] =
      new Parser[(String, Pos, Int)] {
        def apply(in: Input): ParseResult[(String, Pos, Int)] = {
          val source = in.source
          val offset = in.offset
          val start = handleWhiteSpace(source, offset)
          p(in) match {
            case Success(str, otherIn) =>
              val p = in.drop(start - offset).pos
              Success((str, Pos(p.line, p.column), offset), otherIn)
            case nosucc: NoSuccess =>
              nosucc
          }
        }
      }
}