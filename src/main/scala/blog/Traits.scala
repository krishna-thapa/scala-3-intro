package blog

import java.awt.Color

// https://blog.rockthejvm.com/scala-3-traits/

object Traits {
  
  // Trait can receive constructor arguments
  trait Talker(subject: String) {
    def talkTo(another: Talker): String = "" // making non-abstract method 
  }

  class Person(name: String) extends Talker("rock music"){
    override def talkTo(another: Talker): String = ???
  }
  
  /*
    What happens if you mix-in a trait with one argument in one place, and with another argument in another place?
    The short answer is that won’t compile. The rule is: if a superclass already passes an argument to the trait, 
    if we mix it again, we must not pass any argument to that trait again
   */
  class RockFan extends Talker("rock")
  class RockFanatic extends RockFan with Talker  //must not pass argument here
  
  /*
    What happens if we define a trait hierarchy? How should we pass arguments to derived traits?
    Again, short answer: derived traits will not pass arguments to parent traits
   */
  trait BrokenRecord extends Talker  // Passing arguments to parent traits will not compile.
  
  // How are we now supposed to mix this trait into one of our classes?
  
  //class AnnoyingFriend extends BrokenRecord("politics")  // Not Okay because the BrokenRecord trait doesn’t take arguments
  
  class AnnoyingFriend extends BrokenRecord with Talker("politics")  // Ok
  
  
  // Super traits 
  super trait Paintable
  trait Color
  case object Red extends Color with Paintable
  case object Green extends Color with Paintable
  case object Blue extends Color with Paintable
  
  val color = if(43 > 2) Red else Blue  // full inferred type 
  
  /*
    The thing is that we rarely use the traits Product or Serializable as standalone types we 
    attach to values. So Scala 3 allows us to ignore these kinds of traits in type inference, 
    by making them a super trait
   */
  
  // trait Paintable is rarely used as a standalone trait, but rather as an auxiliary trait in our library definitions.
  // After that we’ll see that our variable color is now marked as Color.
  
  // When Scala 3 comes out, the traits Product, Comparable (from Java) and Serializable (from Java) 
  // will be automatically be treated as super traits in the Scala compiler.
  
  def main(args: Array[String]): Unit = {
    
  }
}
