package telran.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.MessageBox;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 4;
	private static final int N_RECEIVERS = 10;
	private static List<Receiver> receivers = new ArrayList<>();
	private static MessageBox messageBox;
	
	public static void main(String[] args) throws InterruptedException {
		
		messageBox = new MessageBox();
		startReceivers(messageBox);
		
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		
		sender.join();
		stopReceivers();
		
	}

	

	private static void startReceivers(MessageBox messageBox) {
	
		IntStream.range(0, N_RECEIVERS).forEach(i -> {
			Receiver receiver = new Receiver(messageBox);
			receivers.add(receiver);
			receiver.start();
		});
		
	}
	
	private static void stopReceivers() {
		while (messageBox.take() != null) {}
		receivers.forEach(receiver -> receiver.interrupt());
	}

}


	