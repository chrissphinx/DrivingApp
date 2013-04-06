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
		var path = new ShortestPath();
		var ui = new UI();
	}
}