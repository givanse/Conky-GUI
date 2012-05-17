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

case class Pos(line: Int, column: Int)

abstract class Node

// shell
case class Cmd(string: String, options: List[Node], args: List[Node]) extends Node
case class Opt(string: String, args: Node) extends Node
case class Arg(string: String, pos: Pos, offset: Int) extends Node
case class EndOp(pos: Pos, offset: Int) extends Node
case class N() extends Node

// conky
case class Txt(string: String, pos: Pos, offset: Int) extends Node
case class Key(string: String, pos: Pos, offset: Int) extends Node
case class Var(string: String, pos: Pos, offset: Int, key: Key) extends Node
// case class Param(string: String, pos: Pos, offset: Int) extends Node
case class Comment(string: String, pos: Pos, offset: Int) extends Node
