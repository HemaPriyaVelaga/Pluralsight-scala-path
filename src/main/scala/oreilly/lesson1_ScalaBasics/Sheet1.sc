6 * 7
val a = 6 * 7
a
val b : BigInt = 6 * 7
b pow 1000  // same as b.pow(1000)
// val always need to be assigned. Or else it will CE

import scala.math._
// In scala, imports can be anywhere
// and not just on the top of the file
// we can use this import to all the code below it
sqrt(10) * sqrt(10)

1.to(10).map(println(_))
1.to(10).map(sqrt(_))

6.*(7)

"Rhine".permutations.toArray.length
"Mississippi".permutations.toArray.length

"ABC".sum // Adds ascii val of the strs and converts back to ascii char
// sum method works with sequences of any type and gives the result
// in the context of that type
"A" + "B" + "C"
'A' + 'B' + 'C'
('A' + 'B' + 'C').toChar
'Æ'.toInt
'Æ'.toDouble













