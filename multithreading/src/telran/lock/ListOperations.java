package telran.lock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class ListOperations extends Thread {
     Monitor monitor;
     ArrayList<Integer> list;
     ExpData expData;
     Random gen = new Random();
	public ListOperations(Monitor monitor, ArrayList<Integer> list, ExpData expData) {
		super();
		this.monitor = monitor;
		this.list = list;
		this.expData = expData;
	}
     @Override
     public void run() {
    	 int nRuns = expData.nOperations();
    	 for (int i = 0; i < nRuns; i++) {
			if (gen.nextInt(1, 100) <= expData.updateProb()) {
				updateList();
			} else {
				readList();
			} 
		}
     }
	private void readList() {
		try {tryLock(monitor.read());
			for (int i = 0; i < 10000; i++) {
				list.get(list.size() - 1);
			}
		} finally {
			monitor.read().unlock();
		}
		
	}
	private void updateList() {
		try{
			tryLock(monitor.write());
			int nUpdates = 10;
			for (int i = 0; i < nUpdates; i++) {
				list.remove(0);

			}
			for (int i = 0; i < nUpdates; i++) {
				list.add(100);

			}
		} finally {
			monitor.write().unlock();
		}
		
	}
	private void tryLock(Lock lock) {
		while(!lock.tryLock()) {
			monitor.coount().incrementAndGet();
		}
	}
}
