package interruptPrinters;


public class PrinterApp {
	final private static int N_PRINTERS = 6;
	final private static int N_NUMBERS = 100;
	final private static int N_PORTION = 10;
	
	
	public static void main(String[] args) {
		Printer[] printers = new Printer[N_PRINTERS];
		printers[0] = new Printer(String.valueOf(1), N_PORTION, N_NUMBERS / N_PORTION);
		for(int i = 1; i < printers.length; i++){
			printers[i] = new Printer(String.valueOf(i + 1), N_PORTION, N_NUMBERS / N_PORTION);
			printers[i-1].nextPrinter = printers[i];
			printers[i-1].start();
		}
		printers[printers.length-1].nextPrinter = printers[0];
		printers[printers.length-1].start();
		printers[0].interrupt();
	}
}