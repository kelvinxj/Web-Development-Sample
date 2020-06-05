import scala.collection.mutable.ListBuffer

object FirstApp{
  def main(args:Array[String ]): Unit={
    //Scala program don't need semicolon ";"
    //Print "hello, world"
    println("Hello, world!")

    //Define variables and basic data types: string, int, array,Array accessing
    //immutable variable, can't be change to other value
    val i:Int = 1 // you will get "reassignment to val" error when you type "i=2"
    val myStr = "This is a string"
    val myArray:Array[Int] = Array(1,2,3)
    println("The second element of array is: " + myArray(1))

    //Type convert:
    //Int to String
    val i2:Int = 98
    val myStr123 = i2.toString
    println(myStr123)

    //Int to String
    val numStr = "99"
    //using Java Integer.parseInt method
    println("98 + 99 = " + (i2 + Integer.parseInt(numStr)))
    //using scala StringLike.toInt method (StringLike.toInt call java Integer.parseInt()):
    println("98 + 99 = " + (i2 + "99".toInt))

    //mutable variable
    var i1:Int = 1
    val str = "This is a string"

    //If…else struct
    if(i == 1)
      println("i is 1")
    else if(i == 2)
      println("i is 2")
    else
      println("i is not 1")

    //Comparators: ==, <,>,<=,>=,!=
    println("1==1: " + 1==1)
    println("3<1: " + (3<1))
    println("3>1: " + (3>1))
    println("4<=4: " + (4<=4))
    println("5<=7: " + (5<=7))
    println("8!=9: " + (8!=9))

    //logical operator(and/or/not)
    val a = true
    val b = false
    println("True and True is: " + (a&&a))
    println("True or False is: " + (a||b))
    println("Not True is: " + !a)

    //For/looping/while/do while/foreach struct
    //to means: 1,2,3...10
    for(i <- 1 to 10)
      println("i is:" + i)
    //until means 0,1,2
    for(i <- 0 until 3)
      println(myArray(i))

    //Writing code in multiple lines
    val myval
      ="abc"
    val myIntVal
      :Int =
      8

    //Define function/methods
//    def funcName(parameter list): returnValueType = {
//      //function body
//    }
    //Method without return value
    def sayHello(name:String): Unit = println("Hello, " + name)
    sayHello("Bill")

    //a method without parameter and return a string
    def simpleMethod = "Hello"
    //calling a method without parameter shouldn't contain ()
    println(simpleMethod)

    //Method with return value
    //if method body contains only one line,
    //could be: def addOne(number:Int) = number + 1
    def addOne(number: Int): Int = {
      number+1
    }
    println("4 add one is:" + addOne(4))

    //Method, default arguments
    def showNameAgain(firstName:String = "", lastName:String = "") =
      println(firstName + "," + lastName)
    //if you pass one parameter, this will be passed to first
    showNameAgain("Jary")
    //if you want  to only pass lastName:
    showNameAgain(lastName="Tomlinson")
	
	//case match:
	def checkTuple(arg: Tuple2[String,String]):Int={
		arg._2 match{
			case "1"=>1
			case "0"=>1
			case _=>0
		}
	}
	println(checkTuple(("id1","p")))
	println(checkTuple(("id1","1")))

    //Get type of object
    println("i=" + i + ", type is " + i.getClass) //i=1, type is int
    println("str=" + str + ", type is " + str.getClass)//str=This is a string, type is class java.lang.String

    //String:
    //scala string is java.lang.String, so we can use java String function
    //Define a string
    val str1 = "This is a string"
    //Basic string methods:
    //Length
    println(str1 + ", length is:" + str1.length)
    //String match or not
    println(str1 + " match STRING? " + str1.matches("STRING"))
    //Find a substring
    println(str1.substring(3))
    //StartWith:
    println(str1 + " starts with This? " + str1.startsWith("This"))
    //endWith
    println(str1 + " ends with string? " + str1.endsWith("string"))
    //String replace
    println(str1.replace("string","not a string"))
    //String format
    println(String.format("Your name is %s", "Jerry"))

    //Regular expression:
    //Define a regular variable
    val pattern = "name".r

    //Basic regular methods:
    //Match or not
    print("123 matches \\\\d{3}? ")
    println("123".matches("\\d{3}")) //true

    //Get subGroups

    //Collection:
    //List:
    //Define a lis
    val list = List(1,2,3)

    //Access item of list
    println("First element is List(1,2,3) is: " + list(0)) //1

    //List is scala.collection.immutable.List, so when modify elements from list, you get a new list
    //Add an item to the end of list
    println(list + " add 1 to the end: " + (list :+ 1))

    //Add an item to beginning of list
    println(list + " add 1 to the beginning: " + (1 +: list))

    //Delete an item to list
    //Using ListBufer for deleting items in list
    val listTest = ListBuffer(1,2,3,4,5)
    println(listTest)//ListBuffer(1,2,3,4,5)
    listTest-=4
    println(listTest) //ListBuffer(1,2,3,5)

    //Check an item in list
    println(list.contains(1)) //true

    //Set:
    //Define a Set
    val set1 = Set(1,2,3,3)
    println(set1)

    //Access item Set

    //set is immutable
    //Add an item to Set
    //Delete an item to Set
    //Check an item in Set
    println("1 in set?" + set1(1))

    //Map:
    //Define a Map
    val map = Map(1->"a",2->"b")
    println(map(2))

    //Access item of Map
    println(map.get(1))

    //Add an item to Map
    val map1 = map + (3->"c")
    println("old map is:" + map)
    println("new map is:" + map1)

    //Delete an item to Map
    //Check an item in Map
    println(map.contains(1))

    //Tuple(if have)
    //Define a tuple
    val tp = (1,2)
    //Access item of tuple
    println(tp._1)

    //Deconstruct a tuple
    val (first,second) = tp
    println("first item in tuple:" + first)
    println("second item in tuple:" + second)

    println("item type is tuple is:" + first.getClass)

    //Class
    //Define class without body
    class Hello
    val t = new Hello
    //print address of t in memory, e.g.Main$Hello@7586beff
    println(t)

    //Class with a body, with mutable field(can be changed)
    class Hello1{
      var message :String = "This is a class"
    }

    val t1 = new Hello1()
    //print: This is a class
    println(t1.message)

    //promoting a parameter to a field
    class Hello2(val message:String){
    }

    val t2 = new Hello2("promoted field")
    //print: promoted field
    println(t2.message)


    //object, it is a singleton class
    object myObj{
      def message = "Hello"
      private val defaulValue = 1
    }

    //File I/O:
    //Create file obj
    //Readlines from file

    //Language specific features
    //case class: class only contains data
    case class Time(hours:Int=0,minutes:Int=0)

    val time = Time(9,0)
    val time1 = Time(9,0)

    //unapply function can retrieve the value inside an case class
    //timeOption is Tuple2 wrapped by "Some" type
    val timeOption = Time.unapply(time)
    //timeValue is scala.Tuple2$mcII$sp type
    val timeValue = timeOption.get

    //print: time case class unapplied is: Some((9,0))its value is: (9,0)
    println("time case class unapplied is: " + timeOption + "its value is: " + timeValue)

    //cass class compared by value, not by reference
    println(time == time1)//print true

    //Option:
    var op = Option("Jamie")
    println(op)
    println(op.getClass) //print class scala.Some

    //get value of option
    println(op.get) //print Jamie

    //get value of option, if not found ,return a default value
    println(op.getOrElse("Jane")) //print Jamie

    //option can be assigned with Some(option with value) or None(option without value)
    op = None
    println(op) //print None
    op = Some("Jamie")
    println(op) //print Jamie

    //Higher order functions take other functions as parameters
    //or return a function as a result.
    //This is possible because functions are first-class values in Scala.
    val salaries = Seq(20000, 70000, 40000)
    println("Salary is: " + salaries)
    //return function to multiple a value by 2
    val doubleSalary = (x: Int) => x * 2

    //Higher order function often used in map method of List
    val newSalaries = salaries.map(doubleSalary)
    //or using anonymous function
    val newSalaries1 = salaries.map(x=>x * 2)
    println("doubled salary is: " + newSalaries1)

    //map function for collection
    //map will return a new map by a function parameter
    val myVector = Vector(1,2,3,4,5)
    var myVector1 = myVector.map(number=>number+1)
    println("Old vector: " + myVector)
    println("New vector: " + myVector1)

    //flatMap method for collection
    val fruits = List("Apple", "Pear")
    println("Original map:" + fruits)
    println("Flat map:" + fruits.flatMap(word=>word))

    //filter function for collection
    //filter will return a new map, which contains item filtered by a function parameter
    val languages = List("python", "java")
    val python = languages.filter(lang=>lang.contains("py"))
    println("Language contains 'python': " + python)

    //foreach function will execute some method for each item in collection
    var listForeach = List(1,2)
    listForeach.foreach(println)

    //forall function(if all elements satisfy condition, return true)
    var result = listForeach.forall(number=>number>100)
    println(result)

    //reduce method for collection
    val numbers = 1 to 10
    println("The numbers:" + numbers)
    println("Add them together:" + numbers.reduce(_+_))
    println("Multiple them together:" + numbers.product)

    //exist and find(find will only find the first value which matches the condition)
    println("Is there a number which is more than 100?" + numbers.exists(value=>value>100))
    println("Find first value which is less than 7: " + numbers.find(number=>number<=7).get)

    //takeWhile(take the item to a new list, until find item which do not match condition)
    //and dropWhile(drop item from list, unit find item which do not matched items)
    //will return an empty list
    println(numbers.takeWhile(num=>num>2))
    //will still return list contains 1 to 10
    println(numbers.dropWhile(num=>num>2))

    //Companion object:
    //an object and a class are in the same source file
    object TheObj{
      private val defaultMessage:String ="Hello: message from object private member"
    }

    //class TheObj can access private member of Hello object
    class TheObj(message:String=TheObj.defaultMessage){
      println("Message:" + message)
    }

    //will print defaultMessage in TheObj
    val theObj = new TheObj();

    //For and yield
    val myNums = 1 to 3
    val myVec = for(i <- myNums if i%2 == 1) yield i
    println("Vector can't divided by : " + myVec)

    //i = 1,3; j: from 1 to 1; 1 to 3
    val myVec1 = for{
      i <- myNums if i%2 == 1
      j <- 1 to i
    }yield i*j
    println(myVec1)
    }
}
