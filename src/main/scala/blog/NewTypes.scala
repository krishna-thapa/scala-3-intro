package blog

import java.io.File

// https://blog.rockthejvm.com/new-types-scala-3/
object NewTypes {

  // 1. Literal types
  // Used to enforce compile-time checks to your definitions so that 
  // you don't go reckless with your code
  val aNumber: Int = 3
  val three: 3 = 3 // 3 <: Int
  
  //normally
  def passNumber(n: Int) = println(n)
  passNumber(24)
  passNumber(three) //ok
  
  def passStrict(n: 3) = println(n)
  passStrict(three)
  //passStrict(24)  //not okay
  
  val pi: 3.14 = 3.14
  val truth: true = true
  val language: "Scala" = "Scala"
  val answer: Option[42] = Some(42) //or None
  
  // 2. Union types: A or B (!= Either)
  def ambivalentMethod(arg: String | Int) = arg match {
    case _: String => println(s"a string: $arg")
    case _: Int => println(s"an int: $arg")
  }
  
  ambivalentMethod(42)
  ambivalentMethod("Scala")
  
  type ErrorOr[T] = T | "error" // this puts some restraints on what you can do with your values
  def handleResponse(file: ErrorOr[File]): Unit = {
    // your code here
  }
  
  val stringOrInt = if(43 > 0) "a string" else 43 // Any, as inferred by the compiler
  val aStringOrInt: String | Int = if(43 > 0) "a string" else 43  // okay
  
  // 3. Interaction types: A and B
  trait Camera {
    def takePhoto() = println("snap")
  }
  trait Phone {
    def makeCall() = println("ring")
  }
  
  def useSmartDevice(sp: Camera & Phone): Unit = {
    //  can use both types’ methods inside
    sp.takePhoto()
    sp.makeCall()
  }
  
  //The intersection type will also act as a type restriction, 
  // so we need to mix in both traits if we are to use this method properly:
  class SmartPhone extends Camera with Phone
  useSmartDevice(new SmartPhone)  // ok
  
  
  trait HostConfig
  trait HostController {
    def get: Option[HostConfig]
  }
  
  trait PortConfig
  trait PortController {
    def get: Option[PortConfig]
  }
  
  //  what if the two types share a method signature except the returned types
  def getConfigs(controller: HostController & PortController): Option[HostConfig & PortConfig] = controller.get
  
  //any real type that can extend both HostController and PortController must implement 
  // the get method such that it returns both an Option[HostConfig] and Option[PortConfig]
  
  def main(args: Array[String]): Unit = {
    println("Hello world")
  }
}
