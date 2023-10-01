package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;
	private boolean haveToStop = false;
	public Receiver(MessageBox messageBox) {
		//setDaemon(true); //FIXME
		this.messageBox = messageBox;
	}
	int a = 0;
	@Override
	public void run() {
		while(!haveToStop) {//FIXME
			try {
				String message = messageBox.get();
				System.out.printf("Thread %d has got message: %s\n", threadId(), message);
			} catch (InterruptedException e) {
				haveToStop = true;
			}
	
		}
	}
}
