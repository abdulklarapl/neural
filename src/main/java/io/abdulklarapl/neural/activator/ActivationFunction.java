package io.abdulklarapl.neural.activator;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public interface ActivationFunction {

    double activate(double weightSum);
    double derivative(double weightSum);
    ActivationFunction copy();
}
