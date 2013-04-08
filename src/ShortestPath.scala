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
		println(map.getCityName(start) + " to " + map.getCityName(end))
		println("** SEARCH ***********")
		search(start, end)
		println("__ INCLUDED __________")
		included foreach println
		println("__ " + start + " to " + end + " DISTANCES __________")
		distance foreach println
		println("__ PATH __________")
		path foreach println
		println("__ END __________")
		reportAnswer(end)
		println("\n")
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
		while(!included(end)) {
			val target = (distance.zipWithIndex filter (p => included(p._2) != true)).min._2
			println(map.getCityName(target) + " at " + distance(target))
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
	}

	private def reportAnswer(end: Int) = {
		println("TOTAL DISTANCE: " + distance(end))
		val builder = new VectorBuilder[Int]
		builder += end
		var following = path(end)
		while(following != -1) {
			builder += following
			following = path(following)
		}
		val finalPath = builder.result.reverse
		finalPath foreach println
	}
}