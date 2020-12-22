package blog

// https://blog.rockthejvm.com/scala-3-indentation/

object Indentation {
  
  def main(args: Array[String]): Unit = {
    // if-statements

    // one-liner
    val aCondition = if (2 > 3) "bigger" else "smaller"

    // java-style
    val aCondition2 =
      if (2 > 3) {
        "bigger"
      } else {
        "samller"
      }

    // compact
    val aCondition3 =
      if (2 > 3) "bigger"
      else "samller"

    // Scala 3 with indentation
    /*
    When we remove the parens around 2 > 3, indentation becomes significant for this expression. 
    This means that the else branch must be at least at the indent level of the if
     */
    val aCondition4 =
      if 2 < 3
        "bigger"
      else
        "samller"

      // scala-3 one-liner
      val aCondition5 = if 2 > 3 then "bigger" else "smaller"

      // scala-3 compact
      val aCondition6 =
        if 2 > 3 then "bigger"
        else "samller"

    // For Comprehensions
    // normal in scala 2.x
    for {
      n <- List(1,2,3)
      c <- List("a", "b", "c")
    } yield s"$c$n"
    
    // Scala 3: Without braces, those indents are now mandatory
    for 
      n <- List(1,2,3)
      c <- List("a", "b", "c")
    yield s"$c$n"
    
    // Scala 3: methods without braces: Have to be indented
    def meaningOfLife(year: Int): Int =  // indentation
      println("computing")
      
      42
    
    println(meaningOfLife(2020))
    
    // An indentation region is also created when we define classes, 
    // traits, objects or enums followed by a colon : and a line break
    
    class Animal:
      def eat(): Unit =
        print("eating")
    end Animal  // Optional: end token to differentiate which code belongs to which indentation region
    
    // Function argumentsPermalink: See in the Article for more
    
    given myOrder as Ordering[Int]:
      def compare(x: Int, y: Int) = x - y
    
  }
}
