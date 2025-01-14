package Conspect

object PatternMatchingClasswork15 extends App {

  val i = 4

  val month = i match {
    case 1 => "January"
    case 2 => "February"

  }

  val oddResults = i match {
    case 1 | 3 | 5 => "odd"
    case 2 | 4 => "even"
  }

  val cmd = "start"
  cmd match {
    case "start" | "go" => println("starting")
    case _ => println("error")
  }

  def echoWhatYouGaveMe(x: Any): String = x match {
    // constant patterns - exact match to some constant
    case 0 => "zero"
    case true => "true"
    case "hello" => "you said 'hello'"
    case Nil => "an empty List"
    // sequence patterns
    case List(0, _, _) => "a three-element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
    case Array(1, _*) => "an array beginning with 1, having any number of elements"
    case Array(1.7, _*) => "an array starting with 1.7, any number of elements"
    case Vector(1, _*) => "a vector starting with 1, having any number of elements"
    //    case Map(("one",1), _*) => "map!" //TODO implement specific Map matching
    // tuples
    case (a, b) => s"got $a and $b"
    case (a, b, c) => s"got $a, $b, and $c"
    case (_, _, _, _) => s"got a tuple with 4 elements (any types inside)"
    // constructor patterns
    //again more specific patterns first!
    case Person("valdis", "myself", 105) => s"Oh hi Valdis, your relation is myself, speed - 105 !!!"
    case Person("valdis", relation, 105) => s"Oh hi Valdis, your relation is $relation, speed - 105 !!!"
    case Person(first, "father", speed) => s"found a father first name = $first, speed - $speed"
    case Person("valdis", relation, speed) => s"Oh hi Valdis, your relation is $relation, speed - $speed"
    case Person(name, relation, speed) => s"Oh hi generic Conspect.Person! your name is $name your relation is $relation, speed - $speed"
    //    case Dog("Dzulbars") => "found a dog named Dzulbars"
    //    case Car("Ford","Black", "T") => "a black model T Ford"
    //    //we can match on some specific property of of our class
    //    case Car("VW",color, model) => s"VW model: $model color: $color"
    // typed patterns
    case s: String => s"you gave me this string: $s" // переменную называем сами и она выглядит как любой стринг
    case i: Int => s"thanks for the int: $i"
    case f: Float => s"thanks for the float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"

    //    case d: Dog => s"dog: ${d.name}"
    case list: List[_] => s"thanks for the generic List: $list"
    case m: Map[_, _] => m.toString
    // the default wildcard pattern
    case default => s"Not found so default for ${default.toString}" //toString is available for pretty much everything
    //    case _ => "Unknown" //alternative when we do not want to show the input for default
  }

  println(echoWhatYouGaveMe(0))
  println(echoWhatYouGaveMe("hello"))
  println(echoWhatYouGaveMe("hello there!"))
  //
  println(echoWhatYouGaveMe(List(0, 1, 7)))
  println(echoWhatYouGaveMe(List(0, 1, 7, 10)))
  println(echoWhatYouGaveMe(List(1, 0, 3, 5, 1241431)))
  println(echoWhatYouGaveMe(Array(1, 0, 3, 5, 1241431)))
  println(echoWhatYouGaveMe(Array(0, 0, 3, 5, 1241431)))
  println(echoWhatYouGaveMe(Array("one", "two")))
  println(echoWhatYouGaveMe(Array(1.7, 2.7)))

  println(echoWhatYouGaveMe(Vector(1.7, 2.3, -35, 1.2)))
  println(echoWhatYouGaveMe(Vector(1, 2.3, -35, 1.2)))
  println(echoWhatYouGaveMe(Vector(1, 2, -35, 12)))

  println(echoWhatYouGaveMe((5, 10)))
  println(echoWhatYouGaveMe(("Valdis", 180, 23)))
  println(echoWhatYouGaveMe(("Valdis", 180, 23, "RTU")))
  println(echoWhatYouGaveMe(("Valdis", 180, 23, "RTU", "fifth element")))
  //so both double and int version of Vector was matched by that single case

  println(echoWhatYouGaveMe(Person("valdis", "myself", 105)))
  println(echoWhatYouGaveMe(Person("valdis", "myself", 125)))
  println(echoWhatYouGaveMe(Person("martins", "father", 125)))
  println(echoWhatYouGaveMe(Person("maija", "daughter", 125)))

  //  println(echoWhatYouGaveMe(Car()))
  //  println(echoWhatYouGaveMe(Car("VW")))
  //  println(echoWhatYouGaveMe(Car("VW", "yellow", "Touran")))
  //  println(echoWhatYouGaveMe(Car("Peugot"))) //this goes to default and gets pretty printed because Car is a case class
  //
  println(echoWhatYouGaveMe(Map((1, "one"), (2, "two"), (5, "Five"))))

  //
  //  //we can use pattern matcher as a poor man's or woman's regex - regular expression not quite but good in  many cases
  def stringMatcher(text: String): String = {
    text match {
      case s"name:$myName" => s"Cool your name is: $myName" //instead of if text.startsWith("name:")
      case s"lastName:$myName" => s"Oh your last name is: $myName"
      case s"$year-$h:$m:$ampm" => s"Ahh the year is $year and the hour is $h and minutes are $m and it is $ampm"
      case s"$year-$time" => s"Ahh the year is $year and time is $time"
      case _ => s"unrecognized string"
    }
  }

  println(stringMatcher("name:Valdis"))
  println(stringMatcher("lastName:Saules"))
  println(stringMatcher("justRandomString"))
  println(stringMatcher("2022-21:20:PM")) //of course this is not precise all it cares is -
  println(stringMatcher("2022-21.20.PM")) //of course this is not precise all it cares is -
}
