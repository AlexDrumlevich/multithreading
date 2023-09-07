package telran.threadsRace;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import telran.view.InputOutput;

public class Race {

	static AtomicReference<Racer> atomicReferenceWinner = new AtomicReference<>(); 
	
	private Racer[] racers;
	
	public Race(int countOfRasers, int distance, Consumer<String> printer) {
		racers = getRacers(countOfRasers, distance, printer);
		
	}
	
	private Racer[] getRacers(int countOfRasers, int distance, Consumer<String> printer) {
		return IntStream
			.range(1, countOfRasers + 1)
			.mapToObj(number -> new Racer(String.valueOf(number), distance, printer))
			.toArray(Racer[]::new);
			
		}

	public void startRace() {
		for (Racer racer : racers) {
			racer.start();
		}
		for (Racer racer : racers) {
			try {
				racer.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		atomicReferenceWinner.set(null);
	}
}
