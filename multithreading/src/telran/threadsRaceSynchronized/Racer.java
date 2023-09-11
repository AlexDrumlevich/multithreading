package telran.threadsRaceSynchronized;

import java.util.Random;
import java.util.function.Consumer;

public class Racer extends Thread {

	private static final int MIN_TIME_SLEEP = 2;
	private static final int MAX_TIME_SLEEP = 5;
	private String racerName;
	private Race race;
	private int distance;



	public Racer(String name, Race race, int distance) {
		this.racerName = name;
		this.race = race;
		this.distance = distance;
	}

	public String getRacerName() {
		return racerName;
	}
	
	@Override
	public void run() {

		for(int i = 0; i < distance; i++) {
			try {
				sleep(new Random().nextInt(MIN_TIME_SLEEP, MAX_TIME_SLEEP));
			} catch (InterruptedException e) {
			}
		} 
		race.racerFinish(this);
	}
}