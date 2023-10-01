package telran.multithreading.messaging;

public class MessageBox {
	private String message;
	public synchronized void put(String message) throws InterruptedException {
		//wait until receiver get current message and set message = null
		//otherwise we change previous message even if receiver did not got it   
		while (this.message !=  null) {
			this.wait();
		}
		
			this.message = message;
			//notify about new message
			this.notify();

	}
	
	public synchronized String get() throws InterruptedException {
		//wait until putting next message 
		while(message == null) {
			this.wait();
		}
		
		String res = message;
		message = null;
		//notify  about getting a message => producer can put new message into 
		//message property
		this.notifyAll();
		return res;
	}
	public String take() {
		return message;
	}
}

