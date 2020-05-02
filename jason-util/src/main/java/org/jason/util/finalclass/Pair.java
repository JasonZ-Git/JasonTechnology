/**
 * 
 */
package org.jason.util.finalclass;

/**
 * Two objects, this class is original a final class, later on there are some scenarios which required extend it(For example, T and U are of same type)
 * So the final keyword is removed and it is allowed to be extended.
 *
 * If follows the design Pattern of JKD: UnaryOperator extends Function
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
