// Lab exercise Part 2 and 3: Uniform Access and Operators

class Time(h:Int, m:Int = 0) {
  private  var minutesSinceMidnight = h * 60 + m
  def hours = minutesSinceMidnight/60
  def minutes = minutesSinceMidnight % 60
  def minutes_=(newMinutes : Int) {
    if(newMinutes<0 || newMinutes>59)
      throw new IllegalArgumentException
    minutesSinceMidnight = hours * 60 + newMinutes
  }
  if((h<0 || h>23) || (m<0 || m>59)) throw new IllegalArgumentException
  override def toString: String = f"Time : ${hours} hours and ${minutes} minutes"
  def before(other:Time) = minutesSinceMidnight < other.minutesSinceMidnight
  def <(t2:Time) = minutesSinceMidnight<t2.minutesSinceMidnight
  def -(t2:Time) = minutesSinceMidnight - t2.minutesSinceMidnight
}

object Time{
  def apply (hours:Int, minutes:Int) = new Time(hours, minutes)
}

val afternoon = new Time(12,50)
afternoon.before(new Time(11, 59))
afternoon.before(new Time(13, 49))
//val time2 = new Time(23, 60) // Throws exception
val time2 = new Time(13)
val morning = Time(10, 30)

// The main advantage of this uniform access principle is that
// though we made a lot of changes in the above class
// compared to that in the previous sheet
// the way we declare the class object and use its methods,
// as a user of the class, did not have to be changed

time2.minutes = 30 // this is possible due to the minutes_= method
// and changing the minutesSince Midnight variable to var

//time2.minutes = 400 // throws illegal arg exception

// for the following, check out the last 2 methods in the class
afternoon<morning // afternoon.<(morning)
morning<afternoon // < is a method of time class
afternoon-morning // afternoon.-(morning)
morning-afternoon // - is a method of the time class
