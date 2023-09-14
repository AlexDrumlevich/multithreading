package telran.deadLock;

public class DeadLock {

	static Object mutex1 = new Object();
	static Object mutex2 = new Object();
	
	
	public static void main(String[] args) {
		
	  Thread thread1 = new Thread() {
			@Override
			public void run() {
				System.out.println(func1(8));					
			}
		};
		
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				System.out.println(func2(8));				
			}
		};
		
		
		thread1.start();
		thread2.start();
		
		System.out.println("threads were started");
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main completed execution");
		
		
		
	}
	
	
	static public int func1(int x) {
		synchronized (mutex1) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("func 1 is going to call func2");
			x += func2(5);
			System.out.println("func 1 is going to return");
			return x;
		}
	}
	
	static public int func2(int x) {
		synchronized (mutex2) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("func 2 is going to call func1");
			x += func1(5);
			System.out.println("func 2 is going to return");
			return x;
		}
	}
	
	
	
}
