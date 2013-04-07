/*
 *
 *
 *
 *
 *
 *******************************************************/
 
class UI(file: String) {
	private val lines = io.Source.fromFile(file + "CityPairs.txt").getLines

	def getNextPair: Option[Tuple2[String, String]] = {
		if(lines.hasNext) {
			lines.next.split(" ") match {
				case Array(a, b) => Some(a, b)
			}
		} else {
			return None
		}
	}
}