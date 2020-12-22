package blog

// https://blog.rockthejvm.com/scala-3-infix-methods/

import scala.annotation.infix

object InfixMethods {
  
  // permissive with methods names in Scala:  ::, ++, -->
  // infix methods
  
  case class Person(name: String) {
    @infix // only required for alphanumeric method names
    def likes(movie: String): String = s"$name likes $movie"
  }
  
  val person = Person("Dave")
  person.likes("Batman")
  person likes "Joker"  // identical -- only use infix for a method that has one arg only
  
  /*
    In Scala 3, infix notation for “regular” (i.e. alphanumeric) methods will be discouraged 
    and deprecated (according to the docs starting in Scala 3.1). Expressions like Dave likes 
    "Batman" will now compile with a warning. However, Scala 3 allows us to remove the 
    warning if the method comes annotated with @infix:
   */

  @infix
  def (person: Person).enjoys(musicGenre: String): String = s"${person.name} listens to $musicGenre"
  
  // extension method
  extension (person: Person)
    @infix
    def enjoys2(musicGenre: String): String = s"${person.name} listens to $musicGenre"
    
  def main(args: Array[String]): Unit = {
    println(person.enjoys2("rock"))
    println(person enjoys2 "romance")
  }
}
