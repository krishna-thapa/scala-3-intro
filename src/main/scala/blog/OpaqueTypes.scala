package blog

// https://blog.rockthejvm.com/scala-3-opaque/

object OpaqueTypes {

  /*case class Name(value: String) {
    // additional logic
  }*/
  
  object SocialNetwork { // "domain"
    opaque type Name = String
    // Name == String
    
    // 1. Companion object
    object Name {
      def fromString(s: String): Option[Name] =
        if(s.isEmpty || s.charAt(0).isLower) None else Some(s) // simplified
    }
    
    // 2. Extension methods
    extension (n: Name) {
      def length: Int = n.length  // on the String class
    }
  }
  
  import SocialNetwork._
  // Outside the scope, Name != String since it is defined as opaque type
  // val name: Name = "Krishna"  // will not compile

  // Opaque type definitions can have type restrictions, much like regular type aliases
  object Graphics {
    opaque type Color = Int  // Only the inside object knows that Color is type of Int
    opaque type ColorFilter <: Color = Int // Upper bound

    val Red: Color = 0xFF000000
    val Blue: Color = 0x00FF0000
    val halfTransparency: ColorFilter = 0x88  // 50% transparancy
  }

  import Graphics._
  case class OverlayFilter(c: Color)
  
  // use Color and ColorFilter in the same style as we use a class hierarchy
  val fadeLayer = OverlayFilter(halfTransparency)  // ColorFilter "extends" Color
  
  def main(args: Array[String]): Unit = {
    val nameOption: Option[Name] = Name.fromString("Krishna")  // Some("Krishna")
    nameOption.foreach(println)
    
    /*
      The bad news is that this new type has no API of its own. Even if it’s implemented as a String, 
      you don’t have access to any String methods. However, the good news is that you now have a fresh 
      zero-overhead type whose API you can write from scratch.
     */
    //nameOption.map(_.charAt(1)) // will not compile
    
    val nameLenghtOption = nameOption.map(_.length)  // length method defined as extension methods
    nameLenghtOption.foreach(println)
  }
}
