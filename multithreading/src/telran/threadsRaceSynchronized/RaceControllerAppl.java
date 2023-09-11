package telran.threadsRaceSynchronized;

import telran.view.ConsoleInputOutput;
import telran.view.Menu;

public class RaceControllerAppl {

	public static void main(String[] args) {
		
		Menu menu = RaceMenu.getRaceMenu();
		menu.perform(new ConsoleInputOutput());

	}

}
