/*
 *
 *
 *
 *
 *
 *******************************************************/

import collection.immutable.VectorBuilder

class ShortestPath(map: Map, ui: UI)
{
	private val N = map.cities
	private val included = Array.ofDim[Boolean](N)
	private val distance = Array.ofDim[Int](N)
	private val path = Array.ofDim[Int](N)

	def findShortestPath(start: Int, end: Int) = {
		initialize(start)
		search(start, end)
		reportAnswer(end)
	}

	private def initialize(start: Int) = {
		for(i <- 0 to N - 1) {
			if(i == start) {
				included(i) = true
			} else {
				included(i) = false
			}
			distance(i) = map.getDistance(start, i)
			if(distance(i) < Integer.MAX_VALUE && distance(i) > 0) {
				path(i) = start
			} else {
				path(i) = -1
			}
		}
	}

	private def search(start: Int, end: Int) = {
		ui.log("\nTRACE OF TARGETS:  ")
		var numTargets = 0
		while(!included(end)) {
			val target = (distance.zipWithIndex filter (p => included(p._2) != true)).min._2
			ui.log(map.getCityName(target) + " ")
			numTargets += 1
			included(target) = true
			for(i <- 0 to N - 1) {
				if(included(i) == false) {
					val edge = map.getDistance(target, i)
					if(edge < Integer.MAX_VALUE && edge > 0) {
						if(distance(target) + edge < distance(i)) {
							distance(i) = distance(target) + edge
							path(i) = target
						}
					}
				}
			}
		}
		ui.log("[" + numTargets + " targets]\n\n")
	}

	private def reportAnswer(end: Int) = {
		if(distance(end) < 0) {
			ui.log("ERROR – no route to destination exists\n\n")
		} else {
			ui.log("DISTANCE:  " + distance(end) + "\n")
			val builder = new VectorBuilder[Int]
			builder += end
			var following = path(end)
			while(following != -1) {
				builder += following
				following = path(following)
			}
			val finalPath = builder.result.reverse
			ui.log("SHORTEST ROUTE:  ")
			for(i <- 0 to finalPath.length - 1) {
				ui.log(map.getCityName(finalPath(i)))
				if(i != finalPath.length - 1) ui.log(" > ")
			}
			ui.log("\n\n")
		}
	}
}