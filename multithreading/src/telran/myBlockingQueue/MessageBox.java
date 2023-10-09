package telran.myBlockingQueue;

import java.util.concurrent.*;

public class MessageBox {
	
	private MyBlockingQueue<String> queue = new MyLinkedBlockingQueue<>(1);
	
	public void put(String message) throws InterruptedException {
		queue.put(message);	
	}
	public  String get() throws InterruptedException {
		return queue.take();
	}
	public String take() {
		return queue.poll();
	}
}
