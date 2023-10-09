package telran.multithreading.messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBox {
	
	private BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
	
	public void put(String message) throws InterruptedException {
		queue.put(message);
	
	}
	
	public String get() throws InterruptedException {
		return queue.take();
	}
	
	public String take() {
		return queue.poll();
	}
}

