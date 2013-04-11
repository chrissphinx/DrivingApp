/*
 *	ShortestPath.scala									Colin MacCreery
 *
 *	Dijkstra's shortest path algorithm. Uses three
 *	parallel working arrays to calculate the optimal
 *	path from start to end.
 *
 *******************************************************/

import collection.immutable.VectorBuilder

class ShortestPath(map: Map, ui: UI)
{
/* CONSTRUCTOR *****************************************/
	// PRIVATE FIELDS
	private val N = map.cities
	private val included = Array.ofDim[Boolean](N)
	private val distance = Array.ofDim[Int](N)
	private val path = Array.ofDim[Int](N)

/* PUBLIC METHODS **************************************/
	def findShortestPath(start: Int, end: Int): Unit = {
		// CALLS THE SHOTS
		initialize(start)
		search(start, end)
		reportAnswer(end)
	}

/* PRIVATE METHODS *************************************/
	private def initialize(start: Int): Unit = {
		// INITIALIZE THE 3 ARRAYS
		for(i <- 0 to N - 1) {
			// INCLUDED ARRAY: ALL FALSE EXCEPT FOR START NODE
			if(i == start) {
				included(i) = true
			} else {
				included(i) = false
			}
			// DISTANCE ARRAY: EDGE WEIGHTS FOR START TO NODE
			distance(i) = map.getDistance(start, i)
			// PATH ARRAY: ALL -1 EXCEPT WHEN DISTANCE
			// OF NODE  HAS A VALID EDGE WEIGHT
			if(distance(i) < Integer.MAX_VALUE && distance(i) > 0) {
				path(i) = start
			} else {
				path(i) = -1
			}
		}
	}

	private def search(start: Int, end: Int): Unit = {
		// DIJKSTRA'S ALGORITHM
		ui.log("\nTRACE OF TARGETS:  " + map.getCityName(start) + " ")
		// COUNT TARGETS
		var targets = 1
		// WHILE DESTINATION IS NOT YET INCLUDED
		while(!included(end)) {
			// OUT OF ALL NODES NOT YET INCLUDED, CHOOSE NODE
			// WITH SMALLEST DISTANCE VALUE
			val target = (distance.zipWithIndex
										filter (p => included(p._2) != true)).min._2
			ui.log(map.getCityName(target) + " ")
			targets += 1
			// TARGET NOW BECOMES INCLUDED
			included(target) = true
			for(i <- 0 to N - 1) {
				// RELAX ALL NECESSARY EDGES
				if(included(i) == false) {
					val edge = map.getDistance(target, i)
					// (STOP INTEGER OVERFLOW)
					val legal = distance(target) < Integer.MAX_VALUE
					// IF EDGE FROM TARGET TO NODE IS A VALID EDGE WEIGHT
					if(edge < Integer.MAX_VALUE && edge > 0 && legal) {
						// AND IT'S AN IMPROVEMENT
						if((distance(target) + edge) < distance(i)) {
							// RELAX EDGE AND SET PREDECESSOR OF NODE TO TARGET
							distance(i) = distance(target) + edge
							path(i) = target
				} } }
			}
		}
		ui.log("[" + targets + " targets]\n\n")
	}

	private def reportAnswer(end: Int): Unit = {
		// LOG THE DISTANCE AND FINAL PATH
		if(distance(end) == Integer.MAX_VALUE) {
			// PRINT AN ERRORR ...
			ui.log("ERROR – no route to destination exists\n\n")
		} else {
			// ... OR THE SHORTEST DISTANCE FROM START TO END
			ui.log("DISTANCE:  " + distance(end) + "\n")
			// CREATE A VECTOR BUILDER FOR FOLLOWING VALUES IN PATH
			val builder = new VectorBuilder[Int]
			// ADD THE DESTINATION
			builder += end
			// NOW FOLLOW THE VALUES UNTIL -1 ...
			var following = path(end)
			while(following != -1) {
				// ... ADDING IT TO THE BUILDER
				builder += following
				following = path(following)
			}
			// GET THE VECTOR FROM THE BUILDER AND FLIP IT
			val finalPath = builder.result.reverse
			// FINALLY PRINT THE PATH PRETTILY (IN A PRETTY MANNER)
			ui.log("SHORTEST ROUTE:  ")
			for(i <- 0 to finalPath.length - 1) {
				ui.log(map.getCityName(finalPath(i)))
				if(i != finalPath.length - 1) ui.log(" > ")
			}
			ui.log("\n\n")
		}
	}

}