package blog

// https://blog.rockthejvm.com/scala-3-type-lambdas/

object TypeLambdas {

  /*
    - kinds = type of types
    - Int, String = value-level kind (level 0) => attach to values
    - List, Option = level 1 kind ("generics")
    - Functor, Monad = level 2 kind ("generics of generics")
    Generic types can’t be attached to values on their own; they need the right type arguments 
    (of inferior kinds) in place. For this reason, they’re called type constructors.
   */
  
  val aNumber: Int = 42
  val aList: List[Int] = ???
  
  class Functor[F[_]]
  val functionOption = new Functor[Option]
  
  // List is similiar to a function == type contructor
  type MyList = [T] =>> List[T]  // MyList == List
  type MapWithStringKey = [T] =>> Map[String, T]
  
  val addressBook: MapWithStringKey[String] = Map()
  
  type MapWithStringKey2[T] = Map[String, T] // exactly the same as MapWithStringKey
  
  type SpecialEither = [T, E] =>> Either[E, Option[T]]
  val specialEither: SpecialEither[Int, String] = Right(Some(2))  // Same as Either[String, Option[Int]]
  
  // monads
  trait Monad[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A, B](ma: M[A])(transformation: A => M[B]): M[B]
  }
  
  // monads for Either
  //class EitherMonad[E] extends Monad[Either[E, ?]] {}
  // the above structure is not valid Scala
  // work for Either[String, Int], Either[String, String], Either[String, Person]
  
  // It’s as if we had a two-argument function, and we needed to pass a partial application of it to another function.
  class EitherMonad[E] extends Monad[[T] =>> Either[E, T]] {
    override def pure[A](value: A) = ???
    override def flatMap[A, B](ma: Either[E, A])(transformation: A => Either[E, B]): Either[E, B] = ???
  }
  
  def main(args: Array[String]): Unit = {
    
  }
}
