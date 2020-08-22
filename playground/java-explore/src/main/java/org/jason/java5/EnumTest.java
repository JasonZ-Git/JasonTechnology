package org.jason.java5;

public class EnumTest {
    public static enum EnumOne {
        One;
        private int one = 1;

        public EnumOne changeOne() {
            one = 2;
            return this;
        }


    }

    public static void main(String[] args) {
        System.out.println(EnumOne.One.changeOne());
    }
}
