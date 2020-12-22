package blog

// https://blog.rockthejvm.com/scala-3-given-using/

// census
case class Person(surname: String, name: String, age: Int)

object Givens {
  
  // use everywhere
  val personOrdering: Ordering[Person] = new Ordering[Person] {
    override def compare(x: Person, y: Person): Int = 
      x.surname.compareTo(y.surname)
  }
  
  def listPeople(persons: List[Person])(ordering: Ordering[Person]): Seq[Person] = ???
  def methodWithOrdering(dave: Person, alice: Person)(ordering: Ordering[Person]): Int = ???
  
  // find the "standard" value
  // expicitly call methods
  
  // given/ using
/*  given standardPersonOrdering as Ordering[Person] {
    override def compare(x: Person, y: Person): Int =
      x.surname.compareTo(y.surname)
  }*/

  // import givens from different object 
  // 1. imporrt expliclity 
  //import StandardValues.standardPersonOrdering 
  
  // 2. import given for a TYPE (the only one)
  //import  StandardValues.{given Ordering[Person]}

  // 3. import all givens
  import StandardValues.{given _}
  
  //import StandardValues._  // doest NOT import givens
  
  def methodsWithStandardOrdering(persons: List[Person])(using ordering: Ordering[Person]): List[Person] = ???
  
  methodsWithStandardOrdering(List(Person("Don", "John", 21), Person("Potter", "Harry", 26)))  // given is injected here
  
  // why? much cleaner code
  
  // deriving givnes
  // working with Options
  
  // create a given Ordering[Option[T]] if we had a Ordering[T] in scope
  given optionOrdering[T](using normalOrdering: Ordering[T]) as Ordering[Option[T]] {
    override def compare(x: Option[T], y: Option[T]): Int = (x, y) match {
      case (None, None) => 0
      case (None, _) => -1
      case (_, None) => 1
      case (Some(a), Some(b)) => normalOrdering.compare(a, b)
    }
  }
  
  def sortThings[T](things: List[T])(using ordering: Ordering[T]) = ???
  
  val maybrPersons: List[Option[Person]] = List.empty
  sortThings(maybrPersons)  // optionOrdering(StandValues.standardPersionOrdering))
  
  /*
    Where are givens useful?
      - type classes 
      - dependency injection
      - contextual abstractions, i.e. ability to use code for some types but not for others
      - type-level programming
   */
  
  /*
    In the simplest terms, a using clause is a marker to the compiler, so that if it can find 
    a given instance of that type in the scope where that definition is used (e.g. a method call), 
    the compiler will simply take that given instance and inject it there.
    
    The obvious restriction is that there cannot be two given instances of the same type in the same scope, 
    otherwise the compiler would not know which one to pick.
   */
}

object StandardValues {
  given standardPersonOrdering as Ordering[Person] {
    override def compare(x: Person, y: Person): Int =
      x.surname.compareTo(y.surname)
  }
}