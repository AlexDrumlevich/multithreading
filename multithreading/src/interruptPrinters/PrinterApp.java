package interruptPrinters;

import java.util.stream.IntStream;

public class PrinterApp {
	final private static int N_PRINTERS = 6;
	final private static int N_NUMBERS = 100;
	final private static int N_PORTION = 10;
	
	
	public static void main(String[] args) {
		Printer[] printers;
		
			printers = IntStream
					.range(1, N_PRINTERS + 1)
					.mapToObj(n -> new Printer(String.valueOf(n), N_PORTION, N_NUMBERS / N_PORTION))
					.peek(printer -> {
						printer.start();
					})
					.toArray(Printer[]::new);
	
	for(int i = 0; i < printers.length; i++) {
		printers[i].nextPrinter = i == printers.length - 1 ? printers[0] : printers[i + 1];
	}
	printers[0].interrupt();
	}
	
}
