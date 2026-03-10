package com.bridgelabz;

/**
 * Functional Interface used as flag for which quantity supports arithmetic or not.
 */

@FunctionalInterface
public interface SupportsArithmetic {
    boolean isSupported();
}