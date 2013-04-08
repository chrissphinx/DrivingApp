/*
 *
 *
 *
 *
 *
 *******************************************************/

object DrivingApp extends App {
	// FAIL SILENTLY LIKE A BOSS
	if(args.length == 0) {}

	else {
		var ui = new UI(args(0))
		var map = new Map(args(0))
		ui.log("STATUS:  OK, " + args(0) + " MapData loaded - "
					 + "contains " + map.cities + " cities\n\n")
		var path = new ShortestPath(map, ui)

		var pair = ui.getNextPair
		do {
			ui.log("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %\n")
			val (a, b) = pair.get
			val aNum = map.getCityNumber(a) match { case -1 => "X"; case n => n }
			val bNum = map.getCityNumber(b) match { case -1 => "X"; case n => n }
			ui.log(a + " (" + aNum + ") TO " + b + " (" + bNum + ")\n")
			if(map.getCityNumber(a) == -1 || map.getCityNumber(b) == -1) {
				ui.log("ERROR – one of these cities is not part of this map\n\n")
			} else {
				path.findShortestPath(map.getCityNumber(a), map.getCityNumber(b))
			}
			pair = ui.getNextPair
		} while(pair.nonEmpty)
		ui.log("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %\n\n\n")
	}
}