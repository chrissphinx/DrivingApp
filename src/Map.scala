/*
 *	Map.scala														Colin MacCreery
 *
 *	Map class to parse and store data from the
 *	?MapData.txt files. Number of cities & type
 *	of graph are public constants.
 *
 *	I didn't use a List with a head pointer array,
 *	instead utilizing a HashMap of Vectors with Tuples.
 *	Each map key is a "head pointer" (start) and the values
 *	for each key are a Vector of (destination, weight)
 *	pairs.
 *
 *******************************************************/
 
import collection.mutable.{ HashMap, ListBuffer }

class Map(file: String)
{
/* CONSTRUCTOR *****************************************/
	// PRIVATE FIELDS
	private val lines = io.Source.fromFile(file + "MapData.txt").getLines
	private val names = new ListBuffer[String]
	private val graph = (new HashMap[Int, Vector[Tuple2[Int, Int]]]
											withDefaultValue Vector.empty)
	private val Array(n, t) = lines.next.split(" ")

	// PUBLIC FIELDS (HEADER)
	val cities = n.toInt
	val directed = t match { case "U" => false; case "D" => true; }

	// PARSE FILE
	for(e <- lines.toList) {
		// PARSE EDGE ENTRY
		if(e.split(" ").length == 3) {
			// A CITY, B CITY AND EDGE WEIGHT
			var Array(a, b, w) = e.split(" ").map(_.toInt)
			// APPEND A TUPLE TO THE KEY'S VECTOR
			graph(a) = graph(a) :+ ((b, w))
			// DO IT AGAIN FOR UNDIRECTED GRAPHS
			if(!directed) graph(b) = graph(b) :+ ((a, w))
		}
		// PARSE NAMES
		else {
			names += e
		}
	}

/* METHODS *********************************************/
	def getCityName(num: Int): String = {
		// PROBABLY RETURNS THE CITY'S NAME
		if(num != -1) {
			names(num)
		} else {
			"-1"
		}
	}
	
	def getCityNumber(name: String): Int = {
		// RETURNS THE CITY'S NUMBER, I THINK
		names.indexOf(name)
	}

	def getDistance(a: Int, b: Int): Int = {
		// FIND THE DISTANCE BETWEEN TWO CITIES
		if(a == b) return 0;
		graph(a).find(p => p._1 == b) match {
			// RETURN THE EDGE WEIGHT ...
			case Some(r) => r._2
			// ... OR "INFINITY" IF NO CONNECTION IS FOUND
			case None => Integer.MAX_VALUE
		}
	}

}