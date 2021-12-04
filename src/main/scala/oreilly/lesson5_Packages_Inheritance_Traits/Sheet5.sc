import java.io.{FileInputStream, InputStream}
import java.util.Date

// Traits are like interfaces in Java
// Mixins and Layered Traits

trait Logged{
  def log(msg:String): Unit = {
    println("In Logged, message is : " + msg)
  }
}

trait ConsoleLogger extends Logged
{
  override def log(msg: String): Unit = {
    println("In ConsoleLogger, message is : " + msg)
  }
}

trait SomeLogger extends Logged
{
  override def log(msg: String): Unit = {
    println("In SomeLogger, message is : " + msg)
  }
}

class SavingsAccount extends Logged
{
  private var balance: Double = 0
  def withdraw(amount: Double): Unit =
  {
    if(amount > balance) log("Insufficient Funds!!")
    else balance-=amount
  }
}

// We can 'mix in' any trait we want with the class
val acct = new SavingsAccount with ConsoleLogger
acct.withdraw(1000)

val acct2 = new SavingsAccount with SomeLogger
acct2.withdraw(1000)

val acct3 = new SavingsAccount with Logged
acct3.withdraw(1000)


// Trait Layers

trait  TimestampLogger extends Logged{
  override def log(msg: String): Unit = {
    super.log("In TimeStampLogger : " + new Date() + ": " + msg)
  }
}

trait ShortLogger extends Logged{
  val len = 10
  override def log(msg: String): Unit = {
    super.log(" In ShortLogger, " + msg + " len = " + len)
  }
}

val acct4 = new SavingsAccount with ConsoleLogger with TimestampLogger with ShortLogger{
  override val len: Int = 20
}
acct4.withdraw(1000)
// the log message will be first passed to
// shortlogger, then the message created by this logger
// will be passed to timestamp logger, whose new modified message
// will be passed to console logger
// So, the sequence here is reverse as to what is declared
// But it will "appear" in the same order when printed


// Lab Exercise Part I
// a)

import java.awt._
val rect = new Rectangle(5,10,20, 30)
rect.translate(10, 20)
rect

val egg = new geom.Ellipse2D.Double(5,10,20,30)
//egg.translate(10,20) // No translate method in ellipse class
// to make it available for ellipse, create it
// defining a trait

trait RectangleLike{
  def setFrame(x:Double,y:Double,w:Double,h:Double):Unit
  def getX:Double
  def getY:Double
  def getWidth:Double
  def getHeight:Double
  def translate(dx : Double, dy: Double): Unit ={
    setFrame(getX + dx, getY + dy, getWidth, getHeight)
  }

  override def toString: String = f"(${getX}, ${getY}, ${getWidth}, ${getHeight})"
}

val movableEgg = new geom.Ellipse2D.Double(5,10,20,30) with RectangleLike
movableEgg.toString
movableEgg.translate(10, 20)
movableEgg.toString


// Lab Exercise Part II - Reversing the Mixin Order
val acct5 = new SavingsAccount with ConsoleLogger with ShortLogger with TimestampLogger{
  override val len: Int = 20
}
acct5.withdraw(1000)

val acct6 = new SavingsAccount with ShortLogger with TimestampLogger with ConsoleLogger
acct6.withdraw(1000)
// Here, only the console logger msg will be printed
// because it did not call the super log method

// So, when you want to mix in these traits,
// you have to pay attention to the order
// in which you mix them in.
// Make it so that the ones who do decorating come first,
// and the ones that disposes of it comes last.



// Lab Exercise Part 3 : Buffering
//Buffering means that it is plainly
// inefficient to read a character at a
// time from a disk file. so read it in a huge chunk at a time

trait Buffered extends InputStream with  ConsoleLogger {
  val SIZE = 1024
  private var end = 0
  private val buffer = new Array[Byte](SIZE)
  private var pos = 0

  override def read()= {
    if(pos == end)
      {
        log("Buffer was empty")
        end = super.read(buffer, 0, SIZE)
        pos = 0
      }
      if(pos == end) -1
      else{
        log("Buffer is Not empty")
        pos+=1
        buffer(pos-1)
      }
    }

}

val newInputStream = new FileInputStream("/usr/share/dict/words") with Buffered with ConsoleLogger
newInputStream.read()
newInputStream.read()









