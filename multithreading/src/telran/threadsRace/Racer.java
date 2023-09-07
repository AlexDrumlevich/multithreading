package telran.threadsRace;

import java.util.Random;
import java.util.function.Consumer;

public class Racer extends Thread {

	private static final int MIN_TIME_SLEEP = 2;
	private static final int MAX_TIME_SLEEP = 5;
	private String name;
	private int distance;
	private Consumer<String> printer;


	public Racer(String name, int distance, Consumer<String> printer) {
		this.name = name;
		this.distance = distance;
		this.printer = printer;
	}

	@Override
	public void run() {

		for(int i = 0; i < distance && Race.atomicReferenceWinner.get() == null; i++) {
			printer.accept(name);
			try {
				sleep(new Random().nextInt(MIN_TIME_SLEEP, MAX_TIME_SLEEP));
			} catch (InterruptedException e) {

			}
		} 
		Race.atomicReferenceWinner.compareAndSet(null, this);
		if(Race.atomicReferenceWinner.get() == this) {
			printer.accept(String.format("Congratulations to thread %s", name));
		}
	}
}