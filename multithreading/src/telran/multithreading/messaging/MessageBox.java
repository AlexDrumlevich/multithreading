package telran.multithreading.messaging;

public class MessageBox {
	private String message;
	public synchronized void put(String message) throws InterruptedException {
		System.out.println("Senred entered in synchronized section");
		//wait until receiver get current message and set message = null
		//otherwise we change previous message even if receiver did not got it   
		while (this.message !=  null) {
			System.out.println("Senred is waiting");
			this.wait();
		}
		
			this.message = message;
			//notify about new message
			this.notify();
			Thread.currentThread().sleep(1000);
			System.out.println("Senred is leving synchronized section");
	}
	
	public synchronized String get() throws InterruptedException {
		System.out.println("Receiver entered in synchronized section N" + Thread.currentThread().threadId());
		//wait until putting next message 
		while(message == null) {
			this.wait();
		 	System.out.println("Receiver awoke N " + Thread.currentThread().threadId());
		}
		
		String res = message;
		message = null;
		//notify  about getting a message => producer can put new message into 
		//message property
		this.notify();
		System.out.println("Receiver is almost left N" + Thread.currentThread().threadId());
		return res;
	}
	public String take() {
		return message;
	}
}

