package interruptPrinters;

public class Printer extends Thread {
	
	String threadName;
	int nPortions;
	int nPortion;
	Printer nextPrinter;
	
	public Printer(String threadName, int nPortion, int nPortions) {
		this.threadName = threadName;
		this.nPortion = nPortion;
		this.nPortions = nPortions;
		
	}
	
	@Override
	public void run() {
		int countDonePortions = 0;
		while (countDonePortions < nPortions) {
			try {
				join();
			} catch (InterruptedException e) {
				for (int j = 0; j < nPortion; j++) {
					System.out.print(threadName);
				}
				System.out.println();
				nextPrinter.interrupt();
				countDonePortions ++;
			}
			
		}
	}
}
