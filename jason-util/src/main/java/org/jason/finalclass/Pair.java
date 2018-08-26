/**
 * 
 */
package org.jason.finalclass;

/**
 * 表示 两个 对象
 * 
 * @author Jason.zhang
 * 
 */
public class Pair<T, U> {
	private final T first;
	private final U second;

	public Pair(T t, U u) {
		this.first = t;
		this.second = u;
	}

	public T getFirst() {
		return this.first;
	}

	public U getSecond() {
		return this.second;
	}
}
