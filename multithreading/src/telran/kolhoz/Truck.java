package telran.kolhoz;

public class Truck extends Thread {
	
	private int load;
	private static long elevator1;
	private static Object mutex1 = new Object();
	private static Object mutex2 = new Object();
	public static long getElevator1() {
		return elevator1;
	}
	public static long getElevator2() {
		return elevator2;
	}
	private static long elevator2;
	private int nLoads;
	public Truck(int load, int nLoads) {
		this.load = load;
		this.nLoads = nLoads;
	}
	@Override
	public void run() {
		
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < nLoads; i++) {
					loadElevator1(load);
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < nLoads; i++) {
					loadElevator2(load);
				}
			}
		}.start();
		
		for (int i = 0; i < nLoads; i++) {
			loadElevator1(load);
			loadElevator2(load);
		}
		
		
	}
	
	//ATOMIC
	/*
	 private static AtomicLong elevator1=new AtomicLong(0);
	 private static AtomicLong elevator2=new AtomicLong(0);
	 
	 private static  void loadElevator2(int load) {
			elevator2.addAndGet(load);
		
	}
	private static  void loadElevator1(int load) {
		elevator1.addAndGet(load);
		
	}
	 */
	
	static private  void loadElevator1(int load) {
		synchronized (mutex1) {
			elevator1 += load;
		}
		
	}
	static private  void loadElevator2(int load) {
		synchronized (mutex2) {
			elevator2 += load;
		}
		
	}
//	private static synchronized void loadElevator1(int load) {
//		elevator1 += load;
//		
//	}
}
