object Hello{
	def main(args: Array[String]): Unit ={	
		//Print "hello, world"	
		println("Hello, world!")
		
		//Define variables: string, int, array,Array accessing
		//immutable variable, can't be change to other value
		val i = 1
		//mutable variable
		var i1:Int = 1
		val str = "This is a string"
		val myArray:Array[Int] = Array(1,2,3)
		println("The second element of array is: " + myArray(1))
		
		//If…else struct
		if(i == 1)
			println("i is 1")
		else
			println("i is not 1")
			
		//For/looping/while/do while/foreach struct
		//to means: 1,2,3...10
		for(i <- 1 to 10)
			println("i is:" + i)
		//until means 0,1,2
		for(i <- 0 until 3)
			println(myArray(i))
		
		//Writing code in multiple lines
		
		//Comparators: ==, <,>,<=,>=,!=
		
		//Define function/methods
		//Method without return value
		def sayHello(name:String):Unit = println("Hello, " + name)
		sayHello("Bill")
		//a method without parameter and return a string
		def simpleMethod = "Hello"
		//calling a method without parameter shouldn't contain ()
		println(simpleMethod)
		
		//Method with return value
		//if method body contains only one line, 
		//could be: def addOne(number:Int) = number + 1
		def addOne(number: Int) = {
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
		
		//Type convert:
		//Int to string
		println(i.toString())
		//String to int
		var a = 1
		println("1+2=" + (a+"2".toInt))
			
		//Get type of object
		println("i=" + i + ", type is " + i.getClass)
		println("str=" + str + ", type is " + str.getClass)
		
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
		println("123".matches("\\d{3}"))
		//Get subGroups
		
		//Collection:
		//List:
		//Define a list
		val list = List(1,2,3)
		
		//Access item of list
		println(list(0))
		
		//Add an item to the end of list
		println(list + " add 1 to the end: " + (list :+ 1))
		
		//Add an item to beginning of list
		println(list + " add 1 to the beginning: " + (1 +: list))
		
		//Delete an item to list
		//Check an item in list
		println(list.contains(1))
		
		//Set:
		//Define a Set
		val set1 = Set(1,2,3,3)
		println(set1)
		
		//Access item Set
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
		//Option:
		var op = Option("Jamie")
		println(op)
		println(op.getClass)
		
		//get value of option
		println(op.get)

		//get value of option, if not found ,return a default value
		println(op.getOrElse("Jane"))
		
		//option can be assigned with Some(option with value) or None(option without value)
		op = None
		println(op)
		op = Some("Jamie")
		println(op)
	}
}