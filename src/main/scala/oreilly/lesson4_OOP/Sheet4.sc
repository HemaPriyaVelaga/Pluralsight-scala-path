println("Scala Object Oriented Programming")

class Point(val x:Double, val y:Double)
{
  println(f"Welcome to ($x, $y)")
  // the above message gets printed everytime a
  // new point is constructed
  def move(dx:Double, dy:Double) = new Point(x + dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x + y*y)
  def *(factor:Double) = new Point(x*factor, y*factor)
  // methods can be named with any symbol in unicode
  override def toString: String = f"(${x}, ${y})"
}

val p = new Point(3,4)
p.move(10,20) // returns a new point
p.distanceFromOrigin
p.x
p.y
p.*(2) // * is a method in the class
p * 2 // This is same as the above line


// Infix notation in methods

1 to 10 map (3 * _) filter (_ % 5 == 2)
// same as:
// 1.to(10).map(3*_).filter(_%5==2)


// OBJECT
object Accounts{
  private var lastNumber = 0
  def newUniqueNumber() = {
    lastNumber +=1
    lastNumber
  }
}
Accounts
Accounts.newUniqueNumber()
Accounts.newUniqueNumber()

// COMPANION OBJECT
object Point{
  def apply(x:Double, y:Double) = new Point(x,y)
}
val p2 = Point(3,4) * 4

// Lab Exercise part I
class Time(val hours:Int, val minutes:Int = 0) { // constructor that needs only hours

  // Checking for invalid time
  if((hours<0 || hours>23) || (minutes<0 || minutes>59))
    throw new IllegalArgumentException


  override def toString: String = f"Time : ${hours} hours and ${minutes} minutes"
  def before(other:Time) ={
    if (other.hours < this.hours)
        true
    else if (other.hours == this.hours && other.minutes < this.minutes)
        true
    else
      false
  }
}

object Time{
  def apply (hours:Int, minutes:Int) = new Time(hours, minutes)
}

val time1 = new Time(12,50)
time1.before(new Time(11, 59))
time1.before(new Time(13, 49))

//val time2 = new Time(23, 60) // Throws exception
val time2 = new Time(13)
val time3 = Time(10, 30)
// The above kind of declaration is possible due
// to the companion class i.e, the time object


