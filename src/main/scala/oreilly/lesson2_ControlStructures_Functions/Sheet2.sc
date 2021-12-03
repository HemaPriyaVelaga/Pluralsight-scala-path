val x=4
if(x>0) 1 else -1 // If expr is a value and not just a statement
val result = if(x>3) "Greater than 3" else -1

// Unit type
val result2 = if(x<3) "Less than 3"
val seeResult2Type:Unit = ()

val n = 10
for(i <- 1 to n) println(i)
for (i <- "Hema Priya")println(i)
// <- is called a generator
// for loops can have multiple generators

for(i <- 1 to 3; j <- 1 to 5) println((10 * i + j) + " ")

// Guards in for statement = adding if statement
for(i <- 1 to 3; j <- 1 to 5 if i == j) println((10 * i + j) + " ")

for (i <- 1 to 10) yield i % 5


// FUNCTIONS

def abs(x:Double) = if(x>=0) "Positive" else "Negative"
abs(-10)

// For a recursive function, we MUST specify the
// return type or else it is a CE

def fact(n:Int) : Int = if(n<=0) 1 else n*fact(n-1)
fact(5)

def printName (name : String): Unit = {
  println(name)
}
printName("Hema Priya Velaga")

// VarArgs
def sum(args:Int * ) = {
  var result = 0
  for( i <- args) result += i
  result
}
sum(1,2,3,4,5)
sum(1,2)
sum(1 to 5:_*)


// LAB EXERCISES

// Function to test whether a character is lowercase vowel
def isLowercaseVowel(ch:Char) = {
  if("aeiou".contains(ch)) "Lowercase Vowel" else "No"
}
isLowercaseVowel('A')
isLowercaseVowel('u')

// Function that returns all vowels from a give string

// using for loop
def vowelsInString(s:String) =
  {
    var vowel = ""
    for(c <- s) {
      if(isLowercaseVowel(c)=="Lowercase Vowel") vowel += c
    }
    vowel
  }

vowelsInString("HemaPriyaVelaga")

// using for yield loop (easier to read)
def stringVowels(s:String)= for (c <- s if isLowercaseVowel(c)=="Lowercase Vowel") yield c
stringVowels("HemaPriyaVelaga")
// here, yield just collects things from the for loop
// so, with the help of if statement, we specify what we want to be collected
// and since it returns a collection, we need not add any return variable

// doing the same recursively
def stringVowelsRecursive(s:String): String =
  {
    if (s.length == 0) ""
    else{
      val ch = s(0)
      val rest = stringVowelsRecursive(s.substring(1))
      if(isLowercaseVowel(ch)=="Lowercase Vowel")
        ch + rest
      else
        rest
    }
  }
stringVowelsRecursive("HemaPriyaVelaga")



// doing with while loop
def stringVowelsWhile(s:String) =
  {
    var vowel = ""
    var n = s.length
    while(n>0) {
      if(isLowercaseVowel(s(n-1))=="Lowercase Vowel") vowel += s(n-1)
      n-=1
    }
    vowel
  }
stringVowelsWhile("HemaPriyaVelaga")


// default and named parameters in function
def isVowel(c:Char, vowels:String = "aeiou") = {
  vowels.contains(c)
}

def stringVowelsWithDefaultParams(s:String, vowelChars:String = "aeiou", ignoreCase:Boolean = true)= {
  if(ignoreCase)
    for (c <- s if isVowel(c.toLower, vowelChars)) yield c
  else
    for (c <- s if isVowel(c, vowelChars)) yield c
}

stringVowelsWithDefaultParams("HemaPriyaVelagaEEE", ignoreCase = false)
stringVowelsWithDefaultParams("mädchen","äöüÄÖÜaeiou")









