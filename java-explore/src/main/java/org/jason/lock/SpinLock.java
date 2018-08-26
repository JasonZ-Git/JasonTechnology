package org.jason.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

	private AtomicReference<Thread> signal = new AtomicReference<>();

	public void lock() {
		while (!signal.compareAndSet(null, Thread.currentThread()))
			;
	}

	public void unlock() {
		signal.compareAndSet(Thread.currentThread(), null);
	}
}
