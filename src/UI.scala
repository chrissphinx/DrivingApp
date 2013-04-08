/*
 *	UI.scala														Colin MacCreery
 *
 *	UI class that just handles reading ?CityPairs.txt
 *	and logging messages to the Log.txt file.
 *
 *******************************************************/
 
class UI(file: String)
{
/* CONSTRUCTOR *****************************************/
	// PRIVATE FIELDS
	private val lines = io.Source.fromFile(file + "CityPairs.txt").getLines

/* METHODS *********************************************/
	def getNextPair: Option[Tuple2[String, String]] = {
		// CITY PAIR WRAPPED IN AN OPTION
		if(lines.hasNext) {
			lines.next.split(" ") match {
				// RETURN A TUPLE ...
				case Array(a, b) => Some(a, b)
			}
		} else {
			// OR NONE IF FILE ITERATOR IS FINISHED 
			return None
		}
	}

	def log(text: String): Unit = {
		// FART OUT SOME LOG
		val file = new java.io.FileWriter("log.txt", true)
		try {
			file.write(text)
		} finally file.close()
	}

}