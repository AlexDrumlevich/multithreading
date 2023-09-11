package telran.threadsRaceSynchronized;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;


public class Race {
	
	private Racer[] racers;
	private Instant startRaceTime;
	private LinkedHashMap<Racer, Long> results = new LinkedHashMap<>(); 
	private Consumer<String> printer;
	
	public Race(int countOfRasers, int distance, Consumer<String> printer) {
		racers = getRacers(countOfRasers, distance);
		this.printer = printer;
	}
	
	private Racer[] getRacers(int countOfRasers, int distance) {
		return IntStream
			.range(1, countOfRasers + 1)
			.mapToObj(number -> new Racer(String.valueOf(number), this, distance))
			.toArray(Racer[]::new);
			
		}

	public void startRace() {
		
		startRaceTime = Instant.now();
		
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
		
		printRaceResults();
		
		results = new LinkedHashMap<>();
	}
	
	public synchronized void racerFinish(Racer racer) {
		results.put(racer, ChronoUnit.MICROS.between(startRaceTime, Instant.now()));
	}

	private void printRaceResults() {
		int offset = 10;
		printer.accept(String.format("place%1$sracer%1$stime (microsec)", " ".repeat(offset)));
		int[] place = new int[]{1};
		results.forEach((racer, time) -> {
			printer.accept(String.format("%d%s%s%s%d", place[0]++, " ".repeat(offset + 3), racer.getRacerName(), " ".repeat(offset), time));
		});
	}
	
	
	
}

