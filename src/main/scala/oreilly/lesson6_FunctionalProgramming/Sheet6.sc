import javax.swing.JPopupMenu.Separator
// In scala we prefer to use higher order functions
// instead of loops

// Lab exercise part I - Life without loopd
val zones = java.util.TimeZone.getAvailableIDs
zones.length
// To get reid of continents from the above and get every 10th of the remaining:
val filteredZones = zones.map(_.split("/"))
  .filter(_.length>=2) // same as .filter(arr => arr.length>=2)
  .map(_(1))  // same as .map(arr => arr(1))
  .grouped(10) // returns iterator
  .toArray
  .map(_(0)) // converting array of array to an array
filteredZones.length


// Lab exercise part 2 - Reductions
1 to 10 reduceLeft(_ * _) // returns product of 1 - 10
// function to write n factorial :
def nFact(n:Int) = 1 to n reduceLeft(_ * _)
nFact(5)

// function to write 2 ^ n
// using the above method
def pow2n(n:Int) = 1 to n map(_ => 2) reduceLeft(_*_) // replicate number 2 n times and multiply all ele
pow2n(4)
pow2n(6)

def xpowy(x:Int, y:Int) =  1 to y map(_=> x) reduceLeft(_ * _)
xpowy(3, 2)

// Function to concat strings with a separator in between
def concat(strings : Seq[String], separator: String) = strings.reduceLeft(_ + separator + _)
concat(Array("Mary", "had", "a", "little", "lamb"), " ")
concat(Array("Hema", "is", "a", "good", "girl"), ",")


// Lab exercise Part 3 - DIY while
def While(cond: => Boolean)(body: => Unit): Unit =
{
  if(cond)
    {
      body
      While(cond)(body)
    }
}

val n = 10
var i = 1
var f = 1
//While(() => i<n, () => { f*=i ; i+=1}); f
//While(i<n, {f*=i; i+=1})
While(i<n){f+=i; i+=1} // Currying the above








