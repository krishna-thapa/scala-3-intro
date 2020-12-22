package blog

//https://blog.rockthejvm.com/enums-scala-3/

object Enums {

  // Finally, Scala has first-class enums like any standard programming language
  enum Permissions {
    case READ, WRITE, EXEC, NONE
  }
  
  // Under the hood, the compiler generates a sealed class and 4 well-defined values as constants in its companion object.
  val read: Permissions = Permissions.READ
  
  // enums can also have arguments, and the constants will have to be declared with a given expression
  enum PermissionsWithBits(val bits: Int) {
    case READ extends PermissionsWithBits(4)  // 100
    case WRITE extends PermissionsWithBits(2)  // 010
    case EXEC extends PermissionsWithBits(1)  // 001
    case NONE extends PermissionsWithBits(0)  // 000

    // Enums can contain fields and methods, just like a normal class - they’re compiled to a sealed class after all
    def toHex: String = Integer.toHexString(bits)

    // can also create variables but don't recommended since it is like creating a globval variables
  }
  
  //  create companion objects
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // do checking
      PermissionsWithBits.NONE
  }
  
  val read2: PermissionsWithBits = PermissionsWithBits.READ
  val bitString: Int = read2.bits
  
  // call the method
  val hexString = read2.toHex
  
  // Enums come with some predefined utility methods
  // to check the “index” of a given enum value inside the “order” of definition of cases
  val first = Permissions.READ.ordinal
  println("Index of first enum value " + first)
  
  // ability to fetch all possible values of an enum type, perhaps to iterate over them or to consider all at once
  val allPermissions = Permissions.values  // array with all the possible values
  allPermissions.foreach(println)
  
  // ability to convert a String into an enum value
  val readPermission: Permissions = Permissions.valueOf("READ")  // Permissions.READ
  println(s"Value of READ: $readPermission")
  
  def main(args: Array[String]): Unit = {
    println("Enums in Scala 3")
  }
}
