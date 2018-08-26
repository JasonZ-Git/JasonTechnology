#include <jni.h>

extern "C" {

/**
 * Convert decimal number to modulo 7 number, for example, input 10 will get output 13.
 */
jint toDecimalFromModuloSeven(jint input){
    if (input >= 10) {
        return input%10 + toDecimalFromModuloSeven(input/10) * 7;
    } else {
        return input;
    }
}

/**
 * Convert modulo 7 number to decimal number, for example, input 10 will get output 7.
 */
jint toModuloSevenFromDecimal(jint input){
    if (input >= 7) {
        return input%7 +  toModuloSevenFromDecimal(input/7)*10;
    } else {
        return input;
    }
}

/**
 * Add to modulo 7 number
 */
JNIEXPORT jint JNICALL
Java_org_jason_android_calculator_CalculatorModulo7Proxy_add(JNIEnv *env, jobject instance, jint first,
                                                      jint second) {
    return toModuloSevenFromDecimal(toDecimalFromModuloSeven(first) + toDecimalFromModuloSeven(second));

}

/**
 * subtraction of two modulo 7 number
 */
JNIEXPORT jint JNICALL
Java_org_jason_android_calculator_CalculatorModulo7Proxy_sub(JNIEnv *env, jobject instance, jint first,
                                                      jint second) {
    return  toModuloSevenFromDecimal(toDecimalFromModuloSeven(first) - toDecimalFromModuloSeven(second));
}

/**
 * multiplication of two modulo 7 number
 */
JNIEXPORT jint JNICALL
Java_org_jason_android_calculator_CalculatorModulo7Proxy_mul(JNIEnv *env, jobject instance, jint first,
                                                      jint second) {
    return  toModuloSevenFromDecimal(toDecimalFromModuloSeven(first) * toDecimalFromModuloSeven(second));
}

}

