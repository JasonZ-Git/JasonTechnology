/**
 * 
 */
package org.jason.util.finalclass;

/**
 * Two objects
 * 
 * @author Jason Zhang
 * 
 */
public class Pair<T, U> {
	private final T left;
	private final U right;

	protected Pair(T t, U u) {
		this.left = t;
		this.right = u;
	}
	
	public static <T, U> Pair<T,U> of(T t, U u) {
		return new Pair<>(t, u);
	}

	public T getLeft() {
		return this.left;
	}

	public U getRight() {
		return this.right;
	}
}
