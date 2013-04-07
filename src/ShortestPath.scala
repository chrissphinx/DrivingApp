/*
 *
 *
 *
 *
 *
 *******************************************************/
 
class ShortestPath(map: Map, ui: UI)
{
	private val N = map.cities
	private val included = Array.ofDim[Boolean](N)
	private val distance = Array.ofDim[Int](N)
	private val path = Array.ofDim[Int](N)

	def findShortestPath(start: Int, end: Int) = {
		initialize(start)
		println("__ INCLUDED __________")
		included foreach println
		println("__ " + start + " to " + end + " DISTANCES __________")
		distance foreach println
		println("__ PATH __________")
		path foreach println
	}

	private def initialize(start: Int) = {
		for(i <- 0 to N - 1) {
			i match {
				case 0 => included(i) = true
				case _ => included(i) = false
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

	}

	private def reportAnswer = {

	}
}