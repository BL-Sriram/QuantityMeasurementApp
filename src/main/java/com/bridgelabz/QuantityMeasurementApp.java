package com.bridgelabz;


public class QuantityMeasurementApp {
    // Creating inner class
    // FEET CLASS
    static class Feet{
        private final double value;

        // constructor
        public Feet(double value){
            this.value = value;
        }

        // override equals - When we override equals we need to take parameter as
        // OBJECT(Because equals() must work for ALL objects.)
        // if we don't do that then it is overloading not overriding.
        @Override
        public boolean equals(Object obj){
            if(this == obj){
                System.out.println("here it comes");
                return true;
            }

            if(obj == null || getClass() != obj.getClass()){
                return false;
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // INCHES CLASS
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static boolean checkFeetEquality(double value1, double value2){
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);

        return f1.equals(f2);
    }

    public static boolean checkInchesEquality(double value1, double value2){
        Inches i1 = new Inches(value1);
        Inches i2 = new Inches(value2);

        return i1.equals(i2);
    }
    public static void main(String[] args) {
        System.out.println("1.0 ft and 1.0 ft Equal? "
                + checkFeetEquality(1.0, 1.0));

        System.out.println("1.0 inch and 1.0 inch Equal? "
                + checkInchesEquality(1.0, 1.0));
    }
}