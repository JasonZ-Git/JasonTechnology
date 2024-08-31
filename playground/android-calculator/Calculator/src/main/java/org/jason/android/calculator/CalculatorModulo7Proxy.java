package org.jason.android.calculator;

/**
 * A proxy class, uses c++ to do the real operation.
 * All the number here is modulo 7 based.
 * <p>
 * Created by jason on 19/03/16.
 */
public class CalculatorModulo7Proxy {
    static {
        System.loadLibrary("calculator");
    }

    /**
     * Operate of modulo 7, all the result and input are in modulo 7 format.
     *
     * @param type   operation type, supporting +, - and * operation.
     * @param first  modulo seven number.
     * @param second modulo sever number.
     * @return modulo seven result
     */
    public int operate(OperationType type, int first, int second) {
        int result = 0;
        switch (type) {
            case Add:
                result = add(first, second);
                break;
            case Sub:
                result = sub(first, second);
                break;
            case Mul:
                result = mul(first, second);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Modulo 7 add operation.
     *
     * @param first  modulo seven number.
     * @param second modulo sever number.
     * @return modulo seven result of first + second.
     */
    public native int add(int first, int second);

    /**
     * Modulo 7 subtraction operation.
     *
     * @param first  modulo seven number.
     * @param second modulo sever number.
     * @return modulo seven result of first - second.
     */
    public native int sub(int first, int second);

    /**
     * Modulo 7 multiplication operation.
     *
     * @param first  modulo seven number.
     * @param second modulo sever number.
     * @return modulo seven result of first * second.
     */
    public native int mul(int first, int second);
}
