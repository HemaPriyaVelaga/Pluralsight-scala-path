package Pluralsight

import java.util

object ScalaWithJava extends App {

  val numbersInJava: util.List[Int] = util.Arrays.asList(1, 2, 3, 4)
  // This is possible because of scala collection converters

//  val numbersScala: scala.collection.mutable.Buffer[Int] = numbersInJava.asScala
//
//  numbersScala.foreach(number => println(number))

}
