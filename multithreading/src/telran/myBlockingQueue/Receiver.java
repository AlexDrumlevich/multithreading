package telran.myBlockingQueue;


public class Receiver extends Thread {
	private MessageBox messageBox;
	public Receiver(MessageBox messageBox) {
		//setDaemon(true); //FIXME
		this.messageBox = messageBox;
	}
	int a = 0;
	@Override
	public void run() {
		String message;
		try {
			while(true) {
				message = messageBox.get();
				messageProcessing(message);
			}
		} catch (InterruptedException e) {
			while((message = messageBox.take()) != null ) {
				messageProcessing(message);
			}
		}

	}
	
	private void messageProcessing(String message) {
		System.out.printf("Thread %d has got message: %s\n", threadId(), message);
	}
}
