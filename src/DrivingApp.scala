/*
 *
 *
 *
 *
 *
 *******************************************************/

object DrivingApp extends App {
	if(args.length == 0) println("ERROR: Arguement Not Specified!")
	else {
		var map = new Map(args(0))
		var ui = new UI(args(0));
		var path = new ShortestPath(map, ui);

		var pair = ui.getNextPair
		do {
			var (a, b) = pair.get
			if(map.getCityNumber(a) == -1 || map.getCityNumber(b) == -1) println("ERROR")
			else {
				path.findShortestPath(
					map.getCityNumber(a),
					map.getCityNumber(b)
				)
			}
			pair = ui.getNextPair
		} while(pair.nonEmpty)
	}
}