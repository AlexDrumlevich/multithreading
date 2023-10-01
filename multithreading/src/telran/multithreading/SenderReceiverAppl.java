package telran.multithreading;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.MessageBox;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 4;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		
		MessageBox messageBox = new MessageBox();
		startReceivers(messageBox);
		
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		
		Thread.sleep(10000);
	}

	

	private static void startReceivers(MessageBox messageBox) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			new Receiver(messageBox).start();
		}
		
	}

}


	