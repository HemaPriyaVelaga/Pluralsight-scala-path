package Pluralsight

import scala.xml.{Elem, NodeSeq}

object XmlApp extends App {

  val note: Elem =
    <note>
      <to>Tove</to>
      <from>Jani</from>
      <heading>Reminder</heading>
      <body>Don't forget me this weekend!</body>
    </note>

  // Double back slash below to support nested elements in xml

  val seq: NodeSeq = note \ "to"
  println(seq)
}
