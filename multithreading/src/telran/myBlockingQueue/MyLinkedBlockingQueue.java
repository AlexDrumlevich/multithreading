package telran.myBlockingQueue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	private int limit;
	private LinkedList<E> list = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition additingCondition = lock.newCondition();
	private Condition removingCondition = lock.newCondition();

	public MyLinkedBlockingQueue(int limit) {
		this.limit = limit;
	}

	//ADD
	@Override
	public boolean add(E e) {
		try {
			lock.lock();
			if(e == null) {
				throw new NullPointerException();
			}
			if(list.size() == limit) {
				throw new IllegalStateException();
			}
			boolean isAdded = list.add(e);
			if(isAdded) {
				removingCondition.signal();
			}
			return isAdded;
		} finally {
			lock.unlock();
		}
	}


	@Override
	public boolean offer(E e) {

		try {
			lock.lock();
			if(e == null) {
				throw new NullPointerException();
			}
			boolean isAdded = limit == list.size() ? false : list.offer(e);
			if(isAdded) {
				removingCondition.signal();
			}
			return isAdded;
		} finally {
			lock.unlock();
		}
	}


	@Override
	public void put(E e) throws InterruptedException {
		try {
			lock.lock();
			if(e == null) {
				throw new NullPointerException();
			}
			while (limit == list.size()) {
				additingCondition.await();
			}
			if(list.add(e)) {
				removingCondition.signal();
			}
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			if(list.size() == limit) {
				additingCondition.await(timeout, unit);
			}
			return offer(e);
		} finally {
			lock.unlock();
		}
	}




	//REMOVE
	@Override
	public E remove() {

		try {
			lock.lock();
			if(list.isEmpty()) {
				throw new NoSuchElementException();
			}
			return list.removeFirst();
		} finally {
			additingCondition.signal();
			lock.unlock();
		}
	}

	@Override
	public E poll() {
		try {
			lock.lock();
			E removedElement = list.pollFirst();
			if(removedElement != null) {
				additingCondition.signal();
			}
			return removedElement;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {
		try {
			lock.lock();
			while (list.isEmpty()) {
				removingCondition.await();
			}
			E element = list.removeFirst();
			additingCondition.signal();
			return element;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			if(list.isEmpty()) {
				removingCondition.await(timeout, unit);
			}
			return poll();
		} finally {
			lock.unlock();
		}
	
	}

	//GET
	@Override
	public E element() {
		try {
			lock.lock();
			return list.element();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E peek() {
		try {
			lock.lock();
			return list.peekFirst();
		} finally {
			lock.unlock();
		}
	}
}
