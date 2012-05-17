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

class ConkyParser extends RegexParsers {

    protected override val whiteSpace = "".r

    def conkyText: Parser[List[Node]] =
        //(comment | variable | text )
        rep(comment | variable | text) ^^ {
            case lst => lst
        }

    def text: Parser[Node] =
        ( "\\s*".r ~> pos("([^$#](\\\\[$#])?)+".r) ) ^^ {
            case (s, p, o) => Txt(s, p, o)
        } | "\\s+".r ^^ { case _ => N() }
        
    def comment: Parser[Comment] = pos("^#.*".r) ^^ { case (s, p, o) => Comment(s, p, o) }

    // must be reluctant
    def variable: Parser[Node] =
        pos("[$][{]\\s*".r) ~ keyword ~ "(.*?)[}]".r ^^ {
            case (s, p, o) ~ k ~ args =>
                val string = s+k.string+args
                Var(string, p, o, k)
        } | "$" ~ keyword ^^ {
            case s ~ k => 
                Key(s+k.string, Pos(k.pos.line, k.pos.column-1), k.offset-1)
        }

    def keyword: Parser[Key] =
        pos("[\\w_]*".r) ^^ {
            case (s, p, o) => Key(s, p, o)
        }

    def parse(text: String): List[Node] = {
        this.parseAll(conkyText, text) match {
            case ns: NoSuccess => Nil
            case Success(list, _) =>
                list.filter{ n => n != N()}
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
