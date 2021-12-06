import jdk.internal.util.xml.impl.Pair

// Extractors - Pattern Matching
val arr = Array(0)
val arr2 = Array(1,2,3,4)
val arr3 = Array(0,1,2,3)
val arr4 = Array(1,2)

def matchArr (arr: Array[Int]) ={
  arr match {
    case Array(0) => "0"
    case Array(x,y) => x + " " + y
    case Array(0, _*) => "0 ..."
    case _ => "something else"
  }
}
matchArr(arr)
matchArr(arr2)
matchArr(arr3)
matchArr(arr4)

// Lab Exercise Part 1 - Pattern Matching
// Function swap that receives a pair of
// integers and returns the pair with
// components mapped

def swap(intPair: (Int, Int))= intPair match {case (x, y) => (y, x)}
swap((1,2))

def swapArr(arr: Array[Int]) = arr match {
  case Array(x,y, rest @ _*) => Array(y, x) ++ rest
  case _ => arr
}

swapArr(Array(1,2,3))
swapArr(Array(1))
swapArr(Array(1,2))


// Lab Exercise Part 2
// Article - has a description and price
// Bundle - has a description and a bunch of items
// Write function to compute the price of each of the following classes
abstract class Item
case class Article(description: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, items: Item*) extends Item

val book = Article("Scala for the Impatient", 39.95)
val bottle = Article("Old Potrero Straight Rye Whiskey",79.95)
val gift = Bundle("gift bundle", 10 , book,bottle)


// Recursive data structures
def price(it:Item):Double = it match {
    case Article(_, p) => p
    case Bundle(_, discount, its @ _*) => its.map(price).sum - discount
}
price(book)
price(gift)

// for the following special, write a one line assignment that extracts
// the description and price of the first article
val special = Bundle("Father's day special", 20.0,
  book, Bundle("Anchor Distillery Sampler", 10.0, bottle, Article("Junipero Gin", 32.95)))

val Bundle(_,_, Article(descr, pr), _*) = special


// Lab Exercise Part 3 - The Option Type
abstract class DoubleOption
case class SomeDouble(value : Double) extends DoubleOption
case object NoDouble extends DoubleOption

def inv(x: Double)= if (x==0) NoDouble else SomeDouble(1/x)
inv(5)
inv(0)

def f(x:Double) = if(x<=1) SomeDouble(math.sqrt(1-x)) else NoDouble

def compose(fn1:(Double) => DoubleOption, fn2:(Double) =>DoubleOption) =
  (x:Double) => fn2(x) match {
    case SomeDouble(result) => fn1(result)
    case NoDouble => NoDouble
  }

val h = compose(f, inv)
h(0)
h(1)
h(2)
h(0.25)

def isEmpty(x : DoubleOption) = x match {
  case NoDouble => true
  case _ => false
}

def get( x: DoubleOption) = x match {
  case SomeDouble(a) => a
  case _ => throw new NoSuchElementException
}


// Repeating the above case classes using OOP and polymorphism
abstract class DoubleOptionPoly{
 def isEmptyPoly: Boolean
  def getPoly:Double
}
class SomeDoublePoly (val value: Double) extends DoubleOptionPoly{
  def isEmptyPoly: Boolean = false
  def getPoly = value
}

object  NoDoublePoly extends DoubleOptionPoly{

  def isEmptyPoly: Boolean = true
  def getPoly = throw new NoSuchElementException
}

// We can notice that each of the classes is responsible for
// implementation of the methods
// Case classes implementation is better than this


// What haven't been covered completely:
// 1. Files and RegEx
// 2. Collections
// 3. Annotations
// 4. Type parameters(generics, variance)
// 5. Advanced Types
// 6. Implicits
// 7. Futures and actors for concurrent programming

// For website development : Play or Scalatra
// For bigdata - Spark