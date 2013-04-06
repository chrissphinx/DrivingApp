/*
 *
 *
 *
 *
 *
 *******************************************************/
 
import collection.mutable.{ HashMap, ListBuffer }

class Map(file: String) {
/* CONSTRUCTOR *****************************************/
	// FIELDS
	val names = new ListBuffer[String]
	val graph = (new HashMap[Int, Vector[Tuple2[Int, Int]]]
							withDefaultValue Vector.empty)
	var cities = 0
	var directed = false

	// OPEN & READ FILE INTO FIELDS
	val lines = io.Source.fromFile(file + "MapData.txt").getLines.toList
	for(e <- lines) {
		// PARSE ENTRY
		if(e.split(" ").length == 3) {
			var Array(a, b, w) = e.split(" ").map(_.toInt)
			graph(a) = graph(a) :+ ((b, w))
		}
		// PARSE HEADER
		else if(e.split(" ").length == 2) {
			var Array(n, t) = e.split(" ")
			cities = n.toInt
			directed = t match { case "U" => false; case "D" => true; }
		}
		// PARSE NAMES
		else {
			names += e
		}
	}

/* METHODS *********************************************/
	def getCityName(num: Int): String = {
		names(num)
	}
	def getCityNumber(name: String): Int = {
		names.indexOf(name)
	}

	def getDistance(a: Int, b: Int): Option[Tuple2[Int, Int]] = {
		graph(a).find(p => p._1 == b)
	}
}