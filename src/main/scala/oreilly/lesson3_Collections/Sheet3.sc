import java.util.Scanner

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

val a = new Array[Int](10)
for(i<- 0 until a.length) a(i) = i*i
a
for(elem <- a) println(elem)


// Array Buffers
val b = new ArrayBuffer[Int]
b+=6

val c = ArrayBuffer("hema", "priya")
c += "velaga"
c += ("Hima", "Bindu")

c ++=Array("Velaga", "Kavitha")
c.remove(3)

c.insert(3, "family")
c

// Trims last 5 ele from array
c.trimEnd(5)

// Array computations

val arr = Array(1,2,3,4)
arr.sorted
arr.reverse // doesnt change the original array
arr.max
arr.sum

arr.toString // returns the object type
arr.mkString(" - ") // to print it as string
arr.mkString("[", ",", "]")


// Maps
val scores = Map("Hema" -> 1, "Hima" -> 2)
val mscores = scala.collection.mutable.Map("Kavitha" -> 3, "Venu" -> 4)
"Alice" -> 10 // tuple
scores("Hema")
//scores("noElement") // throws no element exception
scores.getOrElse("ifNoELeGetaVal", 0)
mscores("newEle") = 5
mscores

// Adding/removing KV pairs to mutable maps
mscores += ("Priya" -> 3, "Bindu" -> 4)
mscores -= "Hema"

// adding or removing to or from immutable maps creates new map
var newScores = scores + ("Bob" -> 10)
newScores = newScores - "Bob"  // mutation happens
newScores // mutation might be dangerous in certain apps

for ((k,v) <- scores) yield (v,k)
scores.keySet
scores.values
scores.keys


// Tuples
val t3 = (1,2.3,"Hema")
t3._3
val(_,d,e) = t3 // assignming 2nd val to a and 3rd val to b

// Lab exercise part 1:
// remove all but the first negative number
val posNegArr = ArrayBuffer(1,2,-3,4,-5,6,-7)
def removeAllNumExceptFirstNeg (arr : ArrayBuffer[Int]): Unit = {
  val negIndices = for(index <- 0 until arr.length  if (arr(index)<0)) yield index
  arr.trimEnd(arr.length - negIndices(0) - 1)
  arr.trimStart(negIndices(0))
}
removeAllNumExceptFirstNeg(posNegArr)
posNegArr

// to remove only negative numbers leaving aside the first negative num
val posNegArr2 = ArrayBuffer(1,2,-3,4,-5,6,-7)
def removeOnlyNegEleExceptFirst (arr:ArrayBuffer[Int]) = {
  val indicesToRemove = (for(index <- 0 until arr.length  if (arr(index)<0)) yield index).drop(1)
  //val indicesToRemove = negIndices.drop(1) // drops 1 element from the start. Dont confuse 1 with the index
  for(i <- indicesToRemove.reverse) arr.remove(i)
}
removeOnlyNegEleExceptFirst(posNegArr2)
posNegArr2

// the above 2 functions mutate the array buffer inplace.
// But we may not want to do that
// To return a new array instead of mutating the array buffer:

def returnNewArr(arr: ArrayBuffer[Int]) = {
  val indicesToRemove = (for(index <- 0 until arr.length  if (arr(index)<0)) yield index).drop(1)
  for(i <- 0 until arr.length if !indicesToRemove.contains(i)) yield arr(i)
}
val posNegArr3 = ArrayBuffer(1,2,-3,4,-5,6,-7)
returnNewArr(posNegArr3) // returns a new array instead of modifying the original one
posNegArr3


// Lab exercise part 2 :
// a) Word count with mutable map
val filename = "/Users/Z0084MD/Desktop/Data_Engg_Onboarding/Nov 2021/Day3/resources/txt/alice.txt"
val inputFile = Source.fromFile(filename).getLines.mkString.split(" ")

val mutableWordCount = scala.collection.mutable.Map[String, Int]()
for(word <- inputFile)
  {
    mutableWordCount(word) = mutableWordCount.getOrElse(word, 0) + 1

  }
for ((k,v) <- mutableWordCount) println(k , ": " , v)

// b) Using immutable map -> declare it as var
var immutableCount = Map[String, Int]()
for(word <- inputFile)
  {
    immutableCount = immutableCount + (word -> (immutableCount.getOrElse(word, 0) + 1))
  }
immutableCount



// Lab exercise 3 : Grouping

// Group words by their first letter
val wordArray = Array("Hema", "Hima", "Velaga", "Kavitha", "Venu", "Gopala", "Rao", "Priya", "Bindu")
wordArray.groupBy(_.substring(0,1)) //from 0th index and length of substr is 1


// Grouping words by their length
wordArray.groupBy(_.length)


// Lab exercise 4: Partitions and Zips
// Tuples are very useful to yield more than one result
"New York Madness".partition(_.isUpper) // returns a tuple with 2 entries

// Zip method takes 2 collections of the same length and zips them together
// into a collection of tuples.
val symbols = Array("<", "-", ">")
val counts = Array(1,2,3)
val pairs = symbols.zip(counts)
for((s,n) <- pairs) println(s * n)


















