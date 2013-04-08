/*
 *	DrivingApp.scala										Colin MacCreery
 *
 *	Main class handling the creation of the Map,
 *	ShortestPath & UI objects. Asks UI object for
 *	entries until there are none left.
 *
 *******************************************************/

object DrivingApp extends App
{
	// FAIL SILENTLY LIKE A BOSS
	if(args.length == 0) {}

	else {
		// MAKE SOME OBJECTS
		val ui = new UI(args(0))
		val map = new Map(args(0))
		ui.log("STATUS:  OK, " + args(0) + " MapData loaded - "
					 + "contains " + map.cities + " cities\n\n")
		val path = new ShortestPath(map, ui)

		// LOOP UNTIL UI INDICATES DONE
		var pair = ui.getNextPair
		do {
			ui.log("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %\n")
			// GET TUPLE FROM OPTION
			val (a, b) = pair.get
			// GET CITIES' NUMBERS, USE "X" IF CITY DOESN'T EXIST
			val aNum = map.getCityNumber(a) match { case -1 => "X"; case n => n }
			val bNum = map.getCityNumber(b) match { case -1 => "X"; case n => n }
			ui.log(a + " (" + aNum + ") TO " + b + " (" + bNum + ")\n")
			// PRINT AN ERROR ...
			if(map.getCityNumber(a) == -1 || map.getCityNumber(b) == -1) {
				ui.log("ERROR – one of these cities is not part of this map\n\n")
			// ... OR CALCULATE SHORTEST PATH
			} else {
				path.findShortestPath(map.getCityNumber(a), map.getCityNumber(b))
			}
			// ASK UI FOR ANOTHER PAIR, IF OPTION IS EMPTY ...
			pair = ui.getNextPair
		} while(pair.nonEmpty)
		// ... FINISH
		ui.log("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %\n\n\n")
	}

}