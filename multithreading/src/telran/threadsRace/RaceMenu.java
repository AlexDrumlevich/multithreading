package telran.threadsRace;

import java.io.IOError;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;


public class RaceMenu {

	
	
	private static final int MIN_THREADS = 3;
	private static final int MAX_THREADS = 10;
	private static final int MIN_DISTANCE = 3;
	private static final int MAX_DISTANCE = 100;
	
	

	public static Menu getRaceMenu() {
		return new Menu("Race threads", getItems());
	}
	
	private static Item[] getItems() {
		return new Item[] {
			Item.of("Start game", io -> {
				int countOfRacers = io.readInt("Enter count of threads take part into race", "Entered wrong values", MIN_THREADS, MAX_THREADS);
				int distance = io.readInt("Enter distance of threads race", "Entered wrong values", MIN_DISTANCE, MAX_DISTANCE);
				new Race(countOfRacers, distance, io::writeLine).startRace();
			}),
			Item.ofExit()
		};
	}
}
