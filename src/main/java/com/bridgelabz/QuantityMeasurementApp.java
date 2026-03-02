package com.bridgelabz;


public class QuantityMeasurementApp {
    // Creating inner class
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
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("Are equal: " + f1.equals(f2));
    }
}