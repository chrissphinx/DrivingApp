/*
 *
 *
 *
 *
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

	// PUBLIC FIELDS
	val cities = n.toInt
	val directed = t match { case "U" => false; case "D" => true; }

	// PARSE FILE
	for(e <- lines.toList) {
		// PARSE EDGE ENTRY
		if(e.split(" ").length == 3) {
			var Array(a, b, w) = e.split(" ").map(_.toInt)
			graph(a) = graph(a) :+ ((b, w))
			if(!directed) graph(b) = graph(b) :+ ((a, w))
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

	def getDistance(a: Int, b: Int): Int = {
		if(a == b) return 0;
		graph(a).find(p => p._1 == b) match {
			case Some(r) => r._2
			case None => Integer.MAX_VALUE
		}
	}
}